/**
 * FileName: AccountQueryReqParam
 * Author:   jason
 * Date:     2021/3/23 23:44
 * Description:
 */
package org.zhangxujie.konfig.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountQueryReqParam {

    @ApiModelProperty(value = "用户名,模糊查询",required = true)
    private String usernameLike;

    @ApiModelProperty(value = "邮箱，模糊查询",required = true)
    private String emailLike;

    @ApiModelProperty(value = "页码，从0开始",required = true)
    private Integer pageNumber;

    @ApiModelProperty(value = "每页数量",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "升序或降序",required = true, notes = ">=0:正序，<0：逆序")
    private Integer sort;


}
