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
import org.zhangxujie.konfig.model.Account;
import org.zhangxujie.konfig.model.CfgPermission;
import org.zhangxujie.konfig.model.Group;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPermissionResp {

    private List<CfgPermission> permissionList;

    private List<Account> accountList;

    private List<Group> groupList;

    private Integer pageNumber;

    private Integer pageSize;

    private Integer count;

}
