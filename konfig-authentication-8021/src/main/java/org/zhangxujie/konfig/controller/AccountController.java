/**
 * FileName: AccountController
 * Author:   jason
 * Date:     2021/3/10 18:04
 * Description:
 */
package org.zhangxujie.konfig.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhangxujie.konfig.common.CommonResult;
import org.zhangxujie.konfig.dto.*;
import org.zhangxujie.konfig.model.Account;
import org.zhangxujie.konfig.model.Group;
import org.zhangxujie.konfig.model.UserInfo;
import org.zhangxujie.konfig.service.AccountService;
import org.zhangxujie.konfig.service.UserInfoService;
import org.zhangxujie.konfig.util.TokenUtil;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Api(tags = "AccountController", description = "用户管理")
@Slf4j
public class AccountController {
    @Resource
    private AccountService accountService;

    @Resource
    private UserInfoService userInfoService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @ApiOperation(value = "用户注册")
    @PostMapping(value = "register")
    public CommonResult<Account> register(@RequestBody AccountRegisterParam accountRegisterParam) {
        Account umsAdmin = accountService.register(accountRegisterParam);
        if (umsAdmin == null) {
            return CommonResult.failed("用户名已存在！");
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "查重")
    @PostMapping(value = "dup")
    public CommonResult dup(@RequestBody AccountRegisterParam accountRegisterParam) {
        Integer count = accountService.dup(accountRegisterParam);
        if (count != 0) {
            return CommonResult.failed("用户名已存在！");
        }
        return CommonResult.success(true);
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "login")
    public CommonResult login(@Validated @RequestBody AccountLoginReqParam accountLoginReqParam) {
        String token = accountService.login(accountLoginReqParam.getUsername(), accountLoginReqParam.getPassword());

        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误!");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "info")
    public CommonResult info(Principal principal) {
        if (principal == null) {
            return CommonResult.failed("token失效！请重新登录！");
        }
        String username = principal.getName();
        Account account = accountService.getAdminByUsername(username);

        UserInfo userInfo = userInfoService.getUserInfoByAccountId(account.getId());

        InfoRespParam info = new InfoRespParam();
        if (account != null) {
            info.setAccountId(account.getId());
            info.setUsername(account.getUsername());
            info.setEmail(account.getEmail());
        }
        if (userInfo != null) {
            info.setExtra(userInfo.getExtra());
            info.setImgUrl(userInfo.getImgUrl());
            info.setNickname(userInfo.getNickname());
            info.setPhone(userInfo.getPhone());
            info.setUpdateTime(userInfo.getUpdateTime());
            info.setLastAccessedTime(userInfo.getLastAccessedTime());
        }


        return CommonResult.success(info);
    }

    @ApiOperation(value = "获取用户列表")
    @PostMapping(value = "queryall")
    public CommonResult queryall(@RequestBody AccountQueryReqParam reqParam, @RequestParam("token") String token) {

        if (!TokenUtil.validateToken(token)){
            return CommonResult.unauthorized("Token失效，请重新登录！");
        }
        if (reqParam.getPageNumber() <= 0){
            reqParam.setPageNumber(1);
        }
        if (reqParam.getPageSize() <= 0){
            reqParam.setPageSize(10);
        }

        log.info(reqParam.toString());
        Integer count = accountService.countAll();

        List<AccountItem> userList = accountService.queryall(reqParam.getUsernameLike(), reqParam.getEmailLike(), reqParam.getPageNumber(), reqParam.getPageSize(), reqParam.getSort());

        AccountQueryRespParam resp = new AccountQueryRespParam(reqParam.getPageNumber(), reqParam.getPageSize(), count, userList);

        return CommonResult.success(resp);
    }

    @ApiOperation(value = "查询单个用户细节")
    @GetMapping(value = "detail")
    @PreAuthorize("hasAnyAuthority('root', 'user:edit')")
    public CommonResult detail(Principal principal) {

        return CommonResult.success(null);
    }


    @PostMapping("/user/list_by_aids")
    public CommonResult getUsersByAid(@RequestBody List<Integer> accountIds, @RequestParam("token") String token) {
        if (!TokenUtil.validateToken(token)){
            return CommonResult.unauthorized("Token失效，请重新登录！");
        }

        List<Account> accountList = accountService.listByAids(accountIds);

        for (int i = 0; i < accountList.size(); i++) {
            accountList.get(i).setPassword("");
            accountList.get(i).setEmail("");
        }

        return CommonResult.success(accountList);
    }

    @PostMapping("/list_by_name")
    public CommonResult listByName(@RequestBody String name, @RequestParam("token") String token){
        if (!TokenUtil.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        List<Account> accounts = accountService.listByName(name);

        return CommonResult.success(accounts);
    }



}
