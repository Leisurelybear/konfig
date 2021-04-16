/**
 * FileName: UpdateConfigResp
 * Author:   jason
 * Date:     2021/4/16 2:07
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateConfigResp {

    //返回刷新的集合ID
    private Integer collectionId;

}
