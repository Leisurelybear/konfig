package org.zhangxujie.konfig.service;

import java.util.List;

public interface GroupUserService {

    List<Integer> getGroupIdsByAccountId(Integer accountId);

}
