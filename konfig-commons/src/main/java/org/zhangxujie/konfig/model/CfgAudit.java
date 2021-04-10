package org.zhangxujie.konfig.model;

import java.io.Serializable;

public class CfgAudit implements Serializable {
    private Integer id;

    private Integer cfgCollectionId;

    private Integer status;

    private Integer applicantAid;

    private Integer reviewerAid;

    private Long submitTime;

    private Long handleTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCfgCollectionId() {
        return cfgCollectionId;
    }

    public void setCfgCollectionId(Integer cfgCollectionId) {
        this.cfgCollectionId = cfgCollectionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getApplicantAid() {
        return applicantAid;
    }

    public void setApplicantAid(Integer applicantAid) {
        this.applicantAid = applicantAid;
    }

    public Integer getReviewerAid() {
        return reviewerAid;
    }

    public void setReviewerAid(Integer reviewerAid) {
        this.reviewerAid = reviewerAid;
    }

    public Long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Long submitTime) {
        this.submitTime = submitTime;
    }

    public Long getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Long handleTime) {
        this.handleTime = handleTime;
    }
}