/**
 * FileName: AccountItem
 * Author:   jason
 * Date:     2021/4/18 17:44
 * Description:
 */
package org.zhangxujie.konfig.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class AccountItem {
    @ApiModelProperty(value = "id",required = true)
    private Integer id;

    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;
}
