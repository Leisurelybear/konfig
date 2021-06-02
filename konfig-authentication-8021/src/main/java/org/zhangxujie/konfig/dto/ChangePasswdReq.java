/**
 * FileName: ChangePasswdReq
 * Author:   jason
 * Date:     2021/6/2 14:00
 * Description:
 */
package org.zhangxujie.konfig.dto;

import lombok.Data;

@Data
public class ChangePasswdReq {

    private Integer accountId;
    private String password;
}
