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
import org.zhangxujie.konfig.common.Const;
import org.zhangxujie.konfig.common.LogUtil;
import org.zhangxujie.konfig.db.RedisClient;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private RedisClient redisClient;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private LogUtil opLog;


    public Account getAdminByUsername(String username) {
        String redisKeyPrefix = "account:name:";
        Object redisAccount = redisClient.getObject(redisKeyPrefix + username);
        if (redisAccount != null) { //??????redis?????????????????????
            log.info("getAdminByUsername via redis");
            return ((Account) redisAccount);
        }

        AccountExample example = new AccountExample();
        example.createCriteria().andUsernameEqualTo(username);

        List<Account> accountList = accountDao.selectByExample(example);
        if (accountList.size() < 1) {
            return null;
        }
        log.info("getAdminByUsername via mysql");

        redisClient.setObject(redisKeyPrefix + username, accountList.get(0));
        return accountList.get(0);
    }


    //????????????
    public Account register(AccountRegisterParam formAccount) {

        Account account = new Account();

        AccountExample exampleUsernameEqual = new AccountExample();
        exampleUsernameEqual.createCriteria().andIsDelEqualTo(0).andUsernameEqualTo(formAccount.getUsername());

        //????????????????????????
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
        //?????????????????????

        //?????????????????????
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

        //??????????????????
        account.setPassword("");

        opLog.insert(Const.LOG_OPTYPE_USER, "????????????", "", account.toString(), "", 0);

        return account;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //????????????????????????????????????
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                log.error("???????????????????????????");
                return token;
            }
            if (!userDetails.isEnabled()) {
                log.error("???????????????????????????");
                return token;
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);

//            insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("????????????:{}", e.getMessage());
        }

        opLog.insert(Const.LOG_OPTYPE_USER, "????????????", "", "username: " + username, username, 0);


        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        String redisKeyPrefix = "permission:account:id:";

        //??????????????????
        Account admin = getAdminByUsername(username);
        if (admin != null) {
            List<Permission> permissionList = null;

            Object permissionsRedis = redisClient.getObject(redisKeyPrefix + admin.getId());
            if (permissionsRedis != null) {
                permissionList = (List<Permission>) permissionsRedis;
            } else {
                PermissionExample example = new PermissionExample();
                example.createCriteria().andIdentityTypeEqualTo("USER").andIdentityIdEqualTo(admin.getId());
                permissionList = permissionDao.selectByExample(example);
                redisClient.setObject(redisKeyPrefix + admin.getId(), permissionList);
            }

            return new AdminUserDetails(admin, permissionList);
        }
        throw new UsernameNotFoundException("????????????????????????");
    }

    @Override //?????????????????????account??????
    public List<AccountItem> queryall(String usernameLike, String emailLike, Integer pageNumber, Integer pageSize, Integer sort) {


        List<Account> accountList = null;

        AccountExample example = new AccountExample();

        if (sort >= 0) {//??????
            example.setOrderByClause(String.format("id limit %d, %d", (pageNumber - 1) * pageSize, pageSize));
//            accountList = accountDao.queryAll(usernameLike, emailLike, pageNumber, pageSize);
        } else {//??????
            example.setOrderByClause(String.format("id desc limit %d, %d", (pageNumber - 1) * pageSize, pageSize));
//            accountList = accountDao.queryAllDesc(usernameLike, emailLike, pageNumber, pageSize);
        }
        example.createCriteria()
                .andIsDelEqualTo(0)
                .andUsernameLike("%" + usernameLike + "%")
                .andEmailLike("%" + emailLike + "%");


        List<AccountItem> accountItems = new ArrayList<>();

        List<Account> accounts = accountDao.selectByExample(example);

        if (accounts != null) {
            accounts.forEach(c -> {
                accountItems.add(new AccountItem(c.getId(), c.getUsername(), c.getEmail()));
            });
        }

        return accountItems;

    }

    @Override
    public Integer countAll() {
        AccountExample example = new AccountExample();
        //????????????
        example.createCriteria().andIsDelEqualTo(0);
        Integer count = (int) accountDao.countByExample(example);

        return count;
    }

    @Override
    public List<Account> listByAids(List<Integer> accountIds) {
        //redis??????
        String RedisKeyPrefix = "account:id:";

        //??????
        Set<Integer> set = new HashSet<>(accountIds);
        accountIds.clear();
        accountIds.addAll(set);

        //?????????????????????list
        List<Account> accountList = new ArrayList<>();
        //???redis??????????????????id
        List<Integer> misAccountIds = new ArrayList<>();
        for (Integer accountId : accountIds) {
            Object obj = redisClient.getObject(RedisKeyPrefix + accountId);
            if (obj == null) {
                misAccountIds.add(accountId);
                continue;
            }
            accountList.add((Account) obj);
        }

        if (misAccountIds.size() != 0) {
            AccountExample example = new AccountExample();
            example.createCriteria()
                    .andIsDelEqualTo(0)
                    .andIdIn(misAccountIds);
            List<Account> misAccountList = accountDao.selectByExample(example);
            //???redis?????????????????????
            misAccountList.forEach(c -> {
                accountList.add(c);
                redisClient.setObject(RedisKeyPrefix + c.getId(), c);
            });
        }

        return accountList;
    }

    @Override
    public List<Account> listByName(String name) {

        AccountExample example = new AccountExample();

        example.createCriteria().andIsDelEqualTo(0)
                .andUsernameLike("%" + name + "%");

        List<Account> accounts = accountDao.selectByExample(example);
        if (accounts == null) {
            return new ArrayList<>();
        }
        for (Account account : accounts) {
            account.setPassword("");
        }

        return accounts;
    }

    @Override
    public Integer dup(AccountRegisterParam accountRegisterParam) {

        AccountExample exampleUsernameEqual = new AccountExample();
        exampleUsernameEqual.createCriteria().andIsDelEqualTo(0).andUsernameEqualTo(accountRegisterParam.getUsername());

        //????????????????????????
        long c = accountDao.countByExample(exampleUsernameEqual);

        return (int) c;
    }

    @Override
    public boolean changePassword(Integer accountId, String password) {

        Account account = accountDao.selectByPrimaryKey(accountId);

        if (account == null) {
            return false;
        }

        String encodePassword = passwordEncoder.encode(password);
        account.setPassword(encodePassword);
        accountDao.updateByPrimaryKey(account);

        redisClient.delete("account:id:" + account.getId());
        redisClient.delete("account:name:" + account.getUsername());

        opLog.insert(Const.LOG_OPTYPE_USER, "??????????????????", "", "accountid???" + accountId, "root", 1);


        return true;
    }
}
