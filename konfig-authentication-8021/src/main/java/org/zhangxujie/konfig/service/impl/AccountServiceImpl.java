/**
 * FileName: AccountService
 * Author:   jason
 * Date:     2021/3/10 17:02
 * Description:
 */
package org.zhangxujie.konfig.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.dto.AccountItem;
import org.zhangxujie.konfig.dto.AccountQueryRespParam;
import org.zhangxujie.konfig.dto.AccountRegisterParam;
import org.zhangxujie.konfig.dto.AdminUserDetails;
import org.zhangxujie.konfig.mapper.AccountMapper;
import org.zhangxujie.konfig.mapper.PermissionMapper;
import org.zhangxujie.konfig.mapper.UserInfoMapper;
import org.zhangxujie.konfig.model.*;
import org.zhangxujie.konfig.service.AccountService;
import org.zhangxujie.konfig.utils.JwtTokenUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountDao;

    @Resource
    private PermissionMapper permissionDao;

    @Resource
    private UserInfoMapper userInfoDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenUtil jwtTokenUtil;


    public Account getAdminByUsername(String username) {

        AccountExample example = new AccountExample();
        example.createCriteria().andUsernameEqualTo(username);

        List<Account> accountList = accountDao.selectByExample(example);
        if (accountList.size() < 1) {
            return null;
        }

        return accountList.get(0);
    }


    //注册账号
    public Account register(AccountRegisterParam formAccount) {

        Account account = new Account();

        AccountExample exampleUsernameEqual = new AccountExample();
        exampleUsernameEqual.createCriteria().andUsernameEqualTo(formAccount.getUsername());

        //防止用户名重复，
        long c = accountDao.countByExample(exampleUsernameEqual);
        if (c >= 1) {
            return null;
        }

        String encodePassword = passwordEncoder.encode(formAccount.getPassword());
        account.setPassword(encodePassword);
        account.setUsername(formAccount.getUsername());
        account.setEmail(formAccount.getEmail());
        account.setIsDel(0);
        accountDao.insert(account);
        //插入用户表完毕

        //插入用户信息表
//        Account account2 = accountDao.selectOneByUsername(account.getUsername());
        UserInfo userInfo = new UserInfo();
        userInfo.setAccountId(account.getId());
        userInfo.setIsDel(0);
        userInfo.setUpdateTime(System.currentTimeMillis());
        userInfo.setNickname(account.getUsername());
        userInfo.setLastAccessedTime(System.currentTimeMillis());
        userInfoDao.insert(userInfo);

        Permission permission = new Permission();
        permission.setIdentityType("USER");
        permission.setIdentityId(account.getId());
        permission.setPermission("cfg");
        permission.setIsDel(0);
        permission.setTime(System.currentTimeMillis());
        permissionDao.insert(permission);

//        UserInfo accountInfo = new UserInfo();
//        accountInfo.setAccountId(account.getId());
//        accountInfo.setNickname(formAccount.getNickname());
//        accountInfo.setExtra(formAccount.getExtra());
//        accountInfo.setIsDel(0);
//        accountInfo.setPhone(formAccount.getPhone());
//        accountInfo.setUpdateTime(System.currentTimeMillis());
//        userInfoDao.insert(accountInfo);
//        //插入用户详细信息表完毕

        return account;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                log.error("用户名或密码错误！");
                return token;
            }
            if (!userDetails.isEnabled()) {
                log.error("用户无效或已删除！");
                return token;
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);

//            insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }


        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        Account admin = getAdminByUsername(username);
        if (admin != null) {
//            List<Permission> resourceList = permissionDao.getResourceList(admin.getId());
            PermissionExample example = new PermissionExample();
            example.createCriteria().andIdentityTypeEqualTo("USER").andIdentityIdEqualTo(admin.getId());
            List<Permission> permissionList = permissionDao.selectByExample(example);

            return new AdminUserDetails(admin, permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override //通过条件查询出account列表
    public List<AccountItem> queryall(String usernameLike, String emailLike, Integer pageNumber, Integer pageSize, Integer sort) {


        List<Account> accountList = null;

        AccountExample example = new AccountExample();

        if (sort >= 0) {//正序
            example.setOrderByClause(String.format("id limit %d, %d", (pageNumber - 1) * pageSize, pageSize));
//            accountList = accountDao.queryAll(usernameLike, emailLike, pageNumber, pageSize);
        } else {//倒叙
            example.setOrderByClause(String.format("id desc limit %d, %d", (pageNumber - 1) * pageSize, pageSize));
//            accountList = accountDao.queryAllDesc(usernameLike, emailLike, pageNumber, pageSize);
        }
        example.createCriteria()
                .andIsDelEqualTo(0)
                .andUsernameLike("%" + usernameLike + "%")
                .andEmailLike("%" + emailLike + "%");


        List<AccountItem> accountItems = new ArrayList<>();

        List<Account> accounts = accountDao.selectByExample(example);

        if (accounts != null){
            accounts.forEach(c -> {
                accountItems.add(new AccountItem(c.getId(), c.getUsername(), c.getEmail()));
            });
        }

        return accountItems;

    }

    @Override
    public Integer countAll() {
        AccountExample example = new AccountExample();
        //未被删除
        example.createCriteria().andIsDelEqualTo(0);
        Integer count = (int)accountDao.countByExample(example);

        return count;
    }
}
