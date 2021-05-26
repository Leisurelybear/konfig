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
import org.zhangxujie.konfig.model.UserInfoExample;
import org.zhangxujie.konfig.service.UserInfoService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoDao;

    @Override
    public UserInfo getUserInfoByAccountId(Integer accountId) {
        UserInfo info = null;

        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andIsDelEqualTo(0)
                .andAccountIdEqualTo(accountId);

        List<UserInfo> userInfoList = userInfoDao.selectByExample(example);
        if (userInfoList != null && userInfoList.size() != 0){
            info = userInfoList.get(0);
        }

        return info;
    }

    @Override
    public boolean insertInfo(UserInfo userInfo) {
        userInfoDao.insert(userInfo);
        return true;
    }


}
