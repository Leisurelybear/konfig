package org.zhangxujie.konfig.service;

import org.zhangxujie.konfig.model.CfgAudit;

import java.util.List;

public interface CfgAuditService {


    /**
     * @Author: Jason
     * @Description: 提交申请，返回申请ID号
     * @Date: 2021/5/21 20:00
     * @Param collectionId: 申请上线的ID
     * @Param applicantAccountId: 申请人ID
     * @return: int 返回添加的ID
     **/
    int submit(Integer collectionId, Integer applicantAccountId);

    void approve(Integer auditId, Integer reviewerAccountId);

    void reject(Integer auditId, Integer reviewerAccountId);

    /**
     * @Description: 通过配置集ID和审核状态查询list
     * @Date: 2021/5/21 21:48
     * @Param collectionIds: 申请权限的配置集ID
     * @Param statusCondition: 一个List，里面写要查询的status列表
     * @return: java.util.List<org.zhangxujie.konfig.model.CfgAudit>
     **/
    List<CfgAudit> selectByCollectionIds(List<Integer> collectionIds, List<Integer> statusCondition);

    /**
     * @Description: 通过申请人ID和审核状态查询List
     **/
    List<CfgAudit> selectByApplicantId(Integer applicantAccountId, List<Integer> statusCondition);

    /**
     * @Description: 通过处理人ID查询List
     **/
    List<CfgAudit> selectByReviewerId(Integer reviewerAccountId, List<Integer> statusCondition);

    /**
     * @Author: Jason
     * @Description: 通过AuditId查询
     * @Date: 2021/5/24 14:01
     * @Param auditId: 审核ID
     * @return: org.zhangxujie.konfig.model.CfgAudit
     **/
    CfgAudit selectByAuditId(Integer auditId);
}
