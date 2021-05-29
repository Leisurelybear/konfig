/**
 * FileName: AccountQueryRespParam
 * Author:   jason
 * Date:     2021/3/23 23:54
 * Description:
 */
package org.zhangxujie.konfig.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zhangxujie.konfig.model.Group;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class GroupListRespParam {

    //当前页数
    private Integer pageNum;

    //每页数据数
    private Integer pageSize;

    //总共数据数
    private Integer count;

    //当前用户创建的用户组列表
    private List<GroupItem> groupList;

}

