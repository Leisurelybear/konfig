/**
 * FileName: GroupUserController
 * Author:   jason
 * Date:     2021/5/26 21:02
 * Description:
 */
package org.zhangxujie.konfig.controller;

import org.springframework.web.bind.annotation.*;
import org.zhangxujie.konfig.common.CommonResult;
import org.zhangxujie.konfig.dto.GroupUserAddUserReq;
import org.zhangxujie.konfig.dto.GroupUserItem;
import org.zhangxujie.konfig.dto.GroupUserRemoveUserReq;
import org.zhangxujie.konfig.model.Account;
import org.zhangxujie.konfig.model.Group;
import org.zhangxujie.konfig.model.GroupUser;
import org.zhangxujie.konfig.service.AccountService;
import org.zhangxujie.konfig.service.GroupService;
import org.zhangxujie.konfig.service.GroupUserService;
import org.zhangxujie.konfig.util.TokenUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group_user")
@CrossOrigin
public class GroupUserController {


    @Resource
    private GroupService groupService;

    @Resource
    private AccountService accountService;

    @Resource
    private GroupUserService groupUserService;

    @PostMapping("/list/{groupId}")
    public CommonResult list(@PathVariable("groupId") Integer groupId, @RequestParam("token") String token) {
        //查看当前group的所有用户
        if (!TokenUtil.validateToken(token)) {
            return CommonResult.unauthorized("Token失效，请重新登录！");
        }
        List<GroupUserItem> groupUserItemList = new ArrayList<>();

        List<GroupUser> groupUserList = groupUserService.list(groupId);

        List<Integer> accountIds = new ArrayList<>();
        if (groupUserList == null || groupUserList.size() == 0) {
            return CommonResult.success(new ArrayList<>());
        }
        Map<Integer, Integer> userIdGroupUserIdMapping = new HashMap<>();//为了拼结果
        groupUserList.forEach(c -> {
            accountIds.add(c.getAccountId());
            userIdGroupUserIdMapping.put(c.getAccountId(), c.getId());
        });

        List<Account> accounts = accountService.listByAids(accountIds);
        accounts.forEach(c -> {
            GroupUserItem groupUserItem = new GroupUserItem();
            groupUserItem.setAccountId(c.getId());
            groupUserItem.setEmail(c.getEmail());
            groupUserItem.setGroupUserId(userIdGroupUserIdMapping.get(c.getId()));
            groupUserItem.setUsername(c.getUsername());
            groupUserItemList.add(groupUserItem);
        });

        return CommonResult.success(groupUserItemList);
    }


    @PostMapping(value = "/add")
    public CommonResult addUser(@RequestBody GroupUserAddUserReq req, @RequestParam("token") String token) {

        if (!TokenUtil.validateToken(token)) {
            return CommonResult.unauthorized("Token失效，请重新登录！");
        }

        //
        String currentUsername = TokenUtil.getUsernameFromToken(token);
        Account currentUser = accountService.getAdminByUsername(currentUsername);

        Group group = groupService.getGroupsByIds(new ArrayList<Integer>() {{
            add(req.getGroupId());
        }}).get(0);
        Integer rootAccountId = group.getRootAccountId();
        if (!rootAccountId.equals(currentUser.getId())) {
            return CommonResult.failed("操作失败，您不是该用户组的创建者");
        }

        Integer id = groupUserService.add(req.getGroupId(), req.getAccountId(), currentUser.getId());
        if (id == -1) {
            return CommonResult.failed("该组中已有该用户[" + req.getAccountId() + "]！不能重复添加");
        }
        if (id <= 0) {
            return CommonResult.failed("添加失败");
        }
        return CommonResult.success("成功");
    }

    @PostMapping(value = "/remove")
    public CommonResult removeUser(@RequestBody GroupUserRemoveUserReq req, @RequestParam("token") String token) {
        //只可以删除自己创建的用户组
        if (!TokenUtil.validateToken(token)) {
            return CommonResult.unauthorized("Token失效，请重新登录！");
        }

        String currentUsername = TokenUtil.getUsernameFromToken(token);
        Account currentUser = accountService.getAdminByUsername(currentUsername);

        Group group = groupService.getGroupsByIds(new ArrayList<Integer>() {{
            add(req.getGroupId());
        }}).get(0);
        Integer rootAccountId = group.getRootAccountId();
        if (!rootAccountId.equals(currentUser.getId())) {
            return CommonResult.failed("操作失败，您不是该用户组的创建者");
        }


        int ok = groupUserService.remove(req.getId(), currentUser.getId());
        if (ok < 0) {
            return CommonResult.failed("操作失败，您不可以删除自己（所有者）,您可以尝试直接删除用户组。");
        }

        return CommonResult.success("成功");
    }
}
