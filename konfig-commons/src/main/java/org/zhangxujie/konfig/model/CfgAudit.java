package org.zhangxujie.konfig.model;

public class CfgAudit {
    private Integer id;

    private Integer cfgCollectionId;

    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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