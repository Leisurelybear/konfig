/**
 * FileName: ConfigPushData
 * Author:   jason
 * Date:     2021/6/3 15:47
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.Data;

@Data
public class ConfigPushData {

    // 推送时间戳
    private Long timestamp;

    //推送的配置集ID
    private Integer id;

    //配置集合的状态，0=正常，1=草稿版本
    private Integer status;

}
