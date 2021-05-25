package org.zhangxujie.konfig.service;


import org.zhangxujie.konfig.model.Group;

import java.util.ArrayList;
import java.util.List;

public interface GroupService {

    List<Group> getGroupsByIds(List<Integer> groupIds);

    List<Group> listByName(String name);
}
