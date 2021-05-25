/**
 * FileName: GroupServiceImpl
 * Author:   jason
 * Date:     2021/3/16 23:15
 * Description:
 */
package org.zhangxujie.konfig.service.impl;

import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.mapper.GroupMapper;
import org.zhangxujie.konfig.model.Group;
import org.zhangxujie.konfig.model.GroupExample;
import org.zhangxujie.konfig.service.GroupService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupMapper groupMapper;

    @Override
    public List<Group> getGroupsByIds(List<Integer> groupIds) {

        if (groupIds != null && groupIds.size() != 0){
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
        if (groupList == null){
            return new ArrayList<>();
        }

        return groupList;
    }
}
