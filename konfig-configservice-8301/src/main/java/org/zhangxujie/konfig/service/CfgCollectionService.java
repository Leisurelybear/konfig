/**
 * FileName: CfgCollectionService
 * Author:   jason
 * Date:     2021/4/5 20:43
 * Description:
 */
package org.zhangxujie.konfig.service;

import org.zhangxujie.konfig.dto.AddCollectionReq;
import org.zhangxujie.konfig.dto.AddCollectionResp;
import org.zhangxujie.konfig.model.CfgCollection;

import java.util.List;

public interface CfgCollectionService {

    List<CfgCollection> query(String collectionNameLike, Integer sort, Integer pageNum, Integer nums);


    Integer setToDraft(Integer collectionId, String cName, String username);

    AddCollectionResp add(AddCollectionReq req, String username);

    CfgCollection queryById(Integer id);
}
