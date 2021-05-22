/**
 * FileName: ConfigPermissionService
 * Author:   jason
 * Date:     2021/5/20 16:51
 * Description:
 */
package org.zhangxujie.konfig.service;

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
    Integer addUserPermission(Integer accountId, Integer collectionId, String createUsername, Integer createAccountId);


    /**
     * @Author: Jason
     * @Description: 对用户组授权
     * @Date: 2021/5/20 23:07
     * @Param groupId:
     * @Param collectionId:
     * @Param createUsername:
     * @Param createAccountId:
     * @return: java.lang.Integer
     **/
    Integer addGroupPermission(Integer groupId, Integer collectionId, String createUsername, String createAccountId);


}
