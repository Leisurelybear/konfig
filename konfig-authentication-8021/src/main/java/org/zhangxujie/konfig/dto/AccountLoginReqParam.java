/**
 * FileName: AccountLoginParam
 * Author:   jason
 * Date:     2021/3/11 1:51
 * Description:
 */
package org.zhangxujie.konfig.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountLoginReqParam {

    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
