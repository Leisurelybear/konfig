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

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class AccountQueryRespParam {

    @ApiModelProperty(value = "id",required = true)
    private Integer id;

    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

}
