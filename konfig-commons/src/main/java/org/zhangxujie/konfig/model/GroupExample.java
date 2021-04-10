package org.zhangxujie.konfig.model;

import java.util.ArrayList;
import java.util.List;

public class GroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupExample() {
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

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIsNull() {
            addCriterion("group_role is null");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIsNotNull() {
            addCriterion("group_role is not null");
            return (Criteria) this;
        }

        public Criteria andGroupRoleEqualTo(Integer value) {
            addCriterion("group_role =", value, "groupRole");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNotEqualTo(Integer value) {
            addCriterion("group_role <>", value, "groupRole");
            return (Criteria) this;
        }

        public Criteria andGroupRoleGreaterThan(Integer value) {
            addCriterion("group_role >", value, "groupRole");
            return (Criteria) this;
        }

        public Criteria andGroupRoleGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_role >=", value, "groupRole");
            return (Criteria) this;
        }

        public Criteria andGroupRoleLessThan(Integer value) {
            addCriterion("group_role <", value, "groupRole");
            return (Criteria) this;
        }

        public Criteria andGroupRoleLessThanOrEqualTo(Integer value) {
            addCriterion("group_role <=", value, "groupRole");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIn(List<Integer> values) {
            addCriterion("group_role in", values, "groupRole");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNotIn(List<Integer> values) {
            addCriterion("group_role not in", values, "groupRole");
            return (Criteria) this;
        }

        public Criteria andGroupRoleBetween(Integer value1, Integer value2) {
            addCriterion("group_role between", value1, value2, "groupRole");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNotBetween(Integer value1, Integer value2) {
            addCriterion("group_role not between", value1, value2, "groupRole");
            return (Criteria) this;
        }

        public Criteria andRootAccountIdIsNull() {
            addCriterion("root_account_id is null");
            return (Criteria) this;
        }

        public Criteria andRootAccountIdIsNotNull() {
            addCriterion("root_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andRootAccountIdEqualTo(Integer value) {
            addCriterion("root_account_id =", value, "rootAccountId");
            return (Criteria) this;
        }

        public Criteria andRootAccountIdNotEqualTo(Integer value) {
            addCriterion("root_account_id <>", value, "rootAccountId");
            return (Criteria) this;
        }

        public Criteria andRootAccountIdGreaterThan(Integer value) {
            addCriterion("root_account_id >", value, "rootAccountId");
            return (Criteria) this;
        }

        public Criteria andRootAccountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("root_account_id >=", value, "rootAccountId");
            return (Criteria) this;
        }

        public Criteria andRootAccountIdLessThan(Integer value) {
            addCriterion("root_account_id <", value, "rootAccountId");
            return (Criteria) this;
        }

        public Criteria andRootAccountIdLessThanOrEqualTo(Integer value) {
            addCriterion("root_account_id <=", value, "rootAccountId");
            return (Criteria) this;
        }

        public Criteria andRootAccountIdIn(List<Integer> values) {
            addCriterion("root_account_id in", values, "rootAccountId");
            return (Criteria) this;
        }

        public Criteria andRootAccountIdNotIn(List<Integer> values) {
            addCriterion("root_account_id not in", values, "rootAccountId");
            return (Criteria) this;
        }

        public Criteria andRootAccountIdBetween(Integer value1, Integer value2) {
            addCriterion("root_account_id between", value1, value2, "rootAccountId");
            return (Criteria) this;
        }

        public Criteria andRootAccountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("root_account_id not between", value1, value2, "rootAccountId");
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

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Integer value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Integer value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Integer value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Integer value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Integer value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Integer> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Integer> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Integer value1, Integer value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Integer value1, Integer value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
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