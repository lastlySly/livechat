package cn.lastlysly.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessagesSheetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessagesSheetExample() {
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

        public Criteria andMessagesIdIsNull() {
            addCriterion("messages_id is null");
            return (Criteria) this;
        }

        public Criteria andMessagesIdIsNotNull() {
            addCriterion("messages_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessagesIdEqualTo(Integer value) {
            addCriterion("messages_id =", value, "messagesId");
            return (Criteria) this;
        }

        public Criteria andMessagesIdNotEqualTo(Integer value) {
            addCriterion("messages_id <>", value, "messagesId");
            return (Criteria) this;
        }

        public Criteria andMessagesIdGreaterThan(Integer value) {
            addCriterion("messages_id >", value, "messagesId");
            return (Criteria) this;
        }

        public Criteria andMessagesIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("messages_id >=", value, "messagesId");
            return (Criteria) this;
        }

        public Criteria andMessagesIdLessThan(Integer value) {
            addCriterion("messages_id <", value, "messagesId");
            return (Criteria) this;
        }

        public Criteria andMessagesIdLessThanOrEqualTo(Integer value) {
            addCriterion("messages_id <=", value, "messagesId");
            return (Criteria) this;
        }

        public Criteria andMessagesIdIn(List<Integer> values) {
            addCriterion("messages_id in", values, "messagesId");
            return (Criteria) this;
        }

        public Criteria andMessagesIdNotIn(List<Integer> values) {
            addCriterion("messages_id not in", values, "messagesId");
            return (Criteria) this;
        }

        public Criteria andMessagesIdBetween(Integer value1, Integer value2) {
            addCriterion("messages_id between", value1, value2, "messagesId");
            return (Criteria) this;
        }

        public Criteria andMessagesIdNotBetween(Integer value1, Integer value2) {
            addCriterion("messages_id not between", value1, value2, "messagesId");
            return (Criteria) this;
        }

        public Criteria andMessagesStatusIsNull() {
            addCriterion("messages_status is null");
            return (Criteria) this;
        }

        public Criteria andMessagesStatusIsNotNull() {
            addCriterion("messages_status is not null");
            return (Criteria) this;
        }

        public Criteria andMessagesStatusEqualTo(Boolean value) {
            addCriterion("messages_status =", value, "messagesStatus");
            return (Criteria) this;
        }

        public Criteria andMessagesStatusNotEqualTo(Boolean value) {
            addCriterion("messages_status <>", value, "messagesStatus");
            return (Criteria) this;
        }

        public Criteria andMessagesStatusGreaterThan(Boolean value) {
            addCriterion("messages_status >", value, "messagesStatus");
            return (Criteria) this;
        }

        public Criteria andMessagesStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("messages_status >=", value, "messagesStatus");
            return (Criteria) this;
        }

        public Criteria andMessagesStatusLessThan(Boolean value) {
            addCriterion("messages_status <", value, "messagesStatus");
            return (Criteria) this;
        }

        public Criteria andMessagesStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("messages_status <=", value, "messagesStatus");
            return (Criteria) this;
        }

        public Criteria andMessagesStatusIn(List<Boolean> values) {
            addCriterion("messages_status in", values, "messagesStatus");
            return (Criteria) this;
        }

        public Criteria andMessagesStatusNotIn(List<Boolean> values) {
            addCriterion("messages_status not in", values, "messagesStatus");
            return (Criteria) this;
        }

        public Criteria andMessagesStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("messages_status between", value1, value2, "messagesStatus");
            return (Criteria) this;
        }

        public Criteria andMessagesStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("messages_status not between", value1, value2, "messagesStatus");
            return (Criteria) this;
        }

        public Criteria andMessagesTimeIsNull() {
            addCriterion("messages_time is null");
            return (Criteria) this;
        }

        public Criteria andMessagesTimeIsNotNull() {
            addCriterion("messages_time is not null");
            return (Criteria) this;
        }

        public Criteria andMessagesTimeEqualTo(Date value) {
            addCriterion("messages_time =", value, "messagesTime");
            return (Criteria) this;
        }

        public Criteria andMessagesTimeNotEqualTo(Date value) {
            addCriterion("messages_time <>", value, "messagesTime");
            return (Criteria) this;
        }

        public Criteria andMessagesTimeGreaterThan(Date value) {
            addCriterion("messages_time >", value, "messagesTime");
            return (Criteria) this;
        }

        public Criteria andMessagesTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("messages_time >=", value, "messagesTime");
            return (Criteria) this;
        }

        public Criteria andMessagesTimeLessThan(Date value) {
            addCriterion("messages_time <", value, "messagesTime");
            return (Criteria) this;
        }

        public Criteria andMessagesTimeLessThanOrEqualTo(Date value) {
            addCriterion("messages_time <=", value, "messagesTime");
            return (Criteria) this;
        }

        public Criteria andMessagesTimeIn(List<Date> values) {
            addCriterion("messages_time in", values, "messagesTime");
            return (Criteria) this;
        }

        public Criteria andMessagesTimeNotIn(List<Date> values) {
            addCriterion("messages_time not in", values, "messagesTime");
            return (Criteria) this;
        }

        public Criteria andMessagesTimeBetween(Date value1, Date value2) {
            addCriterion("messages_time between", value1, value2, "messagesTime");
            return (Criteria) this;
        }

        public Criteria andMessagesTimeNotBetween(Date value1, Date value2) {
            addCriterion("messages_time not between", value1, value2, "messagesTime");
            return (Criteria) this;
        }

        public Criteria andMessagesTypeidIsNull() {
            addCriterion("messages_typeid is null");
            return (Criteria) this;
        }

        public Criteria andMessagesTypeidIsNotNull() {
            addCriterion("messages_typeid is not null");
            return (Criteria) this;
        }

        public Criteria andMessagesTypeidEqualTo(Integer value) {
            addCriterion("messages_typeid =", value, "messagesTypeid");
            return (Criteria) this;
        }

        public Criteria andMessagesTypeidNotEqualTo(Integer value) {
            addCriterion("messages_typeid <>", value, "messagesTypeid");
            return (Criteria) this;
        }

        public Criteria andMessagesTypeidGreaterThan(Integer value) {
            addCriterion("messages_typeid >", value, "messagesTypeid");
            return (Criteria) this;
        }

        public Criteria andMessagesTypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("messages_typeid >=", value, "messagesTypeid");
            return (Criteria) this;
        }

        public Criteria andMessagesTypeidLessThan(Integer value) {
            addCriterion("messages_typeid <", value, "messagesTypeid");
            return (Criteria) this;
        }

        public Criteria andMessagesTypeidLessThanOrEqualTo(Integer value) {
            addCriterion("messages_typeid <=", value, "messagesTypeid");
            return (Criteria) this;
        }

        public Criteria andMessagesTypeidIn(List<Integer> values) {
            addCriterion("messages_typeid in", values, "messagesTypeid");
            return (Criteria) this;
        }

        public Criteria andMessagesTypeidNotIn(List<Integer> values) {
            addCriterion("messages_typeid not in", values, "messagesTypeid");
            return (Criteria) this;
        }

        public Criteria andMessagesTypeidBetween(Integer value1, Integer value2) {
            addCriterion("messages_typeid between", value1, value2, "messagesTypeid");
            return (Criteria) this;
        }

        public Criteria andMessagesTypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("messages_typeid not between", value1, value2, "messagesTypeid");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidIsNull() {
            addCriterion("messages_from_loginid is null");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidIsNotNull() {
            addCriterion("messages_from_loginid is not null");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidEqualTo(String value) {
            addCriterion("messages_from_loginid =", value, "messagesFromLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidNotEqualTo(String value) {
            addCriterion("messages_from_loginid <>", value, "messagesFromLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidGreaterThan(String value) {
            addCriterion("messages_from_loginid >", value, "messagesFromLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidGreaterThanOrEqualTo(String value) {
            addCriterion("messages_from_loginid >=", value, "messagesFromLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidLessThan(String value) {
            addCriterion("messages_from_loginid <", value, "messagesFromLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidLessThanOrEqualTo(String value) {
            addCriterion("messages_from_loginid <=", value, "messagesFromLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidLike(String value) {
            addCriterion("messages_from_loginid like", value, "messagesFromLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidNotLike(String value) {
            addCriterion("messages_from_loginid not like", value, "messagesFromLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidIn(List<String> values) {
            addCriterion("messages_from_loginid in", values, "messagesFromLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidNotIn(List<String> values) {
            addCriterion("messages_from_loginid not in", values, "messagesFromLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidBetween(String value1, String value2) {
            addCriterion("messages_from_loginid between", value1, value2, "messagesFromLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesFromLoginidNotBetween(String value1, String value2) {
            addCriterion("messages_from_loginid not between", value1, value2, "messagesFromLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidIsNull() {
            addCriterion("messages_to_loginid is null");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidIsNotNull() {
            addCriterion("messages_to_loginid is not null");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidEqualTo(String value) {
            addCriterion("messages_to_loginid =", value, "messagesToLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidNotEqualTo(String value) {
            addCriterion("messages_to_loginid <>", value, "messagesToLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidGreaterThan(String value) {
            addCriterion("messages_to_loginid >", value, "messagesToLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidGreaterThanOrEqualTo(String value) {
            addCriterion("messages_to_loginid >=", value, "messagesToLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidLessThan(String value) {
            addCriterion("messages_to_loginid <", value, "messagesToLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidLessThanOrEqualTo(String value) {
            addCriterion("messages_to_loginid <=", value, "messagesToLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidLike(String value) {
            addCriterion("messages_to_loginid like", value, "messagesToLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidNotLike(String value) {
            addCriterion("messages_to_loginid not like", value, "messagesToLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidIn(List<String> values) {
            addCriterion("messages_to_loginid in", values, "messagesToLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidNotIn(List<String> values) {
            addCriterion("messages_to_loginid not in", values, "messagesToLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidBetween(String value1, String value2) {
            addCriterion("messages_to_loginid between", value1, value2, "messagesToLoginid");
            return (Criteria) this;
        }

        public Criteria andMessagesToLoginidNotBetween(String value1, String value2) {
            addCriterion("messages_to_loginid not between", value1, value2, "messagesToLoginid");
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