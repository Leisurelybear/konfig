/**
 * FileName: CfgCollectionServiceImpl
 * Author:   jason
 * Date:     2021/4/5 20:44
 * Description:
 */
package org.zhangxujie.konfig.service.impl;

import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.dto.AddCollectionReq;
import org.zhangxujie.konfig.dto.AddCollectionResp;
import org.zhangxujie.konfig.dto.DeleteCollectionReq;
import org.zhangxujie.konfig.dto.DeleteCollectionResp;
import org.zhangxujie.konfig.mapper.CfgCollectionMapper;
import org.zhangxujie.konfig.mapper.CfgPermissionMapper;
import org.zhangxujie.konfig.model.CfgCollection;
import org.zhangxujie.konfig.model.CfgCollectionExample;
import org.zhangxujie.konfig.dao.AccountRemoteDAO;
import org.zhangxujie.konfig.service.CfgCollectionService;
import org.zhangxujie.konfig.service.CfgPermissionService;
import org.zhangxujie.konfig.util.TimeUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CfgCollectionServiceImpl implements CfgCollectionService {

    @Resource
    private CfgCollectionMapper cfgCollectionMapper;

    @Resource
    private CfgPermissionMapper cfgPermissionMapper;

    @Resource
    private AccountRemoteDAO accountRemoteDAO;

    @Resource
    private CfgPermissionService cfgPermissionService;

    /**
     * @param collectionNameLike 模糊查询条件：集合名
     * @param sort               排序 >=0正序，否则倒序
     * @param pageNum            页码
     * @param pageSize           每页的数量
     * @param isDraft
     * @Author: Jason
     * @Description:
     * @Date: 2021/4/11 1:24
     * @return: java.util.List<org.zhangxujie.konfig.model.CfgCollection>
     **/
    @Override
    public List<CfgCollection> query(String collectionNameLike, Integer sort, Integer pageNum, Integer pageSize, Boolean isDraft) {

        List<CfgCollection> cfgCollectionList;

        pageNum = pageNum < 0 ? 0 : pageNum;
        pageSize = pageSize <= 0 ? 20 : pageSize;

        CfgCollectionExample example = new CfgCollectionExample();
        example.setOrderByClause(String.format("update_time desc limit %d, %d", pageNum * pageSize, pageSize));
        CfgCollectionExample.Criteria c = example.createCriteria().andIsDelEqualTo(0);
        if (collectionNameLike != null && !collectionNameLike.equals("")) {
            c.andCNameLike("%" + collectionNameLike + "%");
        }

        if (isDraft){
            c.andIsDraftEqualTo(1);
        }


        cfgCollectionList = cfgCollectionMapper.selectByExample(example);

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
    public Integer setToDraft(Integer collectionId, String username) {
        List<CfgCollection> cfgCollectionList = null;
        int draft_id = collectionId;//草稿版本的自增id


        CfgCollectionExample example0 = new CfgCollectionExample();
        example0.createCriteria()
                .andIsDelEqualTo(0)
                .andCollectionIdEqualTo(collectionId);
        String cName = cfgCollectionMapper.selectByExample(example0).get(0).getcName();

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
                cfgCollection.setUpdateTime(TimeUtil.getNowTimestamp());
                cfgCollection.setUpdateUsername(username);
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

                cfgCollection.setId(null);
                cfgCollection.setIsDraft(1);
                cfgCollection.setUpdateUsername(username);
                cfgCollection.setUpdateTime(TimeUtil.getNowTimestamp());

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
            cfgCollection.setCreateTime(TimeUtil.getNowTimestamp());
            cfgCollection.setUpdateTime(TimeUtil.getNowTimestamp());
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

    @Override
    public AddCollectionResp add(AddCollectionReq req, String username) {

        CfgCollectionExample example = new CfgCollectionExample();
        example.createCriteria().andIsDelEqualTo(0).andCNameEqualTo(req.getCollectionName());

        long cnt = cfgCollectionMapper.countByExample(example);

        if (cnt > 0) {
            return new AddCollectionResp(-1);
        }

        CfgCollection cfgCollection = new CfgCollection();
        cfgCollection.setIsDraft(1);
        cfgCollection.setUpdateTime(TimeUtil.getNowTimestamp());
        cfgCollection.setUpdateUsername(username);
        cfgCollection.setIsDel(0);
        cfgCollection.setCreateUsername(username);
        cfgCollection.setCreateTime(TimeUtil.getNowTimestamp());
        cfgCollection.setcName(req.getCollectionName());

        cfgCollectionMapper.insert(cfgCollection);
        cfgCollection.setCollectionId(cfgCollection.getId());//把原始主键ID更新到当前条目中
        cfgCollectionMapper.updateByPrimaryKey(cfgCollection);

        return new AddCollectionResp(cfgCollection.getId());
    }

    @Override
    public CfgCollection queryById(Integer id) {
        if (id <= 0) {
            return null;
        }

        return cfgCollectionMapper.selectByPrimaryKey(id);
    }

    @Override
    public DeleteCollectionResp delete(DeleteCollectionReq req, String username) {

        Integer collectionId = req.getCollectionId();

        //1、先查询是不是线上，如果是，则不能删除
        CfgCollectionExample example = new CfgCollectionExample();
        example.createCriteria()
                .andIdEqualTo(collectionId)
                .andIsDraftEqualTo(0)
                .andIsDelEqualTo(0);

        List<CfgCollection> cfgCollectionList = cfgCollectionMapper.selectByExample(example);
        if (cfgCollectionList.size() != 0) {
            return new DeleteCollectionResp(false);
        }

        //2、如果不是，则删除
        CfgCollectionExample example1 = new CfgCollectionExample();
        example1.createCriteria()
                .andIdEqualTo(collectionId)
                .andIsDraftEqualTo(1)
                .andIsDelEqualTo(0);

        CfgCollection cfgCollection = cfgCollectionMapper.selectByExample(example1).get(0);
        cfgCollection.setIsDel(1);
        int rowsAffact = cfgCollectionMapper.updateByPrimaryKey(cfgCollection);

        if (rowsAffact > 0) {
            return new DeleteCollectionResp(true);
        }
        return new DeleteCollectionResp(false);
    }

    @Override
    public boolean isOnline(Integer collectionId) {
        CfgCollection collection = cfgCollectionMapper.selectByPrimaryKey(collectionId);
        if (collection.getIsDraft() == 1) {
            return false;
        }
        return true;
    }

    @Override
    public void switchDraftStatus(Integer collectionId, String updateUsername) {

        CfgCollection cfgCollection = cfgCollectionMapper.selectByPrimaryKey(collectionId);

        // |1 - 1| = 0
        // |0 - 1| = 1
        cfgCollection.setIsDraft(Math.abs(cfgCollection.getIsDraft() - 1));
        cfgCollectionMapper.updateByPrimaryKey(cfgCollection);

    }

    @Override
    public boolean isOnwer(String username, Integer collectionId) {

        CfgCollection cfgCollection = cfgCollectionMapper.selectByPrimaryKey(collectionId);

        return cfgCollection.getCreateUsername().equals(username);
    }

    @Override
    public List<CfgCollection> getOwnCollectionList(String username) {

        CfgCollectionExample example = new CfgCollectionExample();
        example.createCriteria()
                .andIsDelEqualTo(0)
                .andCreateUsernameEqualTo(username);

        List<CfgCollection> list = cfgCollectionMapper.selectByExample(example);
        if (list == null){
            list = new ArrayList<>();
        }
        return list;
    }

}
