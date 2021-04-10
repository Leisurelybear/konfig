/**
 * FileName: CfgCollectionServiceImpl
 * Author:   jason
 * Date:     2021/4/5 20:44
 * Description:
 */
package org.zhangxujie.konfig.service.impl;

import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.mapper.CfgCollectionMapper;
import org.zhangxujie.konfig.model.CfgCollection;
import org.zhangxujie.konfig.model.CfgCollectionExample;
import org.zhangxujie.konfig.service.CfgCollectionService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CfgCollectionServiceImpl implements CfgCollectionService {

    @Resource
    private CfgCollectionMapper cfgCollectionMapper;

    /**
     * @Author: Jason
     * @Description:
     * @Date: 2021/4/11 1:24
     * @Param collectionNameLike: 模糊查询条件：集合名
     * @Param sort: 排序 >=0正序，否则倒序
     * @Param pageNum: 页码
     * @Param nums: 每页的数量
     * @return: java.util.List<org.zhangxujie.konfig.model.CfgCollection>
     **/
    @Override
    public List<CfgCollection> query(String collectionNameLike, Integer sort, Integer pageNum, Integer nums) {

        List<CfgCollection> cfgCollectionList;

        if (sort >= 0) {
            cfgCollectionList = cfgCollectionMapper.queryAll(collectionNameLike, pageNum, nums);
        } else {
            cfgCollectionList = cfgCollectionMapper.queryAllDesc(collectionNameLike, pageNum, nums);
        }

        return cfgCollectionList;
    }

    /**
     * @Author: Jason
     * @Description:
     * @Date: 2021/4/11 1:23
     * @Param collectionId: 配置集合id
     * @return: java.lang.Integer
     **/
    @Override
    public Integer setToDraft(Integer collectionId, String cName, String username) {
        List<CfgCollection> cfgCollectionList = null;
        int draft_id = collectionId;//草稿版本的自增id

        //先通过collection_id找是否有存在的草稿版本
        CfgCollectionExample example = new CfgCollectionExample();
        example.createCriteria()
                .andCollectionIdEqualTo(collectionId)
                .andIsDelEqualTo(0)
                .andIsDraftEqualTo(1);
        cfgCollectionList = cfgCollectionMapper.selectByExample(example);
        if (cfgCollectionList != null && cfgCollectionList.size() != 0) {
            return cfgCollectionList.get(0).getId();
        }


        //如果查不到，则通过id查找（此时说明这个一定是草稿版本，因为是刚创建的）
        if (cfgCollectionList == null || cfgCollectionList.size() == 0) {
            CfgCollectionExample example1 = new CfgCollectionExample();
            example1.createCriteria()
                    .andIdEqualTo(collectionId)
                    .andIsDelEqualTo(0)
                    .andIsDraftEqualTo(1);
            cfgCollectionList = cfgCollectionMapper.selectByExample(example1);
            if (cfgCollectionList != null && cfgCollectionList.size() != 0) {
                CfgCollection cfgCollection = cfgCollectionList.get(0);
                cfgCollection.setCollectionId(cfgCollection.getId());
                cfgCollectionMapper.updateByPrimaryKey(cfgCollection);

                draft_id = cfgCollection.getId();
            }

        }

        //如果还查不到，再查是否有线上版本，如果有则生成一个草稿版本
        if (cfgCollectionList == null || cfgCollectionList.size() == 0) {
            CfgCollectionExample example2 = new CfgCollectionExample();
            example2.createCriteria()
                    .andCollectionIdEqualTo(collectionId)
                    .andIsDelEqualTo(0)
                    .andIsDraftEqualTo(0);
            cfgCollectionList = cfgCollectionMapper.selectByExample(example2);

            if (cfgCollectionList != null && cfgCollectionList.size() != 0) {
                //生成草稿版本
                CfgCollection cfgCollection = cfgCollectionList.get(0);

                cfgCollection.setCollectionId(null);
                cfgCollection.setIsDraft(null);

                cfgCollectionMapper.insert(cfgCollection);

                //把collection_id更新进去
                cfgCollection.setCollectionId(cfgCollection.getId());
                cfgCollectionMapper.updateByPrimaryKey(cfgCollection);
                draft_id = cfgCollection.getId();
            }
        }

        //如果还查不到，说明没有创建，新增一个草稿版本
        if (cfgCollectionList == null || cfgCollectionList.size() == 0) {
            CfgCollection cfgCollection = new CfgCollection();
            cfgCollection.setcName(cName == null ? "未命名" : cName);
            cfgCollection.setCreateTime(System.currentTimeMillis());
            cfgCollection.setUpdateTime(System.currentTimeMillis());
            cfgCollection.setUpdateUsername(username);
            cfgCollection.setCreateUsername(username);
            cfgCollection.setIsDel(0);
            cfgCollection.setIsDraft(1);

            cfgCollectionMapper.insert(cfgCollection);

            //把collection_id更新进去
            cfgCollection.setCollectionId(cfgCollection.getId());
            cfgCollectionMapper.updateByPrimaryKey(cfgCollection);
            draft_id = cfgCollection.getId();
        }


        return draft_id;
    }
}
