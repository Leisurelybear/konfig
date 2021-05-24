/**
 * FileName: CfgAuditServiceImpl
 * Author:   jason
 * Date:     2021/5/21 19:42
 * Description:
 */
package org.zhangxujie.konfig.service.impl;

import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.mapper.CfgAuditMapper;
import org.zhangxujie.konfig.mapper.CfgCollectionMapper;
import org.zhangxujie.konfig.model.CfgAudit;
import org.zhangxujie.konfig.model.CfgAuditExample;
import org.zhangxujie.konfig.model.CfgCollection;
import org.zhangxujie.konfig.service.CfgAuditService;
import org.zhangxujie.konfig.util.TimeUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CfgAuditServiceImpl implements CfgAuditService {

    @Resource
    private CfgAuditMapper cfgAuditMapper;

    @Resource
    private CfgCollectionMapper cfgCollectionMapper;

    @Override
    public int submit(Integer collectionId, Integer applicantAccountId) {

        //先查询是否有未处理条目，如果有，则不允许提交
        CfgAuditExample example = new CfgAuditExample();
        example.createCriteria()
                .andCfgCollectionIdEqualTo(collectionId)
                .andApplicantAidEqualTo(applicantAccountId)
                .andStatusEqualTo(0);
        List<CfgAudit> audits = cfgAuditMapper.selectByExample(example);
        if (audits.size() > 0){
            //有未处理的
            return -audits.get(0).getId();
        }

        //提交到审核
        CfgCollection cfgCollection = cfgCollectionMapper.selectByPrimaryKey(collectionId);

        CfgAudit item = new CfgAudit();
        item.setCfgCollectionId(collectionId);
        item.setApplicantAid(applicantAccountId);
        item.setStatus(0);
        item.setContent(cfgCollection.getIsDraft() == 1 ? "上线申请 | 配置集ID：" + collectionId : "下线申请 | 配置集ID：" + collectionId);
        item.setSubmitTime(TimeUtil.getNowTimestamp());

        cfgAuditMapper.insert(item);

        return item.getId();
    }

    @Override
    public void approve(Integer auditId, Integer reviewerAccountId) {
        CfgAudit item = cfgAuditMapper.selectByPrimaryKey(auditId);
        item.setStatus(1);
        item.setHandleTime(TimeUtil.getNowTimestamp());
        item.setReviewerAid(reviewerAccountId);
        cfgAuditMapper.updateByPrimaryKey(item);

    }

    @Override
    public void reject(Integer auditId, Integer reviewerAccountId) {
        CfgAudit item = cfgAuditMapper.selectByPrimaryKey(auditId);

        item.setStatus(2);
        item.setHandleTime(TimeUtil.getNowTimestamp());
        item.setReviewerAid(reviewerAccountId);

        cfgAuditMapper.updateByPrimaryKey(item);
    }

    @Override
    public List<CfgAudit> selectByCollectionIds(List<Integer> collectionIds, List<Integer> statusCondition) {

        CfgAuditExample example = new CfgAuditExample();
        example.setOrderByClause("submit_time desc");
        example.createCriteria()
                .andStatusIn(statusCondition)
                .andCfgCollectionIdIn(collectionIds);

        return cfgAuditMapper.selectByExample(example);
    }

    @Override
    public List<CfgAudit> selectByApplicantId(Integer applicantAccountId, List<Integer> statusCondition) {

        CfgAuditExample example = new CfgAuditExample();
        example.setOrderByClause("submit_time desc");

        example.createCriteria()
                .andStatusIn(statusCondition)
                .andApplicantAidEqualTo(applicantAccountId);

        return cfgAuditMapper.selectByExample(example);
    }

    @Override
    public List<CfgAudit> selectByReviewerId(Integer reviewerAccountId, List<Integer> statusCondition) {
        CfgAuditExample example = new CfgAuditExample();
        example.setOrderByClause("handle_time desc");
        example.createCriteria()
                .andStatusIn(statusCondition)
                .andReviewerAidEqualTo(reviewerAccountId);

        return cfgAuditMapper.selectByExample(example);
    }

    @Override
    public CfgAudit selectByAuditId(Integer auditId) {

        return cfgAuditMapper.selectByPrimaryKey(auditId);
    }
}
