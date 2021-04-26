package org.zhangxujie.konfig.service;

import org.zhangxujie.konfig.model.CfgConfig;

import java.util.List;

public interface CfgConfigService {

    List<CfgConfig> query(List<Integer> collectionIds, String nameLike, String keyLike, Integer sort);

    //如果更新前是草稿，则collectionId不变，否则变
    boolean update(Integer collectionIdOld, Integer collectionIdNew, Integer configId, String cfgName, String cfgKey, String cfgValue, String username);

    CfgConfig add(Integer collectionIdOld, Integer collectionIdNew, String cfgName, String cfgKey, String cfgValue, String username);

    int delete(Integer configId, String usernameFromToken);
}
