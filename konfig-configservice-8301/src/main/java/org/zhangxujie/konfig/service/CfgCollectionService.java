/**
 * FileName: CfgCollectionService
 * Author:   jason
 * Date:     2021/4/5 20:43
 * Description:
 */
package org.zhangxujie.konfig.service;

import org.zhangxujie.konfig.dto.AddCollectionReq;
import org.zhangxujie.konfig.dto.AddCollectionResp;
import org.zhangxujie.konfig.dto.DeleteCollectionReq;
import org.zhangxujie.konfig.dto.DeleteCollectionResp;
import org.zhangxujie.konfig.model.CfgCollection;

import java.util.List;

public interface CfgCollectionService {

    List<CfgCollection> query(String collectionNameLike, Integer sort, Integer pageNum, Integer nums);


    Integer setToDraft(Integer collectionId, String username);

    AddCollectionResp add(AddCollectionReq req, String username);

    CfgCollection queryById(Integer id);

    DeleteCollectionResp delete(DeleteCollectionReq req, String username);

    //查看上线状态
    boolean isOnline(Integer collectionId);
}
