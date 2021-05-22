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
import org.zhangxujie.konfig.dao.AccountRemoteDAO;
import org.zhangxujie.konfig.dto.GetUndoAuditListResp;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
@RequestMapping("/audit")
@Api(tags = "CfgAuditController", description = "配置上线审核")
@CrossOrigin
public class CfgAuditController {
    //TODO:需要在审批列表添加新的页面
    @Resource
    private CfgAuditService cfgAuditService;

    @Resource
    private AccountRemoteDAO accountRemoteDAO;

    @Resource
    private CfgPermissionService cfgPermissionService;

    @Resource
    private CfgCollectionService cfgCollectionService;

    @PostMapping("/undo_list") //等待自己处理的申请
    public CommonResult getUndoAuditList(@RequestParam("token") String token) {


        if (!accountRemoteDAO.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        InfoRemote info = accountRemoteDAO.infoFromToken(token);

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
        List<CfgCollection> cfgCollectionList = cfgCollectionService.queryByIds(collectionIds);
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        cfgCollectionList.forEach(c -> map.put(c.getId(), c.getIsDraft()));

        List<GetUndoAuditListResp> resp = new ArrayList<>();
        cfgAuditList.forEach(c -> {
            GetUndoAuditListResp a = new GetUndoAuditListResp();
            a.setId(c.getId());
            a.setApplicantAid(c.getApplicantAid());
            a.setCfgCollectionId(c.getCfgCollectionId());
            a.setSubmitTime(c.getSubmitTime());
            a.setContent(map.get(c.getCfgCollectionId()) == 1 ? "申请上线" : "申请下线");
            resp.add(a);
        });

        return CommonResult.success(resp);

    }

    @PostMapping("/my_list") //自己发起的申请
    public CommonResult getMyAuditList(@RequestParam("token") String token) {


        if (!accountRemoteDAO.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        InfoRemote info = accountRemoteDAO.infoFromToken(token);

        List<Integer> condition = new ArrayList<>();
        condition.add(Const.CFG_AUDIT_UNDO);
        condition.add(Const.CFG_AUDIT_APPROVE);
        condition.add(Const.CFG_AUDIT_REJECT);

        List<CfgAudit> cfgAuditList = cfgAuditService.selectByApplicantId(info.getAccountId(), condition);

        return CommonResult.success(cfgAuditList);

    }

    @PostMapping("/history_list") //历史自己处理的审批
    public CommonResult getHistoryAuditList(@RequestParam("token") String token) {


        if (!accountRemoteDAO.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        InfoRemote info = accountRemoteDAO.infoFromToken(token);

        List<Integer> condition = new ArrayList<>();
        condition.add(Const.CFG_AUDIT_APPROVE);
        condition.add(Const.CFG_AUDIT_REJECT);

        List<CfgAudit> cfgAuditList = cfgAuditService.selectByReviewerId(info.getAccountId(), condition);

        return CommonResult.success(cfgAuditList);

    }

    @PostMapping("/handle") //历史自己处理的审批
    public CommonResult handleAudit(@RequestParam("token") String token, @RequestBody HandleAuditReq req) {

        if (!accountRemoteDAO.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        InfoRemote info = accountRemoteDAO.infoFromToken(token);
        if (!cfgCollectionService.isOnwer(info.getUsername(), req.getCollectionId())) {
            log.info("该用户没有权限");
            return CommonResult.failed("该用户没有权限");
        }

        if (req.getIsApproved()) {
            //同意
            cfgAuditService.approve(req.getAuditId(), info.getAccountId());
            cfgCollectionService.switchDraftStatus(req.getCollectionId(), info.getUsername());
        } else {
            //驳回
            cfgAuditService.reject(req.getAuditId(), info.getAccountId());
            cfgCollectionService.switchDraftStatus(req.getCollectionId(), info.getUsername());
        }


        return CommonResult.success(null);

    }


}
