/**
 * FileName: PermissionService
 * Author:   jason
 * Date:     2021/3/10 17:35
 * Description:
 */
package org.zhangxujie.konfig.service.impl;

import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.mapper.PermissionMapper;
import org.zhangxujie.konfig.model.Permission;
import org.zhangxujie.konfig.model.PermissionExample;
import org.zhangxujie.konfig.service.PermissionService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionDao;

    @Override
    public List<Permission> getPermissionList(Integer accountId, List<Integer> groupIds) {
        //根据accountId 获取该用户的所有权限

        List<Permission> permissionList = null;

        PermissionExample example = new PermissionExample();
        //1、选出用户个人权限
        example.createCriteria().andIdentityTypeEqualTo("USER").andIdentityIdEqualTo(accountId);

        //2、选出用户组权限
        if (groupIds != null && groupIds.size() != 0){
            example.or().andIdentityTypeEqualTo("GROUP").andIdentityIdIn(groupIds);
        }

        //查询出权限
        permissionList = permissionDao.selectByExample(example);


        return permissionList;
    }
}
