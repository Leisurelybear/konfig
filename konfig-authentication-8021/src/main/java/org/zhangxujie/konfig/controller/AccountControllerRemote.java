/**
 * FileName: RemoteController
 * Author:   jason
 * Date:     2021/5/20 12:01
 * Description:
 */
package org.zhangxujie.konfig.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhangxujie.konfig.common.CommonResult;
import org.zhangxujie.konfig.dto.InfoRespParam;
import org.zhangxujie.konfig.dto.account.InfoRemote;
import org.zhangxujie.konfig.model.Account;
import org.zhangxujie.konfig.model.Group;
import org.zhangxujie.konfig.model.UserInfo;
import org.zhangxujie.konfig.service.AccountService;
import org.zhangxujie.konfig.service.GroupService;
import org.zhangxujie.konfig.service.GroupUserService;
import org.zhangxujie.konfig.service.UserInfoService;
import org.zhangxujie.konfig.util.TokenUtil;
import org.zhangxujie.konfig.utils.JwtTokenUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rpc")
public class AccountControllerRemote {

    @Resource
    private AccountService accountService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private GroupUserService groupUserService;

    @GetMapping("/info/{token}")
    public InfoRemote infoByToken(@PathVariable("token") String token) {
        String username = TokenUtil.getUsernameFromToken(token);

        if (username == null || username.equals("")) {
            return null;
        }
        InfoRemote info = new InfoRemote();


        Account account = accountService.getAdminByUsername(username);
        if (account != null) {
            info.setAccountId(account.getId());
            info.setUsername(account.getUsername());
            info.setEmail(account.getEmail());
        }

        UserInfo userInfo = userInfoService.getUserInfoByAccountId(account.getId());
        if (userInfo != null) {
            info.setExtra(userInfo.getExtra());
            info.setImgUrl(userInfo.getImgUrl());
            info.setNickname(userInfo.getNickname());
            info.setPhone(userInfo.getPhone());
            info.setUpdateTime(userInfo.getUpdateTime());
            info.setLastAccessedTime(userInfo.getLastAccessedTime());
        }

        return info;
    }


    @GetMapping("/verify/{token}")
    public boolean checkToken(@PathVariable("token") String token) {
        if (TokenUtil.isTokenExpired(token)) {
            //过期
            return false;
        }

        String username = TokenUtil.getUsernameFromToken(token);
        if (username == null || username.equals("")) {
            //没取出用户名
            return false;
        }

        Account a = accountService.getAdminByUsername(username);
        if (a == null || a.getId() <= 0) {
            //数据库查询用户
            return false;
        }

        return true;
    }

    @GetMapping("/group/list_by_aid/{accountId}")
    public List<Integer> getGroupIdsByAid(@PathVariable("accountId") Integer accountId) {

        List<Integer> groupIdList = groupUserService.getGroupIdsByAccountId(accountId);

        return groupIdList;
    }

    @GetMapping("/user/list_by_aids/{accountIds}")
    public List<Account> getUsersByAid(@PathVariable("accountIds") String accountIds) {

        String[] aidsString = accountIds.trim().split("_");
        List<Integer> aids = new ArrayList<>();
        for (String aid : aidsString) {
            aids.add(Integer.parseInt(aid));
        }

        List<Account> accountList = accountService.listByAids(aids);

        return accountList;
    }


}
