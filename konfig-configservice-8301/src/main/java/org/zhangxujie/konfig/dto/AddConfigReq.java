/**
 * FileName: UpdateConfigReq
 * Author:   jason
 * Date:     2021/4/10 23:29
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.Data;

//data = {
//        'configName': configName,
//        'configKey': key,
//        'configValue': value,
//        'collectionId': collectionId,
//    };

@Data
public class AddConfigReq {

    //配置集id
    private Integer collectionId;
    //配置名
    private String configName;
    //配置key
    private String configKey;
    //配置值value
    private String configValue;

}
