/**
 * FileName: GroupController
 * Author:   jason
 * Date:     2021/5/25 13:47
 * Description:
 */
package org.zhangxujie.konfig.controller;

import org.springframework.web.bind.annotation.*;
import org.zhangxujie.konfig.common.CommonResult;
import org.zhangxujie.konfig.dto.*;
import org.zhangxujie.konfig.model.Account;
import org.zhangxujie.konfig.model.Group;
import org.zhangxujie.konfig.service.AccountService;
import org.zhangxujie.konfig.service.GroupService;
import org.zhangxujie.konfig.util.TokenUtil;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupController {
    //TODO：增删查

    @Resource
    private GroupService groupService;

    @Resource
    private AccountService accountService;

    @PostMapping("/list_by_name")
    public CommonResult listByName(@RequestBody String name, @RequestParam("token") String token) {
        if (!TokenUtil.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        List<Group> groupList = groupService.listByName(name);

        return CommonResult.success(groupList);
    }

    @PostMapping(value = "/list")
    public CommonResult list(@RequestBody GroupListReqParam reqParam, @RequestParam("token") String token) {

        if (!TokenUtil.validateToken(token)) {
            return CommonResult.unauthorized("Token失效，请重新登录！");
        }

        //只可以查询自己创建的用户组
        String createUsername = TokenUtil.getUsernameFromToken(token);
        Account createUser = accountService.getAdminByUsername(createUsername);

        if (reqParam.getPageNumber() <= 0) {
            reqParam.setPageNumber(1);
        }
        if (reqParam.getPageSize() <= 0) {
            reqParam.setPageSize(10);
        }

        Integer count = groupService.countList(reqParam.getGroupNameLike(), createUser.getId());
        List<Group> groupList = groupService.list(reqParam.getGroupNameLike(), reqParam.getPageNumber(), reqParam.getPageSize(), reqParam.getSort(), createUser.getId());

        GroupListRespParam resp = new GroupListRespParam(reqParam.getPageNumber(), reqParam.getPageSize(), count, groupList);

        return CommonResult.success(resp);
    }

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody String groupName, @RequestParam("token") String token) {

        if (!TokenUtil.validateToken(token)) {
            return CommonResult.unauthorized("Token失效，请重新登录！");
        }

        //只可以查询自己创建的用户组
        String createUsername = TokenUtil.getUsernameFromToken(token);
        Account createUser = accountService.getAdminByUsername(createUsername);

        Integer count = groupService.countByGroupName(groupName);

        if (count != 0) {
            return CommonResult.failed("用户组名称重复！");
        }
        Integer id = groupService.create(groupName, createUser.getId());
        if (id <= 0){
            return CommonResult.failed("创建失败！");
        }
        return CommonResult.success(id);
    }

    @PostMapping(value = "/delete/{groupId}")
    public CommonResult delete(@PathVariable("groupId") Integer groupId, @RequestParam("token") String token) {

        if (!TokenUtil.validateToken(token)) {
            return CommonResult.unauthorized("Token失效，请重新登录！");
        }

        //只可以删除自己创建的用户组
        String createUsername = TokenUtil.getUsernameFromToken(token);
        Account createUser = accountService.getAdminByUsername(createUsername);

        Integer ok = groupService.delete(groupId, createUser.getId());

        return CommonResult.success(ok);
    }

}
