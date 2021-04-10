package org.zhangxujie.konfig.model;

import java.util.ArrayList;
import java.util.List;

public class OpLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OpLogExample() {
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

        public Criteria andLogIsNull() {
            addCriterion("log is null");
            return (Criteria) this;
        }

        public Criteria andLogIsNotNull() {
            addCriterion("log is not null");
            return (Criteria) this;
        }

        public Criteria andLogEqualTo(String value) {
            addCriterion("log =", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotEqualTo(String value) {
            addCriterion("log <>", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThan(String value) {
            addCriterion("log >", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogGreaterThanOrEqualTo(String value) {
            addCriterion("log >=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThan(String value) {
            addCriterion("log <", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLessThanOrEqualTo(String value) {
            addCriterion("log <=", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogLike(String value) {
            addCriterion("log like", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotLike(String value) {
            addCriterion("log not like", value, "log");
            return (Criteria) this;
        }

        public Criteria andLogIn(List<String> values) {
            addCriterion("log in", values, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotIn(List<String> values) {
            addCriterion("log not in", values, "log");
            return (Criteria) this;
        }

        public Criteria andLogBetween(String value1, String value2) {
            addCriterion("log between", value1, value2, "log");
            return (Criteria) this;
        }

        public Criteria andLogNotBetween(String value1, String value2) {
            addCriterion("log not between", value1, value2, "log");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdIsNull() {
            addCriterion("update_account_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdIsNotNull() {
            addCriterion("update_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdEqualTo(Integer value) {
            addCriterion("update_account_id =", value, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdNotEqualTo(Integer value) {
            addCriterion("update_account_id <>", value, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdGreaterThan(Integer value) {
            addCriterion("update_account_id >", value, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_account_id >=", value, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdLessThan(Integer value) {
            addCriterion("update_account_id <", value, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdLessThanOrEqualTo(Integer value) {
            addCriterion("update_account_id <=", value, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdIn(List<Integer> values) {
            addCriterion("update_account_id in", values, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdNotIn(List<Integer> values) {
            addCriterion("update_account_id not in", values, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdBetween(Integer value1, Integer value2) {
            addCriterion("update_account_id between", value1, value2, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("update_account_id not between", value1, value2, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameIsNull() {
            addCriterion("update_username is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameIsNotNull() {
            addCriterion("update_username is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameEqualTo(String value) {
            addCriterion("update_username =", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameNotEqualTo(String value) {
            addCriterion("update_username <>", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameGreaterThan(String value) {
            addCriterion("update_username >", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("update_username >=", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameLessThan(String value) {
            addCriterion("update_username <", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameLessThanOrEqualTo(String value) {
            addCriterion("update_username <=", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameLike(String value) {
            addCriterion("update_username like", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameNotLike(String value) {
            addCriterion("update_username not like", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameIn(List<String> values) {
            addCriterion("update_username in", values, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameNotIn(List<String> values) {
            addCriterion("update_username not in", values, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameBetween(String value1, String value2) {
            addCriterion("update_username between", value1, value2, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameNotBetween(String value1, String value2) {
            addCriterion("update_username not between", value1, value2, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Long value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Long value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Long value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Long value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Long value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Long> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Long> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Long value1, Long value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Long value1, Long value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andOpTypeIsNull() {
            addCriterion("op_type is null");
            return (Criteria) this;
        }

        public Criteria andOpTypeIsNotNull() {
            addCriterion("op_type is not null");
            return (Criteria) this;
        }

        public Criteria andOpTypeEqualTo(String value) {
            addCriterion("op_type =", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotEqualTo(String value) {
            addCriterion("op_type <>", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeGreaterThan(String value) {
            addCriterion("op_type >", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeGreaterThanOrEqualTo(String value) {
            addCriterion("op_type >=", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeLessThan(String value) {
            addCriterion("op_type <", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeLessThanOrEqualTo(String value) {
            addCriterion("op_type <=", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeLike(String value) {
            addCriterion("op_type like", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotLike(String value) {
            addCriterion("op_type not like", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeIn(List<String> values) {
            addCriterion("op_type in", values, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotIn(List<String> values) {
            addCriterion("op_type not in", values, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeBetween(String value1, String value2) {
            addCriterion("op_type between", value1, value2, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotBetween(String value1, String value2) {
            addCriterion("op_type not between", value1, value2, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTableIsNull() {
            addCriterion("op_table is null");
            return (Criteria) this;
        }

        public Criteria andOpTableIsNotNull() {
            addCriterion("op_table is not null");
            return (Criteria) this;
        }

        public Criteria andOpTableEqualTo(String value) {
            addCriterion("op_table =", value, "opTable");
            return (Criteria) this;
        }

        public Criteria andOpTableNotEqualTo(String value) {
            addCriterion("op_table <>", value, "opTable");
            return (Criteria) this;
        }

        public Criteria andOpTableGreaterThan(String value) {
            addCriterion("op_table >", value, "opTable");
            return (Criteria) this;
        }

        public Criteria andOpTableGreaterThanOrEqualTo(String value) {
            addCriterion("op_table >=", value, "opTable");
            return (Criteria) this;
        }

        public Criteria andOpTableLessThan(String value) {
            addCriterion("op_table <", value, "opTable");
            return (Criteria) this;
        }

        public Criteria andOpTableLessThanOrEqualTo(String value) {
            addCriterion("op_table <=", value, "opTable");
            return (Criteria) this;
        }

        public Criteria andOpTableLike(String value) {
            addCriterion("op_table like", value, "opTable");
            return (Criteria) this;
        }

        public Criteria andOpTableNotLike(String value) {
            addCriterion("op_table not like", value, "opTable");
            return (Criteria) this;
        }

        public Criteria andOpTableIn(List<String> values) {
            addCriterion("op_table in", values, "opTable");
            return (Criteria) this;
        }

        public Criteria andOpTableNotIn(List<String> values) {
            addCriterion("op_table not in", values, "opTable");
            return (Criteria) this;
        }

        public Criteria andOpTableBetween(String value1, String value2) {
            addCriterion("op_table between", value1, value2, "opTable");
            return (Criteria) this;
        }

        public Criteria andOpTableNotBetween(String value1, String value2) {
            addCriterion("op_table not between", value1, value2, "opTable");
            return (Criteria) this;
        }

        public Criteria andOpFieldIsNull() {
            addCriterion("op_field is null");
            return (Criteria) this;
        }

        public Criteria andOpFieldIsNotNull() {
            addCriterion("op_field is not null");
            return (Criteria) this;
        }

        public Criteria andOpFieldEqualTo(String value) {
            addCriterion("op_field =", value, "opField");
            return (Criteria) this;
        }

        public Criteria andOpFieldNotEqualTo(String value) {
            addCriterion("op_field <>", value, "opField");
            return (Criteria) this;
        }

        public Criteria andOpFieldGreaterThan(String value) {
            addCriterion("op_field >", value, "opField");
            return (Criteria) this;
        }

        public Criteria andOpFieldGreaterThanOrEqualTo(String value) {
            addCriterion("op_field >=", value, "opField");
            return (Criteria) this;
        }

        public Criteria andOpFieldLessThan(String value) {
            addCriterion("op_field <", value, "opField");
            return (Criteria) this;
        }

        public Criteria andOpFieldLessThanOrEqualTo(String value) {
            addCriterion("op_field <=", value, "opField");
            return (Criteria) this;
        }

        public Criteria andOpFieldLike(String value) {
            addCriterion("op_field like", value, "opField");
            return (Criteria) this;
        }

        public Criteria andOpFieldNotLike(String value) {
            addCriterion("op_field not like", value, "opField");
            return (Criteria) this;
        }

        public Criteria andOpFieldIn(List<String> values) {
            addCriterion("op_field in", values, "opField");
            return (Criteria) this;
        }

        public Criteria andOpFieldNotIn(List<String> values) {
            addCriterion("op_field not in", values, "opField");
            return (Criteria) this;
        }

        public Criteria andOpFieldBetween(String value1, String value2) {
            addCriterion("op_field between", value1, value2, "opField");
            return (Criteria) this;
        }

        public Criteria andOpFieldNotBetween(String value1, String value2) {
            addCriterion("op_field not between", value1, value2, "opField");
            return (Criteria) this;
        }

        public Criteria andDataBeforeIsNull() {
            addCriterion("data_before is null");
            return (Criteria) this;
        }

        public Criteria andDataBeforeIsNotNull() {
            addCriterion("data_before is not null");
            return (Criteria) this;
        }

        public Criteria andDataBeforeEqualTo(String value) {
            addCriterion("data_before =", value, "dataBefore");
            return (Criteria) this;
        }

        public Criteria andDataBeforeNotEqualTo(String value) {
            addCriterion("data_before <>", value, "dataBefore");
            return (Criteria) this;
        }

        public Criteria andDataBeforeGreaterThan(String value) {
            addCriterion("data_before >", value, "dataBefore");
            return (Criteria) this;
        }

        public Criteria andDataBeforeGreaterThanOrEqualTo(String value) {
            addCriterion("data_before >=", value, "dataBefore");
            return (Criteria) this;
        }

        public Criteria andDataBeforeLessThan(String value) {
            addCriterion("data_before <", value, "dataBefore");
            return (Criteria) this;
        }

        public Criteria andDataBeforeLessThanOrEqualTo(String value) {
            addCriterion("data_before <=", value, "dataBefore");
            return (Criteria) this;
        }

        public Criteria andDataBeforeLike(String value) {
            addCriterion("data_before like", value, "dataBefore");
            return (Criteria) this;
        }

        public Criteria andDataBeforeNotLike(String value) {
            addCriterion("data_before not like", value, "dataBefore");
            return (Criteria) this;
        }

        public Criteria andDataBeforeIn(List<String> values) {
            addCriterion("data_before in", values, "dataBefore");
            return (Criteria) this;
        }

        public Criteria andDataBeforeNotIn(List<String> values) {
            addCriterion("data_before not in", values, "dataBefore");
            return (Criteria) this;
        }

        public Criteria andDataBeforeBetween(String value1, String value2) {
            addCriterion("data_before between", value1, value2, "dataBefore");
            return (Criteria) this;
        }

        public Criteria andDataBeforeNotBetween(String value1, String value2) {
            addCriterion("data_before not between", value1, value2, "dataBefore");
            return (Criteria) this;
        }

        public Criteria andDataAfterIsNull() {
            addCriterion("data_after is null");
            return (Criteria) this;
        }

        public Criteria andDataAfterIsNotNull() {
            addCriterion("data_after is not null");
            return (Criteria) this;
        }

        public Criteria andDataAfterEqualTo(String value) {
            addCriterion("data_after =", value, "dataAfter");
            return (Criteria) this;
        }

        public Criteria andDataAfterNotEqualTo(String value) {
            addCriterion("data_after <>", value, "dataAfter");
            return (Criteria) this;
        }

        public Criteria andDataAfterGreaterThan(String value) {
            addCriterion("data_after >", value, "dataAfter");
            return (Criteria) this;
        }

        public Criteria andDataAfterGreaterThanOrEqualTo(String value) {
            addCriterion("data_after >=", value, "dataAfter");
            return (Criteria) this;
        }

        public Criteria andDataAfterLessThan(String value) {
            addCriterion("data_after <", value, "dataAfter");
            return (Criteria) this;
        }

        public Criteria andDataAfterLessThanOrEqualTo(String value) {
            addCriterion("data_after <=", value, "dataAfter");
            return (Criteria) this;
        }

        public Criteria andDataAfterLike(String value) {
            addCriterion("data_after like", value, "dataAfter");
            return (Criteria) this;
        }

        public Criteria andDataAfterNotLike(String value) {
            addCriterion("data_after not like", value, "dataAfter");
            return (Criteria) this;
        }

        public Criteria andDataAfterIn(List<String> values) {
            addCriterion("data_after in", values, "dataAfter");
            return (Criteria) this;
        }

        public Criteria andDataAfterNotIn(List<String> values) {
            addCriterion("data_after not in", values, "dataAfter");
            return (Criteria) this;
        }

        public Criteria andDataAfterBetween(String value1, String value2) {
            addCriterion("data_after between", value1, value2, "dataAfter");
            return (Criteria) this;
        }

        public Criteria andDataAfterNotBetween(String value1, String value2) {
            addCriterion("data_after not between", value1, value2, "dataAfter");
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