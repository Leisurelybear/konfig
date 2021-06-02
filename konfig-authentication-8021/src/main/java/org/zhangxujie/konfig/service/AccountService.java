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
import org.zhangxujie.konfig.model.Group;

import java.util.List;

public interface AccountService {

    /**
     * @Author: Jason
     * @Description: 通过用户名查找用户
     * @Date: 2021/5/23 16:45
     * @Param username: 唯一的用户名
     * @return: org.zhangxujie.konfig.model.Account
     **/
    Account getAdminByUsername(String username);

    /**
     * @Author: Jason
     * @Description: 注册用户
     * @Date: 2021/5/23 16:46
     * @Param account: 用户信息
     * @return: org.zhangxujie.konfig.model.Account
     **/
    Account register(AccountRegisterParam account);

    /**
     * @Author: Jason
     * @Description:
     * @Date: 2021/5/23 16:46
     * @Param username: 登录用户名
     * @Param password: 登录密码
     * @return: java.lang.String 返回用户登录token
     **/
    String login(String username, String password);

    /**
     * @Author: Jason
     * @Description: 通过用户名加载UserDetail，用于鉴权
     * @Date: 2021/5/23 16:46
     * @Param username: 用户名
     * @return: org.springframework.security.core.userdetails.UserDetails
     **/
    UserDetails loadUserByUsername(String username);

    /**
     * @Author: Jason
     * @Description: 分页查询用户信息
     * @Date: 2021/5/23 16:47
     * @Param usernameLike: 用户名模糊查询
     * @Param emailLike: email模糊查询
     * @Param pageNumber: 页码
     * @Param pageSize: 每页数量
     * @Param sort: 排序方向
     * @return: java.util.List<org.zhangxujie.konfig.dto.AccountItem>
     **/
    List<AccountItem> queryall(String usernameLike, String emailLike, Integer pageNumber, Integer pageSize, Integer sort);

    //查看总人数
    Integer countAll();

    /**
     * @Author: Jason
     * @Description: 通过用户ID列表查询用户列表
     * @Date: 2021/5/23 17:25
     * @Param accountIds: 用户ID列表
     * @return: java.util.List<org.zhangxujie.konfig.model.Account>
     **/
    List<Account> listByAids(List<Integer> accountIds);


    List<Account> listByName(String name);

    //查重
    Integer dup(AccountRegisterParam accountRegisterParam);

    boolean changePassword(Integer accountId, String password);
}
