/**
 * FileName: GetPermResp
 * Author:   jason
 * Date:     2021/4/4 15:55
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
public class GetPermResp {

    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "拥有的权限，包括所在组和个人",required = true)
    private List<String> perms;


}
