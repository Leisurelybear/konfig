/**
 * FileName: GroupUserController
 * Author:   jason
 * Date:     2021/5/26 21:02
 * Description:
 */
package org.zhangxujie.konfig.controller;

import org.springframework.web.bind.annotation.*;
import org.zhangxujie.konfig.common.CommonResult;
import org.zhangxujie.konfig.model.Account;
import org.zhangxujie.konfig.service.AccountService;
import org.zhangxujie.konfig.service.GroupService;
import org.zhangxujie.konfig.util.TokenUtil;

import javax.annotation.Resource;

@RestController
@RequestMapping("/group_user")
@CrossOrigin
public class GroupUserController {

    //TODO:增删查

    @Resource
    private GroupService groupService;

    @Resource
    private AccountService accountService;


    @PostMapping(value = "/add/{accountId}")
    public CommonResult addUser(@PathVariable("accountId") Integer accountId, @RequestParam("token") String token) {

        if (!TokenUtil.validateToken(token)) {
            return CommonResult.unauthorized("Token失效，请重新登录！");
        }

        //只可以查询自己创建的用户组
        String createUsername = TokenUtil.getUsernameFromToken(token);
        Account createUser = accountService.getAdminByUsername(createUsername);



        return CommonResult.success(null);
    }

    @PostMapping(value = "/remove/{accountId}")
    public CommonResult removeUser(@PathVariable("accountId") Integer accountId, @RequestParam("token") String token) {

        if (!TokenUtil.validateToken(token)) {
            return CommonResult.unauthorized("Token失效，请重新登录！");
        }

        //只可以查询自己创建的用户组
        String createUsername = TokenUtil.getUsernameFromToken(token);
        Account createUser = accountService.getAdminByUsername(createUsername);


        return CommonResult.success(null);
    }
}
