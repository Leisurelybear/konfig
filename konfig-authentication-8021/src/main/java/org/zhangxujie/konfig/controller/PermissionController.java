/**
 * FileName: PermissionController
 * Author:   jason
 * Date:     2021/4/4 15:48
 * Description:
 */
package org.zhangxujie.konfig.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhangxujie.konfig.api.CommonResult;
import org.zhangxujie.konfig.dto.GetPermResp;
import org.zhangxujie.konfig.model.Account;
import org.zhangxujie.konfig.model.Permission;
import org.zhangxujie.konfig.service.AccountService;
import org.zhangxujie.konfig.service.GroupUserService;
import org.zhangxujie.konfig.service.PermissionService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/perm")
public class PermissionController {

    @Resource
    PermissionService permissionService;

    @Resource
    GroupUserService groupUserService;

    @Resource
    AccountService accountService;

    @GetMapping("/get/{username}")
    public CommonResult<GetPermResp> getPerm(@PathVariable String username){

        List<Permission> permissionList = null;
        Account user = accountService.getAdminByUsername(username);
        if (user == null){
            return CommonResult.failed("获取用户失败");
        }

        //TODO:先查出用户所在的Group，再同时查询用户单独的权限和Group的权限
        //1、对于组权限的确定：查出当前用户的所有所在组的id，然后查所有拥有的权限
        List<Integer> groupIds = groupUserService.getGroupIdsByAccountId(user.getId());//

        //2、对于用户个人权限的确定：查出当前用户单独分配的权限
        permissionList = permissionService.getPermissionList(user.getId(), groupIds);

        List<String> perms = new ArrayList<>();
        permissionList.forEach(c -> perms.add(c.getPermission()));
        GetPermResp resp = new GetPermResp(username, perms);

        return CommonResult.success(resp);
    }
}
