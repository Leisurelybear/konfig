package org.zhangxujie.konfig.service;

import org.zhangxujie.konfig.model.CfgConfig;

import java.util.List;

public interface CfgConfigService {

    List<CfgConfig> query(List<Integer> collectionIds, String nameLike, String keyLike, Integer sort);

}
