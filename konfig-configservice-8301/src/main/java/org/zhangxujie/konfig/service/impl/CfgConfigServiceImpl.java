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
import org.zhangxujie.konfig.util.TimeUtil;

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

    @Override
    public boolean update(Integer collectionIdOld, Integer collectionIdNew, Integer configId, String cfgName, String cfgKey, String cfgValue, String username) {
        //如果当前修改的是线上的，则取线下拷贝一份
        if (collectionIdNew == collectionIdOld) {
            //当前修改草稿版本
            //先选择出原始数据
            CfgConfigExample example1 = new CfgConfigExample();
            example1.createCriteria().andIdEqualTo(configId);
            CfgConfig oldConfig = cfgConfigMapper.selectByExampleWithBLOBs(example1).get(0);

            //设为删除
            oldConfig.setIsDel(1);
            oldConfig.setUpdateTime(TimeUtil.getNowTimestamp());

            //更新删除的数据
            cfgConfigMapper.updateByPrimaryKeyWithBLOBs(oldConfig);

            //创建一个引用，把该更改的地方更改一下再插入表
            CfgConfig newConfig = oldConfig;
            newConfig.setId(null);
            newConfig.setIsDel(0);
            newConfig.setCfgName(cfgName);
            newConfig.setCollectionId(collectionIdNew);
            newConfig.setCfgKey(cfgKey);
            newConfig.setCfgValue(cfgValue);
            newConfig.setUpdateUsername(username);
            newConfig.setUpdateTime(TimeUtil.getNowTimestamp());
            cfgConfigMapper.insert(newConfig);
        } else {
            //当前修改线上版本，所以要拷贝一份到新的collection

            //先完整拷贝一份
            CfgConfigExample example = new CfgConfigExample();
            example.createCriteria().andCollectionIdEqualTo(collectionIdOld).andIsDelEqualTo(0);
            List<CfgConfig> oldCfgConfigs =  cfgConfigMapper.selectByExampleWithBLOBs(example);
            oldCfgConfigs.forEach(c -> {
                if (c.getId() == configId){
                    //是修改的配置，修改
                    c.setCfgName(cfgName);
                    c.setCfgKey(cfgKey);
                    c.setCfgValue(cfgValue);
                }
                c.setId(null);
                c.setCollectionId(collectionIdNew);
                c.setIsDraft(1);
                cfgConfigMapper.insert(c);
            });
        }//else

        return true;
    }

    @Override
    public CfgConfig add(Integer collectionIdOld, Integer collectionIdNew, String cfgName, String cfgKey, String cfgValue, String username) {
        //如果当前修改的是线上的，则取线下拷贝一份
        if (collectionIdNew == collectionIdOld) {
            //当前修改草稿版本

            //创建一个配置，加入数据库
            CfgConfig newConfig = new CfgConfig();
            newConfig.setIsDel(0);
            newConfig.setCollectionId(collectionIdNew);
            newConfig.setCfgName(cfgName);
            newConfig.setCfgKey(cfgKey);
            newConfig.setCfgValue(cfgValue);
            newConfig.setIsDraft(1);
            newConfig.setCreateUsername(username);
            newConfig.setCreateTime(TimeUtil.getNowTimestamp());
            newConfig.setUpdateUsername(username);
            newConfig.setUpdateTime(TimeUtil.getNowTimestamp());
            cfgConfigMapper.insert(newConfig);
            return newConfig;
        } else {
            //当前修改线上版本，所以要拷贝一份到新的collection为草稿版本

            //先完整拷贝一份
            CfgConfigExample example = new CfgConfigExample();
            example.createCriteria().andCollectionIdEqualTo(collectionIdOld).andIsDelEqualTo(0);
            List<CfgConfig> oldCfgConfigs =  cfgConfigMapper.selectByExampleWithBLOBs(example);
            oldCfgConfigs.forEach(c -> {
                c.setId(null);
                c.setCollectionId(collectionIdNew);
                c.setIsDraft(1);
                cfgConfigMapper.insert(c);
            });

            //然后再添加一份
            CfgConfig newConfig = new CfgConfig();
            newConfig.setIsDel(0);
            newConfig.setCollectionId(collectionIdNew);
            newConfig.setCfgName(cfgName);
            newConfig.setCfgKey(cfgKey);
            newConfig.setCfgValue(cfgValue);
            newConfig.setIsDraft(1);
            newConfig.setCreateUsername(username);
            newConfig.setCreateTime(TimeUtil.getNowTimestamp());
            newConfig.setUpdateUsername(username);
            newConfig.setUpdateTime(System.currentTimeMillis());
            cfgConfigMapper.insert(newConfig);
            return newConfig;
        }//else

    }

    @Override
    public int delete(Integer configId, String usernameFromToken) {

        CfgConfig cfgConfig = cfgConfigMapper.selectByPrimaryKey(configId);

        cfgConfig.setIsDel(1);//删除
        cfgConfig.setUpdateTime(TimeUtil.getNowTimestamp());
        cfgConfig.setUpdateUsername(usernameFromToken);

        int status = cfgConfigMapper.updateByPrimaryKeyWithBLOBs(cfgConfig);

        return status;
    }
}
