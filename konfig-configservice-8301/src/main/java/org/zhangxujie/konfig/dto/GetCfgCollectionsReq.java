/**
 * FileName: GetCfgCollectionsReq
 * Author:   jason
 * Date:     2021/4/5 20:54
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.Data;

@Data
public class GetCfgCollectionsReq {

    private String nameLike;

    //>=0:正序，否则倒叙
    private Integer sort;

    private Integer nums;

    private Integer pageNum;

    private Boolean isDraft;

}
