package org.zhangxujie.konfig.dao;

import org.zhangxujie.konfig.dto.account.InfoRemote;
import org.zhangxujie.konfig.model.Account;
import org.zhangxujie.konfig.model.Group;

import java.util.List;

public interface AccountRemoteService {

    boolean validateToken(String token);

    InfoRemote infoFromToken(String token);

    List<Integer> getGroupIdListByAccountId(Integer accountId);

    List<Account> getUsersByAid(List<Integer> accountIds);

    List<Group> getGroupsByAid(List<Integer> groupIds);

}
