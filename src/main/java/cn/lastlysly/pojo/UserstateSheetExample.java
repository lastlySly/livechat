package cn.lastlysly.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserstateSheetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserstateSheetExample() {
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

        public Criteria andUserstateIdIsNull() {
            addCriterion("userstate_id is null");
            return (Criteria) this;
        }

        public Criteria andUserstateIdIsNotNull() {
            addCriterion("userstate_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserstateIdEqualTo(Integer value) {
            addCriterion("userstate_id =", value, "userstateId");
            return (Criteria) this;
        }

        public Criteria andUserstateIdNotEqualTo(Integer value) {
            addCriterion("userstate_id <>", value, "userstateId");
            return (Criteria) this;
        }

        public Criteria andUserstateIdGreaterThan(Integer value) {
            addCriterion("userstate_id >", value, "userstateId");
            return (Criteria) this;
        }

        public Criteria andUserstateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("userstate_id >=", value, "userstateId");
            return (Criteria) this;
        }

        public Criteria andUserstateIdLessThan(Integer value) {
            addCriterion("userstate_id <", value, "userstateId");
            return (Criteria) this;
        }

        public Criteria andUserstateIdLessThanOrEqualTo(Integer value) {
            addCriterion("userstate_id <=", value, "userstateId");
            return (Criteria) this;
        }

        public Criteria andUserstateIdIn(List<Integer> values) {
            addCriterion("userstate_id in", values, "userstateId");
            return (Criteria) this;
        }

        public Criteria andUserstateIdNotIn(List<Integer> values) {
            addCriterion("userstate_id not in", values, "userstateId");
            return (Criteria) this;
        }

        public Criteria andUserstateIdBetween(Integer value1, Integer value2) {
            addCriterion("userstate_id between", value1, value2, "userstateId");
            return (Criteria) this;
        }

        public Criteria andUserstateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("userstate_id not between", value1, value2, "userstateId");
            return (Criteria) this;
        }

        public Criteria andUserstateNameIsNull() {
            addCriterion("userstate_name is null");
            return (Criteria) this;
        }

        public Criteria andUserstateNameIsNotNull() {
            addCriterion("userstate_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserstateNameEqualTo(String value) {
            addCriterion("userstate_name =", value, "userstateName");
            return (Criteria) this;
        }

        public Criteria andUserstateNameNotEqualTo(String value) {
            addCriterion("userstate_name <>", value, "userstateName");
            return (Criteria) this;
        }

        public Criteria andUserstateNameGreaterThan(String value) {
            addCriterion("userstate_name >", value, "userstateName");
            return (Criteria) this;
        }

        public Criteria andUserstateNameGreaterThanOrEqualTo(String value) {
            addCriterion("userstate_name >=", value, "userstateName");
            return (Criteria) this;
        }

        public Criteria andUserstateNameLessThan(String value) {
            addCriterion("userstate_name <", value, "userstateName");
            return (Criteria) this;
        }

        public Criteria andUserstateNameLessThanOrEqualTo(String value) {
            addCriterion("userstate_name <=", value, "userstateName");
            return (Criteria) this;
        }

        public Criteria andUserstateNameLike(String value) {
            addCriterion("userstate_name like", value, "userstateName");
            return (Criteria) this;
        }

        public Criteria andUserstateNameNotLike(String value) {
            addCriterion("userstate_name not like", value, "userstateName");
            return (Criteria) this;
        }

        public Criteria andUserstateNameIn(List<String> values) {
            addCriterion("userstate_name in", values, "userstateName");
            return (Criteria) this;
        }

        public Criteria andUserstateNameNotIn(List<String> values) {
            addCriterion("userstate_name not in", values, "userstateName");
            return (Criteria) this;
        }

        public Criteria andUserstateNameBetween(String value1, String value2) {
            addCriterion("userstate_name between", value1, value2, "userstateName");
            return (Criteria) this;
        }

        public Criteria andUserstateNameNotBetween(String value1, String value2) {
            addCriterion("userstate_name not between", value1, value2, "userstateName");
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