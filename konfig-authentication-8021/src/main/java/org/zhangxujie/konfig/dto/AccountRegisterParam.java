/**
 * FileName: AccountRegisterParam
 * Author:   jason
 * Date:     2021/3/11 1:54
 * Description:
 */
package org.zhangxujie.konfig.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountRegisterParam {
    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

//    @ApiModelProperty(value = "昵称")
//    private String nickname;
//
//    @ApiModelProperty(value = "电话")
//    private String phone;
//
//    @ApiModelProperty(value = "额外资料")
//    private String extra;
}
