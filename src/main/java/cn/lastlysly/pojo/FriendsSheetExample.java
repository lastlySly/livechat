package cn.lastlysly.pojo;

import java.util.ArrayList;
import java.util.List;

public class FriendsSheetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FriendsSheetExample() {
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

        public Criteria andFriendsIdIsNull() {
            addCriterion("friends_id is null");
            return (Criteria) this;
        }

        public Criteria andFriendsIdIsNotNull() {
            addCriterion("friends_id is not null");
            return (Criteria) this;
        }

        public Criteria andFriendsIdEqualTo(Integer value) {
            addCriterion("friends_id =", value, "friendsId");
            return (Criteria) this;
        }

        public Criteria andFriendsIdNotEqualTo(Integer value) {
            addCriterion("friends_id <>", value, "friendsId");
            return (Criteria) this;
        }

        public Criteria andFriendsIdGreaterThan(Integer value) {
            addCriterion("friends_id >", value, "friendsId");
            return (Criteria) this;
        }

        public Criteria andFriendsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("friends_id >=", value, "friendsId");
            return (Criteria) this;
        }

        public Criteria andFriendsIdLessThan(Integer value) {
            addCriterion("friends_id <", value, "friendsId");
            return (Criteria) this;
        }

        public Criteria andFriendsIdLessThanOrEqualTo(Integer value) {
            addCriterion("friends_id <=", value, "friendsId");
            return (Criteria) this;
        }

        public Criteria andFriendsIdIn(List<Integer> values) {
            addCriterion("friends_id in", values, "friendsId");
            return (Criteria) this;
        }

        public Criteria andFriendsIdNotIn(List<Integer> values) {
            addCriterion("friends_id not in", values, "friendsId");
            return (Criteria) this;
        }

        public Criteria andFriendsIdBetween(Integer value1, Integer value2) {
            addCriterion("friends_id between", value1, value2, "friendsId");
            return (Criteria) this;
        }

        public Criteria andFriendsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("friends_id not between", value1, value2, "friendsId");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidIsNull() {
            addCriterion("friends_friend_loginid is null");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidIsNotNull() {
            addCriterion("friends_friend_loginid is not null");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidEqualTo(String value) {
            addCriterion("friends_friend_loginid =", value, "friendsFriendLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidNotEqualTo(String value) {
            addCriterion("friends_friend_loginid <>", value, "friendsFriendLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidGreaterThan(String value) {
            addCriterion("friends_friend_loginid >", value, "friendsFriendLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidGreaterThanOrEqualTo(String value) {
            addCriterion("friends_friend_loginid >=", value, "friendsFriendLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidLessThan(String value) {
            addCriterion("friends_friend_loginid <", value, "friendsFriendLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidLessThanOrEqualTo(String value) {
            addCriterion("friends_friend_loginid <=", value, "friendsFriendLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidLike(String value) {
            addCriterion("friends_friend_loginid like", value, "friendsFriendLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidNotLike(String value) {
            addCriterion("friends_friend_loginid not like", value, "friendsFriendLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidIn(List<String> values) {
            addCriterion("friends_friend_loginid in", values, "friendsFriendLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidNotIn(List<String> values) {
            addCriterion("friends_friend_loginid not in", values, "friendsFriendLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidBetween(String value1, String value2) {
            addCriterion("friends_friend_loginid between", value1, value2, "friendsFriendLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendLoginidNotBetween(String value1, String value2) {
            addCriterion("friends_friend_loginid not between", value1, value2, "friendsFriendLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidIsNull() {
            addCriterion("friends_user_loginid is null");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidIsNotNull() {
            addCriterion("friends_user_loginid is not null");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidEqualTo(String value) {
            addCriterion("friends_user_loginid =", value, "friendsUserLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidNotEqualTo(String value) {
            addCriterion("friends_user_loginid <>", value, "friendsUserLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidGreaterThan(String value) {
            addCriterion("friends_user_loginid >", value, "friendsUserLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidGreaterThanOrEqualTo(String value) {
            addCriterion("friends_user_loginid >=", value, "friendsUserLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidLessThan(String value) {
            addCriterion("friends_user_loginid <", value, "friendsUserLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidLessThanOrEqualTo(String value) {
            addCriterion("friends_user_loginid <=", value, "friendsUserLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidLike(String value) {
            addCriterion("friends_user_loginid like", value, "friendsUserLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidNotLike(String value) {
            addCriterion("friends_user_loginid not like", value, "friendsUserLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidIn(List<String> values) {
            addCriterion("friends_user_loginid in", values, "friendsUserLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidNotIn(List<String> values) {
            addCriterion("friends_user_loginid not in", values, "friendsUserLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidBetween(String value1, String value2) {
            addCriterion("friends_user_loginid between", value1, value2, "friendsUserLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsUserLoginidNotBetween(String value1, String value2) {
            addCriterion("friends_user_loginid not between", value1, value2, "friendsUserLoginid");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksIsNull() {
            addCriterion("friends_remarks is null");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksIsNotNull() {
            addCriterion("friends_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksEqualTo(String value) {
            addCriterion("friends_remarks =", value, "friendsRemarks");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksNotEqualTo(String value) {
            addCriterion("friends_remarks <>", value, "friendsRemarks");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksGreaterThan(String value) {
            addCriterion("friends_remarks >", value, "friendsRemarks");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("friends_remarks >=", value, "friendsRemarks");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksLessThan(String value) {
            addCriterion("friends_remarks <", value, "friendsRemarks");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksLessThanOrEqualTo(String value) {
            addCriterion("friends_remarks <=", value, "friendsRemarks");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksLike(String value) {
            addCriterion("friends_remarks like", value, "friendsRemarks");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksNotLike(String value) {
            addCriterion("friends_remarks not like", value, "friendsRemarks");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksIn(List<String> values) {
            addCriterion("friends_remarks in", values, "friendsRemarks");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksNotIn(List<String> values) {
            addCriterion("friends_remarks not in", values, "friendsRemarks");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksBetween(String value1, String value2) {
            addCriterion("friends_remarks between", value1, value2, "friendsRemarks");
            return (Criteria) this;
        }

        public Criteria andFriendsRemarksNotBetween(String value1, String value2) {
            addCriterion("friends_remarks not between", value1, value2, "friendsRemarks");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendgroupsidIsNull() {
            addCriterion("friends_friendgroupsid is null");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendgroupsidIsNotNull() {
            addCriterion("friends_friendgroupsid is not null");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendgroupsidEqualTo(Integer value) {
            addCriterion("friends_friendgroupsid =", value, "friendsFriendgroupsid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendgroupsidNotEqualTo(Integer value) {
            addCriterion("friends_friendgroupsid <>", value, "friendsFriendgroupsid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendgroupsidGreaterThan(Integer value) {
            addCriterion("friends_friendgroupsid >", value, "friendsFriendgroupsid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendgroupsidGreaterThanOrEqualTo(Integer value) {
            addCriterion("friends_friendgroupsid >=", value, "friendsFriendgroupsid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendgroupsidLessThan(Integer value) {
            addCriterion("friends_friendgroupsid <", value, "friendsFriendgroupsid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendgroupsidLessThanOrEqualTo(Integer value) {
            addCriterion("friends_friendgroupsid <=", value, "friendsFriendgroupsid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendgroupsidIn(List<Integer> values) {
            addCriterion("friends_friendgroupsid in", values, "friendsFriendgroupsid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendgroupsidNotIn(List<Integer> values) {
            addCriterion("friends_friendgroupsid not in", values, "friendsFriendgroupsid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendgroupsidBetween(Integer value1, Integer value2) {
            addCriterion("friends_friendgroupsid between", value1, value2, "friendsFriendgroupsid");
            return (Criteria) this;
        }

        public Criteria andFriendsFriendgroupsidNotBetween(Integer value1, Integer value2) {
            addCriterion("friends_friendgroupsid not between", value1, value2, "friendsFriendgroupsid");
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