/**
 * FileName: GetCfgConfigResp
 * Author:   jason
 * Date:     2021/4/10 18:48
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.Data;
import org.zhangxujie.konfig.model.CfgConfig;

import java.util.List;

@Data
public class GetCfgConfigResp {
    //页码
    private Integer pageNum;

    //confs数量
    private Integer num;

    //配置列表
    private List<CfgConfig> confs;

    //所在配置集的名字
    private String collectionName;

    //是否糟糕版本
    private Integer isDraft;

    private Integer collectionId;
}
