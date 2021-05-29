package org.zhangxujie.konfig.service;


import org.zhangxujie.konfig.model.Group;

import java.util.ArrayList;
import java.util.List;

public interface GroupService {

    /**
     * @Description: 通过groupids查询group列表
     * @Param groupIds: id列表
     **/
    List<Group> getGroupsByIds(List<Integer> groupIds);

    /**
     * @Description: 通过用户组名模糊查询
     * @Param name: 用户组名 模糊
     **/
    List<Group> listByName(String name);

    /**
     * @Description: 通过用户组名和创建者查询 列表数量
     * @Param groupNameLike: 用户组名 模糊
     * @Param createAccountId: 用户组创建者ID
     * @return: java.lang.Integer 返回用户组列表数量
     **/
    Integer countList(String groupNameLike, Integer createAccountId);

    /**
     * @Description: 通过条件查询用户组列表
     * @Param groupNameLike: 用户组名 模糊
     * @Param pageNumber: 页码，从1开始
     * @Param pageSize: 每页数量，默认10
     * @Param sort: 排序顺序，-1默认时间倒序
     * @Param createAccountId: 创建者用户ID
     * @return: 返回用户组列表
     **/
    List<Group> list(String groupNameLike, Integer pageNumber, Integer pageSize, Integer sort, Integer createAccountId);

    Integer countByGroupName(String groupName);

    Integer create(String groupName, Integer createAccountId);


    Integer delete(Integer groupId, Integer createAccountId);
}
