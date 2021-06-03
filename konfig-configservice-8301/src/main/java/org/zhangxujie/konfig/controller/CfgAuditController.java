/**
 * FileName: CfgAuditController
 * Author:   jason
 * Date:     2021/5/21 21:14
 * Description:
 */
package org.zhangxujie.konfig.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.zhangxujie.konfig.common.CommonResult;
import org.zhangxujie.konfig.common.Const;
import org.zhangxujie.konfig.common.LogUtil;
import org.zhangxujie.konfig.dao.AccountRemoteService;
import org.zhangxujie.konfig.dao.MqProducer;
import org.zhangxujie.konfig.dto.HandleAuditReq;
import org.zhangxujie.konfig.dto.account.InfoRemote;
import org.zhangxujie.konfig.model.CfgAudit;
import org.zhangxujie.konfig.model.CfgCollection;
import org.zhangxujie.konfig.service.CfgAuditService;
import org.zhangxujie.konfig.service.CfgCollectionService;
import org.zhangxujie.konfig.service.CfgPermissionService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/audit")
@Api(tags = "CfgAuditController", description = "配置上线审核")
@CrossOrigin
public class CfgAuditController {
    @Resource
    private CfgAuditService cfgAuditService;

    @Resource
    private AccountRemoteService accountRemoteService;

    @Resource
    private CfgPermissionService cfgPermissionService;

    @Resource
    private CfgCollectionService cfgCollectionService;

    @Resource
    private LogUtil opLog;

    @Resource
    private MqProducer kafkaProducer;

    @PostMapping("/undo_list") //等待自己处理的申请
    public CommonResult getUndoAuditList(@RequestParam("token") String token) {


        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        InfoRemote info = accountRemoteService.infoFromToken(token);

        //1、先找自己拥有的配置集ID列表
        List<CfgCollection> collectionList = cfgCollectionService.getOwnCollectionList(info.getUsername());
        List<Integer> collectionIds = new ArrayList<>();
        collectionList.forEach(c -> {
            collectionIds.add(c.getId());
        });

        //2、查找 在配置集ID列表 中，同时 存在audit表的记录
        List<Integer> condition = new ArrayList<>();
        condition.add(Const.CFG_AUDIT_UNDO);
        List<CfgAudit> cfgAuditList = cfgAuditService.selectByCollectionIds(collectionIds, condition);

        //3、查找配置集ID列表每条记录的状态，（让审批这知道对方是要申请上线还是下线）
//        List<CfgCollection> cfgCollectionList = cfgCollectionService.queryByIds(collectionIds);
//        Map<Integer, Integer> map = new ConcurrentHashMap<>();
//        cfgCollectionList.forEach(c -> map.put(c.getId(), c.getIsDraft()));

//        List<GetUndoAuditListResp> resp = new ArrayList<>();
//        cfgAuditList.forEach(c -> {
//            GetUndoAuditListResp a = new GetUndoAuditListResp();
//            a.setId(c.getId());
//            a.setApplicantAid(c.getApplicantAid());
//            a.setCfgCollectionId(c.getCfgCollectionId());
//            a.setSubmitTime(c.getSubmitTime());
//            a.setContent(map.get(c.getCfgCollectionId()) == 1 ? "申请上线" : "申请下线");
//            resp.add(a);
//        });

        return CommonResult.success(cfgAuditList);

    }

    @PostMapping("/my_list") //自己发起的申请
    public CommonResult getMyAuditList(@RequestParam("token") String token) {


        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        InfoRemote info = accountRemoteService.infoFromToken(token);

        List<Integer> condition = new ArrayList<>();
        condition.add(Const.CFG_AUDIT_UNDO);
        condition.add(Const.CFG_AUDIT_APPROVE);
        condition.add(Const.CFG_AUDIT_REJECT);

        List<CfgAudit> cfgAuditList = cfgAuditService.selectByApplicantId(info.getAccountId(), condition);

        return CommonResult.success(cfgAuditList);

    }

    @PostMapping("/history_list") //历史自己处理的审批
    public CommonResult getHistoryAuditList(@RequestParam("token") String token) {

        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        InfoRemote info = accountRemoteService.infoFromToken(token);

        List<Integer> condition = new ArrayList<>();
        condition.add(Const.CFG_AUDIT_APPROVE);
        condition.add(Const.CFG_AUDIT_REJECT);

        List<CfgAudit> cfgAuditList = cfgAuditService.selectByReviewerId(info.getAccountId(), condition);

        return CommonResult.success(cfgAuditList);
    }

    @PostMapping("/handle") //历史自己处理的审批
    public CommonResult handleAudit(@RequestParam("token") String token, @RequestBody HandleAuditReq req) {

        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        InfoRemote info = accountRemoteService.infoFromToken(token);
        if (!cfgCollectionService.isOnwer(info.getUsername(), req.getCollectionId())) {
            log.info("该用户没有权限");
            return CommonResult.failed("该用户没有权限");
        }

        if (req.getIsApproved()) {
            //同意
            cfgAuditService.approve(req.getAuditId(), info.getAccountId());
            CfgAudit audit = cfgAuditService.selectByAuditId(req.getAuditId());
            boolean isOnline = cfgCollectionService.isOnline(audit.getCfgCollectionId());

            if (!isOnline && audit.getContent().contains("上线")){
                opLog.insert(Const.LOG_OPTYPE_CONFIG, "同意配置集上线", "", req.toString(), info.getUsername(), info.getAccountId());
                cfgCollectionService.switchDraftStatus(req.getCollectionId(), info.getUsername());
            }else if (isOnline && audit.getContent().contains("下线")){
                opLog.insert(Const.LOG_OPTYPE_CONFIG, "同意配置集下线", "", req.toString(), info.getUsername(), info.getAccountId());

                cfgCollectionService.switchDraftStatus(req.getCollectionId(), info.getUsername());
            }else {
                //说明申请之后，配置状态已经被切换了，已经晚了，什么都不做
                return CommonResult.success("该配置状态已经改变了！");
            }
        } else {
            //驳回
            opLog.insert(Const.LOG_OPTYPE_CONFIG, "驳回配置集上线/下线申请", "", req.toString(), info.getUsername(), info.getAccountId());

            cfgAuditService.reject(req.getAuditId(), info.getAccountId());
        }

        return CommonResult.success(null);

    }


}
