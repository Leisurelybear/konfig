/**
 * FileName: ConfigCollection
 * Author:   jason
 * Date:     2021/5/27 11:22
 * Description: 配置集合
 */
package org.zhangxujie.konfig.dto.external;

import lombok.Data;

import java.util.List;

@Data
public class KonfigCollection {

    //配置集合ID
    private Integer id;

    //配置集合名
    private String collectionName;

    //配置列表
    private List<Konfig> configList;

}
