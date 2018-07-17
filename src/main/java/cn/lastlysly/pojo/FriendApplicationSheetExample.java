package cn.lastlysly.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FriendApplicationSheetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FriendApplicationSheetExample() {
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

        public Criteria andFriendApplicationIdIsNull() {
            addCriterion("friend_application_id is null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationIdIsNotNull() {
            addCriterion("friend_application_id is not null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationIdEqualTo(Integer value) {
            addCriterion("friend_application_id =", value, "friendApplicationId");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationIdNotEqualTo(Integer value) {
            addCriterion("friend_application_id <>", value, "friendApplicationId");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationIdGreaterThan(Integer value) {
            addCriterion("friend_application_id >", value, "friendApplicationId");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("friend_application_id >=", value, "friendApplicationId");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationIdLessThan(Integer value) {
            addCriterion("friend_application_id <", value, "friendApplicationId");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationIdLessThanOrEqualTo(Integer value) {
            addCriterion("friend_application_id <=", value, "friendApplicationId");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationIdIn(List<Integer> values) {
            addCriterion("friend_application_id in", values, "friendApplicationId");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationIdNotIn(List<Integer> values) {
            addCriterion("friend_application_id not in", values, "friendApplicationId");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationIdBetween(Integer value1, Integer value2) {
            addCriterion("friend_application_id between", value1, value2, "friendApplicationId");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("friend_application_id not between", value1, value2, "friendApplicationId");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromIsNull() {
            addCriterion("friend_application_from is null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromIsNotNull() {
            addCriterion("friend_application_from is not null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromEqualTo(String value) {
            addCriterion("friend_application_from =", value, "friendApplicationFrom");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromNotEqualTo(String value) {
            addCriterion("friend_application_from <>", value, "friendApplicationFrom");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromGreaterThan(String value) {
            addCriterion("friend_application_from >", value, "friendApplicationFrom");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromGreaterThanOrEqualTo(String value) {
            addCriterion("friend_application_from >=", value, "friendApplicationFrom");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromLessThan(String value) {
            addCriterion("friend_application_from <", value, "friendApplicationFrom");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromLessThanOrEqualTo(String value) {
            addCriterion("friend_application_from <=", value, "friendApplicationFrom");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromLike(String value) {
            addCriterion("friend_application_from like", value, "friendApplicationFrom");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromNotLike(String value) {
            addCriterion("friend_application_from not like", value, "friendApplicationFrom");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromIn(List<String> values) {
            addCriterion("friend_application_from in", values, "friendApplicationFrom");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromNotIn(List<String> values) {
            addCriterion("friend_application_from not in", values, "friendApplicationFrom");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromBetween(String value1, String value2) {
            addCriterion("friend_application_from between", value1, value2, "friendApplicationFrom");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationFromNotBetween(String value1, String value2) {
            addCriterion("friend_application_from not between", value1, value2, "friendApplicationFrom");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToIsNull() {
            addCriterion("friend_application_to is null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToIsNotNull() {
            addCriterion("friend_application_to is not null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToEqualTo(String value) {
            addCriterion("friend_application_to =", value, "friendApplicationTo");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToNotEqualTo(String value) {
            addCriterion("friend_application_to <>", value, "friendApplicationTo");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToGreaterThan(String value) {
            addCriterion("friend_application_to >", value, "friendApplicationTo");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToGreaterThanOrEqualTo(String value) {
            addCriterion("friend_application_to >=", value, "friendApplicationTo");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToLessThan(String value) {
            addCriterion("friend_application_to <", value, "friendApplicationTo");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToLessThanOrEqualTo(String value) {
            addCriterion("friend_application_to <=", value, "friendApplicationTo");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToLike(String value) {
            addCriterion("friend_application_to like", value, "friendApplicationTo");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToNotLike(String value) {
            addCriterion("friend_application_to not like", value, "friendApplicationTo");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToIn(List<String> values) {
            addCriterion("friend_application_to in", values, "friendApplicationTo");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToNotIn(List<String> values) {
            addCriterion("friend_application_to not in", values, "friendApplicationTo");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToBetween(String value1, String value2) {
            addCriterion("friend_application_to between", value1, value2, "friendApplicationTo");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationToNotBetween(String value1, String value2) {
            addCriterion("friend_application_to not between", value1, value2, "friendApplicationTo");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkIsNull() {
            addCriterion("friend_application_remark is null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkIsNotNull() {
            addCriterion("friend_application_remark is not null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkEqualTo(String value) {
            addCriterion("friend_application_remark =", value, "friendApplicationRemark");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkNotEqualTo(String value) {
            addCriterion("friend_application_remark <>", value, "friendApplicationRemark");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkGreaterThan(String value) {
            addCriterion("friend_application_remark >", value, "friendApplicationRemark");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("friend_application_remark >=", value, "friendApplicationRemark");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkLessThan(String value) {
            addCriterion("friend_application_remark <", value, "friendApplicationRemark");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkLessThanOrEqualTo(String value) {
            addCriterion("friend_application_remark <=", value, "friendApplicationRemark");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkLike(String value) {
            addCriterion("friend_application_remark like", value, "friendApplicationRemark");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkNotLike(String value) {
            addCriterion("friend_application_remark not like", value, "friendApplicationRemark");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkIn(List<String> values) {
            addCriterion("friend_application_remark in", values, "friendApplicationRemark");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkNotIn(List<String> values) {
            addCriterion("friend_application_remark not in", values, "friendApplicationRemark");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkBetween(String value1, String value2) {
            addCriterion("friend_application_remark between", value1, value2, "friendApplicationRemark");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRemarkNotBetween(String value1, String value2) {
            addCriterion("friend_application_remark not between", value1, value2, "friendApplicationRemark");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationGroupIsNull() {
            addCriterion("friend_application_group is null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationGroupIsNotNull() {
            addCriterion("friend_application_group is not null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationGroupEqualTo(Integer value) {
            addCriterion("friend_application_group =", value, "friendApplicationGroup");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationGroupNotEqualTo(Integer value) {
            addCriterion("friend_application_group <>", value, "friendApplicationGroup");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationGroupGreaterThan(Integer value) {
            addCriterion("friend_application_group >", value, "friendApplicationGroup");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationGroupGreaterThanOrEqualTo(Integer value) {
            addCriterion("friend_application_group >=", value, "friendApplicationGroup");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationGroupLessThan(Integer value) {
            addCriterion("friend_application_group <", value, "friendApplicationGroup");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationGroupLessThanOrEqualTo(Integer value) {
            addCriterion("friend_application_group <=", value, "friendApplicationGroup");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationGroupIn(List<Integer> values) {
            addCriterion("friend_application_group in", values, "friendApplicationGroup");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationGroupNotIn(List<Integer> values) {
            addCriterion("friend_application_group not in", values, "friendApplicationGroup");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationGroupBetween(Integer value1, Integer value2) {
            addCriterion("friend_application_group between", value1, value2, "friendApplicationGroup");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationGroupNotBetween(Integer value1, Integer value2) {
            addCriterion("friend_application_group not between", value1, value2, "friendApplicationGroup");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationTimeIsNull() {
            addCriterion("friend_application_time is null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationTimeIsNotNull() {
            addCriterion("friend_application_time is not null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationTimeEqualTo(Date value) {
            addCriterion("friend_application_time =", value, "friendApplicationTime");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationTimeNotEqualTo(Date value) {
            addCriterion("friend_application_time <>", value, "friendApplicationTime");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationTimeGreaterThan(Date value) {
            addCriterion("friend_application_time >", value, "friendApplicationTime");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("friend_application_time >=", value, "friendApplicationTime");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationTimeLessThan(Date value) {
            addCriterion("friend_application_time <", value, "friendApplicationTime");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationTimeLessThanOrEqualTo(Date value) {
            addCriterion("friend_application_time <=", value, "friendApplicationTime");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationTimeIn(List<Date> values) {
            addCriterion("friend_application_time in", values, "friendApplicationTime");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationTimeNotIn(List<Date> values) {
            addCriterion("friend_application_time not in", values, "friendApplicationTime");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationTimeBetween(Date value1, Date value2) {
            addCriterion("friend_application_time between", value1, value2, "friendApplicationTime");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationTimeNotBetween(Date value1, Date value2) {
            addCriterion("friend_application_time not between", value1, value2, "friendApplicationTime");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageIsNull() {
            addCriterion("friend_application_message is null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageIsNotNull() {
            addCriterion("friend_application_message is not null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageEqualTo(String value) {
            addCriterion("friend_application_message =", value, "friendApplicationMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageNotEqualTo(String value) {
            addCriterion("friend_application_message <>", value, "friendApplicationMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageGreaterThan(String value) {
            addCriterion("friend_application_message >", value, "friendApplicationMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageGreaterThanOrEqualTo(String value) {
            addCriterion("friend_application_message >=", value, "friendApplicationMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageLessThan(String value) {
            addCriterion("friend_application_message <", value, "friendApplicationMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageLessThanOrEqualTo(String value) {
            addCriterion("friend_application_message <=", value, "friendApplicationMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageLike(String value) {
            addCriterion("friend_application_message like", value, "friendApplicationMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageNotLike(String value) {
            addCriterion("friend_application_message not like", value, "friendApplicationMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageIn(List<String> values) {
            addCriterion("friend_application_message in", values, "friendApplicationMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageNotIn(List<String> values) {
            addCriterion("friend_application_message not in", values, "friendApplicationMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageBetween(String value1, String value2) {
            addCriterion("friend_application_message between", value1, value2, "friendApplicationMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationMessageNotBetween(String value1, String value2) {
            addCriterion("friend_application_message not between", value1, value2, "friendApplicationMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusIsNull() {
            addCriterion("friend_application_status is null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusIsNotNull() {
            addCriterion("friend_application_status is not null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusEqualTo(String value) {
            addCriterion("friend_application_status =", value, "friendApplicationStatus");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusNotEqualTo(String value) {
            addCriterion("friend_application_status <>", value, "friendApplicationStatus");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusGreaterThan(String value) {
            addCriterion("friend_application_status >", value, "friendApplicationStatus");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusGreaterThanOrEqualTo(String value) {
            addCriterion("friend_application_status >=", value, "friendApplicationStatus");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusLessThan(String value) {
            addCriterion("friend_application_status <", value, "friendApplicationStatus");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusLessThanOrEqualTo(String value) {
            addCriterion("friend_application_status <=", value, "friendApplicationStatus");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusLike(String value) {
            addCriterion("friend_application_status like", value, "friendApplicationStatus");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusNotLike(String value) {
            addCriterion("friend_application_status not like", value, "friendApplicationStatus");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusIn(List<String> values) {
            addCriterion("friend_application_status in", values, "friendApplicationStatus");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusNotIn(List<String> values) {
            addCriterion("friend_application_status not in", values, "friendApplicationStatus");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusBetween(String value1, String value2) {
            addCriterion("friend_application_status between", value1, value2, "friendApplicationStatus");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationStatusNotBetween(String value1, String value2) {
            addCriterion("friend_application_status not between", value1, value2, "friendApplicationStatus");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageIsNull() {
            addCriterion("friend_application_refuse_message is null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageIsNotNull() {
            addCriterion("friend_application_refuse_message is not null");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageEqualTo(String value) {
            addCriterion("friend_application_refuse_message =", value, "friendApplicationRefuseMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageNotEqualTo(String value) {
            addCriterion("friend_application_refuse_message <>", value, "friendApplicationRefuseMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageGreaterThan(String value) {
            addCriterion("friend_application_refuse_message >", value, "friendApplicationRefuseMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageGreaterThanOrEqualTo(String value) {
            addCriterion("friend_application_refuse_message >=", value, "friendApplicationRefuseMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageLessThan(String value) {
            addCriterion("friend_application_refuse_message <", value, "friendApplicationRefuseMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageLessThanOrEqualTo(String value) {
            addCriterion("friend_application_refuse_message <=", value, "friendApplicationRefuseMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageLike(String value) {
            addCriterion("friend_application_refuse_message like", value, "friendApplicationRefuseMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageNotLike(String value) {
            addCriterion("friend_application_refuse_message not like", value, "friendApplicationRefuseMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageIn(List<String> values) {
            addCriterion("friend_application_refuse_message in", values, "friendApplicationRefuseMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageNotIn(List<String> values) {
            addCriterion("friend_application_refuse_message not in", values, "friendApplicationRefuseMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageBetween(String value1, String value2) {
            addCriterion("friend_application_refuse_message between", value1, value2, "friendApplicationRefuseMessage");
            return (Criteria) this;
        }

        public Criteria andFriendApplicationRefuseMessageNotBetween(String value1, String value2) {
            addCriterion("friend_application_refuse_message not between", value1, value2, "friendApplicationRefuseMessage");
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