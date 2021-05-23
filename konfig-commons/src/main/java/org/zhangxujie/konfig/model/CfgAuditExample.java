package org.zhangxujie.konfig.model;

import java.util.ArrayList;
import java.util.List;

public class CfgAuditExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CfgAuditExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCfgCollectionIdIsNull() {
            addCriterion("cfg_collection_id is null");
            return (Criteria) this;
        }

        public Criteria andCfgCollectionIdIsNotNull() {
            addCriterion("cfg_collection_id is not null");
            return (Criteria) this;
        }

        public Criteria andCfgCollectionIdEqualTo(Integer value) {
            addCriterion("cfg_collection_id =", value, "cfgCollectionId");
            return (Criteria) this;
        }

        public Criteria andCfgCollectionIdNotEqualTo(Integer value) {
            addCriterion("cfg_collection_id <>", value, "cfgCollectionId");
            return (Criteria) this;
        }

        public Criteria andCfgCollectionIdGreaterThan(Integer value) {
            addCriterion("cfg_collection_id >", value, "cfgCollectionId");
            return (Criteria) this;
        }

        public Criteria andCfgCollectionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cfg_collection_id >=", value, "cfgCollectionId");
            return (Criteria) this;
        }

        public Criteria andCfgCollectionIdLessThan(Integer value) {
            addCriterion("cfg_collection_id <", value, "cfgCollectionId");
            return (Criteria) this;
        }

        public Criteria andCfgCollectionIdLessThanOrEqualTo(Integer value) {
            addCriterion("cfg_collection_id <=", value, "cfgCollectionId");
            return (Criteria) this;
        }

        public Criteria andCfgCollectionIdIn(List<Integer> values) {
            addCriterion("cfg_collection_id in", values, "cfgCollectionId");
            return (Criteria) this;
        }

        public Criteria andCfgCollectionIdNotIn(List<Integer> values) {
            addCriterion("cfg_collection_id not in", values, "cfgCollectionId");
            return (Criteria) this;
        }

        public Criteria andCfgCollectionIdBetween(Integer value1, Integer value2) {
            addCriterion("cfg_collection_id between", value1, value2, "cfgCollectionId");
            return (Criteria) this;
        }

        public Criteria andCfgCollectionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cfg_collection_id not between", value1, value2, "cfgCollectionId");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andApplicantAidIsNull() {
            addCriterion("applicant_aid is null");
            return (Criteria) this;
        }

        public Criteria andApplicantAidIsNotNull() {
            addCriterion("applicant_aid is not null");
            return (Criteria) this;
        }

        public Criteria andApplicantAidEqualTo(Integer value) {
            addCriterion("applicant_aid =", value, "applicantAid");
            return (Criteria) this;
        }

        public Criteria andApplicantAidNotEqualTo(Integer value) {
            addCriterion("applicant_aid <>", value, "applicantAid");
            return (Criteria) this;
        }

        public Criteria andApplicantAidGreaterThan(Integer value) {
            addCriterion("applicant_aid >", value, "applicantAid");
            return (Criteria) this;
        }

        public Criteria andApplicantAidGreaterThanOrEqualTo(Integer value) {
            addCriterion("applicant_aid >=", value, "applicantAid");
            return (Criteria) this;
        }

        public Criteria andApplicantAidLessThan(Integer value) {
            addCriterion("applicant_aid <", value, "applicantAid");
            return (Criteria) this;
        }

        public Criteria andApplicantAidLessThanOrEqualTo(Integer value) {
            addCriterion("applicant_aid <=", value, "applicantAid");
            return (Criteria) this;
        }

        public Criteria andApplicantAidIn(List<Integer> values) {
            addCriterion("applicant_aid in", values, "applicantAid");
            return (Criteria) this;
        }

        public Criteria andApplicantAidNotIn(List<Integer> values) {
            addCriterion("applicant_aid not in", values, "applicantAid");
            return (Criteria) this;
        }

        public Criteria andApplicantAidBetween(Integer value1, Integer value2) {
            addCriterion("applicant_aid between", value1, value2, "applicantAid");
            return (Criteria) this;
        }

        public Criteria andApplicantAidNotBetween(Integer value1, Integer value2) {
            addCriterion("applicant_aid not between", value1, value2, "applicantAid");
            return (Criteria) this;
        }

        public Criteria andReviewerAidIsNull() {
            addCriterion("reviewer_aid is null");
            return (Criteria) this;
        }

        public Criteria andReviewerAidIsNotNull() {
            addCriterion("reviewer_aid is not null");
            return (Criteria) this;
        }

        public Criteria andReviewerAidEqualTo(Integer value) {
            addCriterion("reviewer_aid =", value, "reviewerAid");
            return (Criteria) this;
        }

        public Criteria andReviewerAidNotEqualTo(Integer value) {
            addCriterion("reviewer_aid <>", value, "reviewerAid");
            return (Criteria) this;
        }

        public Criteria andReviewerAidGreaterThan(Integer value) {
            addCriterion("reviewer_aid >", value, "reviewerAid");
            return (Criteria) this;
        }

        public Criteria andReviewerAidGreaterThanOrEqualTo(Integer value) {
            addCriterion("reviewer_aid >=", value, "reviewerAid");
            return (Criteria) this;
        }

        public Criteria andReviewerAidLessThan(Integer value) {
            addCriterion("reviewer_aid <", value, "reviewerAid");
            return (Criteria) this;
        }

        public Criteria andReviewerAidLessThanOrEqualTo(Integer value) {
            addCriterion("reviewer_aid <=", value, "reviewerAid");
            return (Criteria) this;
        }

        public Criteria andReviewerAidIn(List<Integer> values) {
            addCriterion("reviewer_aid in", values, "reviewerAid");
            return (Criteria) this;
        }

        public Criteria andReviewerAidNotIn(List<Integer> values) {
            addCriterion("reviewer_aid not in", values, "reviewerAid");
            return (Criteria) this;
        }

        public Criteria andReviewerAidBetween(Integer value1, Integer value2) {
            addCriterion("reviewer_aid between", value1, value2, "reviewerAid");
            return (Criteria) this;
        }

        public Criteria andReviewerAidNotBetween(Integer value1, Integer value2) {
            addCriterion("reviewer_aid not between", value1, value2, "reviewerAid");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNull() {
            addCriterion("submit_time is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("submit_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(Long value) {
            addCriterion("submit_time =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(Long value) {
            addCriterion("submit_time <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(Long value) {
            addCriterion("submit_time >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("submit_time >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(Long value) {
            addCriterion("submit_time <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(Long value) {
            addCriterion("submit_time <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<Long> values) {
            addCriterion("submit_time in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<Long> values) {
            addCriterion("submit_time not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(Long value1, Long value2) {
            addCriterion("submit_time between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(Long value1, Long value2) {
            addCriterion("submit_time not between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNull() {
            addCriterion("handle_time is null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNotNull() {
            addCriterion("handle_time is not null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeEqualTo(Long value) {
            addCriterion("handle_time =", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotEqualTo(Long value) {
            addCriterion("handle_time <>", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThan(Long value) {
            addCriterion("handle_time >", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("handle_time >=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThan(Long value) {
            addCriterion("handle_time <", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThanOrEqualTo(Long value) {
            addCriterion("handle_time <=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIn(List<Long> values) {
            addCriterion("handle_time in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotIn(List<Long> values) {
            addCriterion("handle_time not in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeBetween(Long value1, Long value2) {
            addCriterion("handle_time between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotBetween(Long value1, Long value2) {
            addCriterion("handle_time not between", value1, value2, "handleTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}