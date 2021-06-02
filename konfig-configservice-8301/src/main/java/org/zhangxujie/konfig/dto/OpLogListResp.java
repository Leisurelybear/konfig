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
import org.zhangxujie.konfig.model.OpLog;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpLogListResp {

    private List<OpLog> opLogList;

    private String type;

    private Integer pageNumber;

    private Integer pageSize;

    private Integer count;

}
