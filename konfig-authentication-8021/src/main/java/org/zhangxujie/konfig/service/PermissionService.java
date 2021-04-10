package org.zhangxujie.konfig.service;


import org.zhangxujie.konfig.model.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> getPermissionList(Integer accountId, List<Integer> groupIds);

}
