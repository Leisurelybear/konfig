/**
 * FileName: CfgConfigServiceImpl
 * Author:   jason
 * Date:     2021/4/9 1:18
 * Description:
 */
package org.zhangxujie.konfig.service.impl;

import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.mapper.CfgConfigMapper;
import org.zhangxujie.konfig.model.CfgConfig;
import org.zhangxujie.konfig.model.CfgConfigExample;
import org.zhangxujie.konfig.service.CfgConfigService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CfgConfigServiceImpl implements CfgConfigService {

    @Resource
    private CfgConfigMapper cfgConfigMapper;

    @Override
    public List<CfgConfig> query(List<Integer> collectionIds, String nameLike, String keyLike, Integer sort) {

        List<CfgConfig> res;
        nameLike = "%" + nameLike + "%";
        keyLike = "%" + keyLike + "%";

        //如果不通过collectionId查询
        if (collectionIds == null || collectionIds.size() == 0) {
            CfgConfigExample example = new CfgConfigExample();
            example.createCriteria().andIsDelEqualTo(0).andCfgNameLike(nameLike).andCfgKeyLike(keyLike);
            example.setOrderByClause("id");
            res = cfgConfigMapper.selectByExample(example);
            return res;
        }

        //TODO:查不到value值，可能是数据库数据类型原因，还有去过取消下面注释，查不到数据
        CfgConfigExample example2 = new CfgConfigExample();
        example2.createCriteria()
                .andCollectionIdIn(collectionIds)
                .andIsDelEqualTo(0)
                .andCfgNameLike(nameLike)
                .andCfgKeyLike(keyLike);


        if (sort >= 0) {
            example2.setOrderByClause("id ASC");
        } else {
            example2.setOrderByClause("id DESC");
        }
        res = cfgConfigMapper.selectByExampleWithBLOBs(example2);


        return res;
    }
}
