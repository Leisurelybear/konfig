/**
 * FileName: UserInfoService
 * Author:   jason
 * Date:     2021/3/20 15:16
 * Description:
 */
package org.zhangxujie.konfig.service;


import org.zhangxujie.konfig.model.UserInfo;

public interface UserInfoService {

    UserInfo getUserInfoByAccountId(Integer accountId);

    boolean insertInfo(UserInfo userInfo);

}
