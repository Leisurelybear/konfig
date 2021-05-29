/**
 * FileName: GroupItem
 * Author:   jason
 * Date:     2021/5/29 10:32
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.Data;

@Data
public class GroupItem {
    private Integer id;

    private String groupName;

    private Integer groupRole;

    private Integer rootAccountId;
    private String rootAccountUsername;

    private Long updateTime;

    private Integer updateAccountId;
}
