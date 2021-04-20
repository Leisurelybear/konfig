/**
 * FileName: AccountService
 * Author:   jason
 * Date:     2021/3/10 18:48
 * Description:
 */
package org.zhangxujie.konfig.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.zhangxujie.konfig.dto.AccountItem;
import org.zhangxujie.konfig.dto.AccountQueryRespParam;
import org.zhangxujie.konfig.dto.AccountRegisterParam;
import org.zhangxujie.konfig.model.Account;

import java.util.List;

public interface AccountService {

    public Account getAdminByUsername(String username);

    public Account register(AccountRegisterParam account);

    public String login(String username, String password);

    public UserDetails loadUserByUsername(String username);

    List<AccountItem> queryall(String usernameLike, String emailLike, Integer pageNumber, Integer pageSize, Integer sort);

    //查看总人数
    Integer countAll();
}
