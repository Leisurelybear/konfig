package org.zhangxujie.konfig.dao;

import org.zhangxujie.konfig.dto.account.InfoRemote;

import java.util.List;

public interface AccountRemoteDAO {

    boolean validateToken(String token);

    InfoRemote infoFromToken(String token);

    List<Integer> getGroupIdListByAccountId(Integer accountId);

}
