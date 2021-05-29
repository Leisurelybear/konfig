/**
 * FileName: UserServiceImpl
 * Author:   jason
 * Date:     2021/3/16 23:20
 * Description:
 */
package org.zhangxujie.konfig.service.impl;

import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.mapper.GroupUserMapper;
import org.zhangxujie.konfig.model.GroupUser;
import org.zhangxujie.konfig.model.GroupUserExample;
import org.zhangxujie.konfig.service.GroupUserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupUserServiceImpl implements GroupUserService {

    @Resource
    private GroupUserMapper groupUserDao;

    @Override
    public List<Integer> getGroupIdsByAccountId(Integer accountId) {

        GroupUserExample example = new GroupUserExample();
        example.createCriteria().andAccountIdEqualTo(accountId);

        List<GroupUser> groupUserList = groupUserDao.selectByExample(example);
        List<GroupUser> step1 = null;
        if (groupUserList != null) {
            step1 = groupUserList.stream()
                    .filter(u -> u.getIsDel() == 0)
                    .collect(Collectors.toList());
        }

        //得到有效的数据中，当前用户所在的所有组


        //从有效的所有组，取出所有的组ID
        List<Integer> groupIds = null;
        if (step1 != null) {
            groupIds = step1.stream().map(GroupUser::getGroupId).collect(Collectors.toList());
        }

        return groupIds;
    }

    @Override
    public List<GroupUser> list(Integer groupId) {

        GroupUserExample example = new GroupUserExample();
        example.createCriteria().andIsDelEqualTo(0)
                .andGroupIdEqualTo(groupId);

        List<GroupUser> groupUserList = groupUserDao.selectByExample(example);

        return groupUserList == null ? new ArrayList<>() : groupUserList;
    }
}
