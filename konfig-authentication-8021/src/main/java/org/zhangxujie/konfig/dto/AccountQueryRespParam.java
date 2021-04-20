/**
 * FileName: AccountQueryRespParam
 * Author:   jason
 * Date:     2021/3/23 23:54
 * Description:
 */
package org.zhangxujie.konfig.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class AccountQueryRespParam {

    //当前页数
    private Integer pageNum;

    //每页数据数
    private Integer pageSize;

    //总共数据数
    private Integer count;

    //当前用户列表
    private List<AccountItem> userList;

}

