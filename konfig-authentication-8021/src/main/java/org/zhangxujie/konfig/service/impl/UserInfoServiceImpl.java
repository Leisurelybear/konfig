/**
 * FileName: UserInfoServiceImpl
 * Author:   jason
 * Date:     2021/3/20 15:17
 * Description:
 */
package org.zhangxujie.konfig.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.mapper.UserInfoMapper;
import org.zhangxujie.konfig.model.UserInfo;
import org.zhangxujie.konfig.service.UserInfoService;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoDao;

    @Override
    public UserInfo getUserInfoByAccountId(Integer accountId) {
        UserInfo info = null;

        info = userInfoDao.selectByAccountId(accountId);

        return info;
    }

    @Override
    public boolean insertInfo(UserInfo userInfo) {
        userInfoDao.insert(userInfo);
        return true;
    }


}
