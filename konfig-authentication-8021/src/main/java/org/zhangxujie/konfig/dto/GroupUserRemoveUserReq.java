/**
 * FileName: GroupUserRemoveUserReq
 * Author:   jason
 * Date:     2021/5/29 10:10
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.Data;

@Data
public class GroupUserRemoveUserReq {

    private Integer id;
    private Integer groupId;
    private Integer accountId;

}
