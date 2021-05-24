/**
 * FileName: CfgPermissionController
 * Author:   jason
 * Date:     2021/5/21 21:15
 * Description:
 */
package org.zhangxujie.konfig.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;
import org.zhangxujie.konfig.common.CommonResult;
import org.zhangxujie.konfig.dao.AccountRemoteService;
import org.zhangxujie.konfig.dto.CreatePermissionReq;
import org.zhangxujie.konfig.dto.ListPermissionReq;
import org.zhangxujie.konfig.dto.ListPermissionResp;
import org.zhangxujie.konfig.dto.account.InfoRemote;
import org.zhangxujie.konfig.service.CfgPermissionService;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/cfg_permission")
@Api(tags = "CfgPermissionController", description = "配置权限")
@CrossOrigin
public class CfgPermissionController {

    //TODO：申请配置权限、通过、拒绝（前端需要添加页面展示权限管理）

    @Resource
    private CfgPermissionService cfgPermissionService;

    @Resource
    private AccountRemoteService accountRemoteService;

    @PostMapping("/list")
    public CommonResult list(@RequestBody ListPermissionReq req, @RequestParam("token") String token) {
        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        ListPermissionResp resp = cfgPermissionService.list(req.getAccountIds(), req.getCollectionIds(), req.getGroupsIds(), req.getPageNumber(), req.getPageSize());

        return CommonResult.success(resp);
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody CreatePermissionReq req, @RequestParam("token") String token) {
        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }
        InfoRemote info = accountRemoteService.infoFromToken(token);
        boolean ok = false;
        if (req.getAccountIds() != null && req.getAccountIds().size() != 0) {
            //说明添加用户配置权限
            ok = cfgPermissionService.createUserPermission(info.getUsername(), info.getAccountId(), req.getCollectionId(), req.getAccountIds());


        } else if (req.getGroupIds() != null && req.getGroupIds().size() != 0) {
            //说明添加用户组配置权限
            ok = cfgPermissionService.createGroupPermission(info.getUsername(), info.getAccountId(), req.getCollectionId(), req.getGroupIds());
        }

        return CommonResult.success(ok);
    }

    @DeleteMapping("/remove/{cfgPermissionId}")
    public CommonResult remove(@PathVariable("cfgPermissionId") Integer cfgPermissionId, @RequestParam("token") String token) {
        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }
        InfoRemote info = accountRemoteService.infoFromToken(token);


        return CommonResult.success(null);
    }


}
