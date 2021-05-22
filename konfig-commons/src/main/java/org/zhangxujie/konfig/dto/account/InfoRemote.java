/**
 * FileName: InfoRemote
 * Author:   jason
 * Date:     2021/5/20 13:06
 * Description:
 */
package org.zhangxujie.konfig.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoRemote implements Serializable {

    @ApiModelProperty(value = "用户唯一id",required = true)
    private Integer accountId;

    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

    @ApiModelProperty(value = "昵称",required = true)
    private String nickname;

    @ApiModelProperty(value = "电话",required = true)
    private String phone;

    @ApiModelProperty(value = "头像url",required = true)
    private String imgUrl;

    @ApiModelProperty(value = "额外信息",required = true)
    private String extra;

    @ApiModelProperty(value = "资料更新时间",required = true)
    private Long updateTime;

    @ApiModelProperty(value = "上次登录时间",required = true)
    private Long lastAccessedTime;



}
