/**
 * FileName: GetCfgConfigReq
 * Author:   jason
 * Date:     2021/4/9 0:54
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetCfgConfigReq {

    private List<Integer> collectionIds;

    private String nameLike;

    private String keyLike;

    //>=0:正序，否则倒叙
    private Integer sort;

}
