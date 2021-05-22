/**
 * FileName: GetUndoAuditListResp
 * Author:   jason
 * Date:     2021/5/22 16:00
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUndoAuditListResp {

    private Integer id;

    private Integer cfgCollectionId;

    private Integer applicantAid;

    private Long submitTime;

    private String content;
}
