/**
 * FileName: AddCollectionReq
 * Author:   jason
 * Date:     2021/4/20 1:16
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteConfigReq {
    private Integer collectionId;
    private Integer configId;

}
