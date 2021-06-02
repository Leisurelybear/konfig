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
import org.zhangxujie.konfig.common.Const;
import org.zhangxujie.konfig.common.LogUtil;
import org.zhangxujie.konfig.dao.AccountRemoteService;
import org.zhangxujie.konfig.dto.CreatePermissionReq;
import org.zhangxujie.konfig.dto.ListPermissionReq;
import org.zhangxujie.konfig.dto.ListPermissionResp;
import org.zhangxujie.konfig.dto.account.InfoRemote;
import org.zhangxujie.konfig.model.Account;
import org.zhangxujie.konfig.model.CfgPermission;
import org.zhangxujie.konfig.model.Group;
import org.zhangxujie.konfig.model.Permission;
import org.zhangxujie.konfig.service.CfgCollectionService;
import org.zhangxujie.konfig.service.CfgPermissionService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/cfg_permission")
@Api(tags = "CfgPermissionController", description = "配置权限")
@CrossOrigin
public class CfgPermissionController {

    @Resource
    private CfgPermissionService cfgPermissionService;

    @Resource
    private AccountRemoteService accountRemoteService;

    @Resource
    private CfgCollectionService cfgCollectionService;

    @Resource
    private LogUtil opLog;

    @PostMapping("/list")
    public CommonResult list(@RequestBody ListPermissionReq req, @RequestParam("token") String token) {
        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }
        InfoRemote info = accountRemoteService.infoFromToken(token);

        if (!cfgCollectionService.isOnwer(info.getUsername(), req.getCollectionIds().get(0))) {
            return CommonResult.failed("您没有该记录的操作权限");
        }

        ListPermissionResp resp = cfgPermissionService.list(req.getAccountIds(), req.getCollectionIds(), req.getGroupsIds(), req.getPageNumber(), req.getPageSize());

        List<CfgPermission> permissionList = resp.getPermissionList();

        List<Integer> groupIds = new ArrayList<>();
        List<Integer> accountIds = new ArrayList<>();

        permissionList.forEach(c -> {
            if (c.getType() == Const.CFG_PERMISSION_ACCOUNT) {
                accountIds.add(c.getAccountId());
            } else if (c.getType() == Const.CFG_PERMISSION_GROUP) {
                groupIds.add(c.getGroupId());
            }
        });

        List<Group> groupList = accountRemoteService.getGroupsByAid(groupIds);
        List<Account> accountList = accountRemoteService.getUsersByAid(accountIds);

        resp.setGroupList(groupList);
        resp.setAccountList(accountList);

        return CommonResult.success(resp);
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody CreatePermissionReq req, @RequestParam("token") String token) {
        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }
        InfoRemote info = accountRemoteService.infoFromToken(token);

        if (!cfgCollectionService.isOnwer(info.getUsername(), req.getCollectionId())) {
            return CommonResult.failed("您没有该记录的操作权限");
        }

        boolean ok = false;
        if (req.getAccountIds() != null && req.getAccountIds().size() != 0) {
            //说明添加用户配置权限
            ok = cfgPermissionService.createUserPermission(info.getUsername(), info.getAccountId(), req.getCollectionId(), req.getAccountIds());
            opLog.insert(Const.LOG_OPTYPE_PERMISSION, "添加权限", "", req.toString(), info.getUsername(), info.getAccountId());

        } else if (req.getGroupIds() != null && req.getGroupIds().size() != 0) {
            //说明添加用户组配置权限
            ok = cfgPermissionService.createGroupPermission(info.getUsername(), info.getAccountId(), req.getCollectionId(), req.getGroupIds());
            opLog.insert(Const.LOG_OPTYPE_PERMISSION, "添加权限", "", req.toString(), info.getUsername(), info.getAccountId());
        }

        return CommonResult.success(ok);
    }

    @DeleteMapping("/remove/{cfgPermissionId}")
    public CommonResult remove(@PathVariable("cfgPermissionId") Integer cfgPermissionId, @RequestParam("token") String token) {
        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }
        InfoRemote info = accountRemoteService.infoFromToken(token);

        CfgPermission cfgPermission = cfgPermissionService.getById(cfgPermissionId);
        if (!cfgCollectionService.isOnwer(info.getUsername(), cfgPermission.getCollectionId())) {
            return CommonResult.failed("您没有该记录的操作权限");
        }

        //不能删除自己的权限
        boolean ok = cfgPermissionService.remove(cfgPermissionId, info.getAccountId());
        if (!ok) {
            return CommonResult.failed("操作失败！不能删除自身权限");
        }
        opLog.insert(Const.LOG_OPTYPE_PERMISSION, "移除权限", "", "cfgPermissionId: " + cfgPermissionId, info.getUsername(), info.getAccountId());
        return CommonResult.success(ok);
    }

    @DeleteMapping("/remove_by_group/{groupId}")
    public CommonResult removeByGroup(@PathVariable("groupId") Integer groupId, @RequestParam("token") String token) {
        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }
        InfoRemote info = accountRemoteService.infoFromToken(token);

        boolean ok = cfgPermissionService.removeByGroupId(groupId, info.getAccountId());
        if (!ok) {
            return CommonResult.failed("操作失败！不能删除自身权限");
        }
        opLog.insert(Const.LOG_OPTYPE_PERMISSION, "移除权限", "", "groupId: " + groupId, info.getUsername(), info.getAccountId());

        return CommonResult.success(ok);
    }


}
