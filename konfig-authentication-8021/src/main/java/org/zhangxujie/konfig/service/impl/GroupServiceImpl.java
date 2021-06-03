/**
 * FileName: GroupServiceImpl
 * Author:   jason
 * Date:     2021/3/16 23:15
 * Description:
 */
package org.zhangxujie.konfig.service.impl;

import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.mapper.GroupMapper;
import org.zhangxujie.konfig.mapper.GroupUserMapper;
import org.zhangxujie.konfig.model.Group;
import org.zhangxujie.konfig.model.GroupExample;
import org.zhangxujie.konfig.model.GroupUser;
import org.zhangxujie.konfig.model.GroupUserExample;
import org.zhangxujie.konfig.service.GroupService;
import org.zhangxujie.konfig.util.TimeUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupMapper groupMapper;

    @Resource
    private GroupUserMapper groupUserMapper;


    @Override
    public List<Group> getGroupsByIds(List<Integer> groupIds) {

        if (groupIds != null && groupIds.size() != 0) {
            GroupExample example = new GroupExample();
            example.createCriteria().andIsDelEqualTo(0)
                    .andIdIn(groupIds);
            return groupMapper.selectByExample(example);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Group> listByName(String name) {
        GroupExample example = new GroupExample();
        example.createCriteria().andIsDelEqualTo(0)
                .andGroupNameLike("%" + name + "%");

        List<Group> groupList = groupMapper.selectByExample(example);
        if (groupList == null) {
            return new ArrayList<>();
        }

        return groupList;
    }

    @Override
    public Integer countList(String groupNameLike, Integer createAccountId) {

        GroupExample example = new GroupExample();
//        example.setOrderByClause("update_time desc");
        GroupExample.Criteria criteria = example.createCriteria().andIsDelEqualTo(0);

        if (groupNameLike != null && !groupNameLike.equals("")) {
            criteria.andGroupNameLike("%" + groupNameLike + "%");
        }

//        if (createAccountId > 0) {
//            criteria.andUpdateAccountIdEqualTo(createAccountId);
//        }

        long count = groupMapper.countByExample(example);

        return (int) count;
    }

    @Override
    public List<Group> list(String groupNameLike, Integer pageNumber, Integer pageSize, Integer sort, Integer createAccountId) {
        GroupExample example = new GroupExample();

        if (sort >= 0) {//正序
            example.setOrderByClause(String.format("update_time limit %d, %d", (pageNumber - 1) * pageSize, pageSize));
//            accountList = accountDao.queryAll(usernameLike, emailLike, pageNumber, pageSize);
        } else {//倒叙
            example.setOrderByClause(String.format("update_time desc limit %d, %d", (pageNumber - 1) * pageSize, pageSize));
//            accountList = accountDao.queryAllDesc(usernameLike, emailLike, pageNumber, pageSize);
        }

        GroupExample.Criteria criteria = example.createCriteria().andIsDelEqualTo(0);

        if (groupNameLike != null && !groupNameLike.equals("")) {
            criteria.andGroupNameLike("%" + groupNameLike + "%");
        }

//        if (createAccountId > 0) {
//            criteria.andUpdateAccountIdEqualTo(createAccountId);
//        }

        List<Group> groupList = groupMapper.selectByExample(example);

        return groupList == null ? new ArrayList<>() : groupList;
    }

    @Override
    public Integer countByGroupName(String groupName) {

        GroupExample example = new GroupExample();
//        example.setOrderByClause("update_time desc");
        GroupExample.Criteria criteria = example.createCriteria().andIsDelEqualTo(0);

        criteria.andGroupNameEqualTo(groupName);

        return (int)(groupMapper.countByExample(example));
    }

    @Override
    public Integer create(String groupName, Integer createAccountId) {

        Group group = new Group();

        group.setIsDel(0);
        group.setGroupName(groupName);
        group.setGroupRole(4);
        group.setRootAccountId(createAccountId);
        group.setUpdateAccountId(createAccountId);
        group.setUpdateTime(TimeUtil.getNowTimestamp());

        groupMapper.insert(group);

        return group.getId();
    }

    @Override
    public Integer delete(Integer groupId, Integer createAccountId) {

        GroupUserExample example = new GroupUserExample();
        example.createCriteria().andIsDelEqualTo(0)
                .andGroupIdEqualTo(groupId);

        Group group = groupMapper.selectByPrimaryKey(groupId);
        if (group.getGroupName().equals("wheel")){//wheel不能删除
            return -1;
        }
        if(!group.getRootAccountId().equals(createAccountId)){
            return -2;
        }

        //删除组和用户关系
        List<GroupUser> groupUserList = groupUserMapper.selectByExample(example);
        if (groupUserList != null && groupUserList.size() != 0){
            groupUserList.forEach(c -> {
                c.setIsDel(1);
                groupUserMapper.updateByPrimaryKey(c);
            });
        }

        //删除组
        group.setIsDel(1);
        groupMapper.updateByPrimaryKey(group);



        return 1;
    }
}
