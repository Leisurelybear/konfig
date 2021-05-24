/**
 * FileName: ListPermisiionReq
 * Author:   jason
 * Date:     2021/5/24 14:27
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePermissionReq {

    private Integer collectionId;

    private List<Integer> accountIds;

    private List<Integer> groupIds;

}
