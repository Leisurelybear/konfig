/**
 * FileName: HandleAuditReq
 * Author:   jason
 * Date:     2021/5/21 22:28
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandleAuditReq {

    private Integer auditId;

    private Integer collectionId;

    private Boolean isApproved;

}
