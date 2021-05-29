package org.zhangxujie.konfig.service;

import org.zhangxujie.konfig.model.GroupUser;

import java.util.List;

public interface GroupUserService {

    List<Integer> getGroupIdsByAccountId(Integer accountId);

    List<GroupUser> list(Integer groupId);
}
