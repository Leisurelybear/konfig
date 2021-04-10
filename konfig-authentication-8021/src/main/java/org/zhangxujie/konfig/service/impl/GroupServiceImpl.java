/**
 * FileName: GroupServiceImpl
 * Author:   jason
 * Date:     2021/3/16 23:15
 * Description:
 */
package org.zhangxujie.konfig.service.impl;

import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.model.Group;
import org.zhangxujie.konfig.service.GroupService;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Override
    public List<Group> getGroupsByIds(List<Integer> groupIds) {

        return null;
    }
}
