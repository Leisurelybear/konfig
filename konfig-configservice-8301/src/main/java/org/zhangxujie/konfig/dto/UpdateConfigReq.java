/**
 * FileName: UpdateConfigReq
 * Author:   jason
 * Date:     2021/4/10 23:29
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.Data;

//data = {
//        'username': username,
//        'accountId': accountId,
//        'id': configId,
//        'collectionId': collectionId,
//        'cfgName': formdata[0]['value'],
//        'cfgKey': formdata[1]['value'],
//        'cfgValue': formdata[2]['value']
//    };
@Data
public class UpdateConfigReq {

    //用户名
    private String username;
    //用户id
    private Integer accountId;
    //配置id
    private Integer id;
    //配置集id
    private Integer collectionId;
    //配置名
    private String cfgName;
    //配置key
    private String cfgKey;
    //配置值value
    private String cfgValue;


}
