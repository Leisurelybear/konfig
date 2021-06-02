/**
 * FileName: CfgCollectionController
 * Author:   jason
 * Date:     2021/4/4 15:17
 * Description:
 */
package org.zhangxujie.konfig.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.zhangxujie.konfig.common.CommonResult;
import org.zhangxujie.konfig.common.Const;
import org.zhangxujie.konfig.common.LogUtil;
import org.zhangxujie.konfig.dto.*;
import org.zhangxujie.konfig.dto.account.InfoRemote;
import org.zhangxujie.konfig.model.CfgCollection;
import org.zhangxujie.konfig.dao.AccountRemoteService;
import org.zhangxujie.konfig.service.CfgAuditService;
import org.zhangxujie.konfig.service.CfgCollectionService;
import org.zhangxujie.konfig.service.CfgPermissionService;
import org.zhangxujie.konfig.util.TokenUtil;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/cfg_coll")
@Api(tags = "CfgCollectionController", description = "访问配置集合")
@CrossOrigin
public class CfgCollectionController {

    public static final String USER_INVOKE_URL = "http://KONFIG-AUTHENTICATION";

    @Resource
    private CfgCollectionService cfgCollectionService;

    @Resource
    private AccountRemoteService accountRemoteService;

    @Resource
    private CfgPermissionService cfgPermissionService;

    @Resource
    private CfgAuditService cfgAuditService;

    @Resource
    private LogUtil opLog;


    @PostMapping("/list")
    public CommonResult getCfgCollections(@RequestParam("token") String token, @RequestBody GetCfgCollectionsReq req) {


        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        List<CfgCollection> resp = cfgCollectionService.query(req.getNameLike(), req.getSort(), req.getPageNum(), req.getNums(), req.getIsDraft(), req.getCreateUsername());

        return CommonResult.success(resp);

    }


    @PostMapping("/add/{collectionName}")
    public CommonResult add(@RequestParam("token") String token, @PathVariable String collectionName) {


        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        InfoRemote info = accountRemoteService.infoFromToken(token);

        AddCollectionReq req = new AddCollectionReq(collectionName);

        String username = TokenUtil.getUsernameFromToken(token);

        AddCollectionResp resp = cfgCollectionService.add(req, username);

        //添加权限表，把自己加到权限表里
        cfgPermissionService.initCreateUserPermission(info.getAccountId(), resp.getId(), info.getUsername(), info.getAccountId());

        if (resp.getId() == -1) {
            return CommonResult.failed("配置集名重复，请更改后提交");
        }
        opLog.insert(Const.LOG_OPTYPE_CONFIG, "创建配置集", "", "collectionName: " + collectionName, info.getUsername(), info.getAccountId());

        return CommonResult.success(resp);

    }


    @DeleteMapping("/del/{collectionId}")
    public CommonResult delete(@RequestParam("token") String token, @PathVariable Integer collectionId) {

        if (!TokenUtil.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        if (!cfgPermissionService.hasPermission(token, collectionId)) {
            return CommonResult.failed("您没有该记录的操作权限");
        }

        DeleteCollectionReq req = new DeleteCollectionReq(collectionId);
        InfoRemote info = accountRemoteService.infoFromToken(token);

        String username = TokenUtil.getUsernameFromToken(token);

        DeleteCollectionResp resp = cfgCollectionService.delete(req, username);

        if (resp.getStatus() == false) {
            return CommonResult.failed("该配置为线上配置，不可删除，只能够上线替换！");
        }
        opLog.insert(Const.LOG_OPTYPE_CONFIG, "删除配置集", "", "collectionId: " + collectionId, info.getUsername(), info.getAccountId());

        return CommonResult.success(resp);

    }

    @PostMapping("/change_status/{collectionId}")
    public CommonResult changeDraftStatus(@RequestParam("token") String token, @PathVariable Integer collectionId) {

        if (!TokenUtil.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        if (!cfgPermissionService.hasPermission(token, collectionId)) {
            return CommonResult.failed("您没有该记录的操作权限");
        }
        //获取用户信息
        InfoRemote info = accountRemoteService.infoFromToken(token);


        if (!cfgCollectionService.isOnwer(info.getUsername(), collectionId)) {
            log.info("当前用户无权限主动上线/下线，提交审核");
            int auditId = cfgAuditService.submit(collectionId, info.getAccountId());
            if (auditId < 0) {
                return CommonResult.failed("有未处理申请[ID:" + (-auditId) + "]在流程中，不能继续提交！");
            }
            opLog.insert(Const.LOG_OPTYPE_CONFIG, "申请配置集上线/下线", "", "collectionId: " + collectionId, info.getUsername(), info.getAccountId());
            return CommonResult.success(null, "已经提交（上线/下线）申请[ID:" + auditId + "]");
        }

        //有权限，直接改状态
        log.info("该用户为配置所有者，可以直接上线");
        cfgCollectionService.switchDraftStatus(collectionId, info.getUsername());
        opLog.insert(Const.LOG_OPTYPE_CONFIG, "配置集上线/下线", "", "collectionId: " + collectionId, info.getUsername(), info.getAccountId());

        return CommonResult.success("操作成功!");

    }

}
