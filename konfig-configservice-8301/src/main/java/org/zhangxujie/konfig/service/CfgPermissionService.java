/**
 * FileName: ConfigPermissionService
 * Author:   jason
 * Date:     2021/5/20 16:51
 * Description:
 */
package org.zhangxujie.konfig.service;

import org.zhangxujie.konfig.dto.ListPermissionResp;
import org.zhangxujie.konfig.model.CfgPermission;

import java.util.List;

public interface CfgPermissionService {

    /**
     * @Description: 通过token检测用户和所在的用户组是否有权限编辑对应collectionId的配置
     * @Param token: 用户token
     * @Param collectionId: 配置集合的ID
     * @return: boolean 是否有权限操作
     **/
    boolean hasPermission(String token, Integer collectionId);


    /**
     * @Description: 通过用户token 返回用户有权限的配置集ID列表
     * @Param token: 用户token
     * @return: java.util.List<java.lang.Integer> 配置集合的id列表
     **/
    List<Integer> getPermissionCollectionIdList(String token);


    /**
     * @Description: 通过配置，查询该配置集合有权限的用户ID列表
     * @Param collectionId: 配置集合ID
     * @return: java.util.List<java.lang.Integer> 用户ID列表
     **/
    List<Integer> getPermissionUserIdList(Integer collectionId);


    /**
     * @Description: 通过用户组ID，获取有权限的配置集合ID
     * @Param groupId:
     * @return: java.util.List<java.lang.Integer>
     **/
    List<Integer> getPermissionCollectionIdListByGroupId(Integer groupId);


    /**
     * @Description: 对用户授权
     * @Param accountId:
     * @Param collectionId:
     * @Param createUsername:
     * @Param createAccountId:
     * @return: java.lang.Integer
     **/
    Integer initCreateUserPermission(Integer accountId, Integer collectionId, String createUsername, Integer createAccountId);


    /**
     * @Author: Jason
     * @Description: 通过用户ID、集合ID、用户组ID查询权限列表（分页）
     * @Param accountIds: 用户ID
     * @Param collectionIds: 配置集ID
     * @Param groupsIds: 用户组ID
     * @Param pageNumber: 页码
     * @Param pageSize: 每页数量
     * @return: org.zhangxujie.konfig.dto.ListPermissionResp
     **/
    ListPermissionResp list(List<Integer> accountIds, List<Integer> collectionIds, List<Integer> groupsIds, Integer pageNumber, Integer pageSize);

    //創建用戶-配置權限
    boolean createUserPermission(String createUsername, Integer createAccountId, Integer collectionId, List<Integer> accountIds);
    //創建用戶組-配置權限
    boolean createGroupPermission(String createUsername, Integer createAccountId, Integer collectionId, List<Integer> groupIds);

    //删除权限
    boolean remove(Integer cfgPermissionId, Integer accountId);

    CfgPermission getById(Integer cfgPermissionId);

    boolean removeByGroupId(Integer groupId, Integer accountId);
}
