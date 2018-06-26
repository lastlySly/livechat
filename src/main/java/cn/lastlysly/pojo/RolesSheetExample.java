package cn.lastlysly.pojo;

import java.util.ArrayList;
import java.util.List;

public class RolesSheetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RolesSheetExample() {
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

        public Criteria andRolesIdIsNull() {
            addCriterion("roles_id is null");
            return (Criteria) this;
        }

        public Criteria andRolesIdIsNotNull() {
            addCriterion("roles_id is not null");
            return (Criteria) this;
        }

        public Criteria andRolesIdEqualTo(Integer value) {
            addCriterion("roles_id =", value, "rolesId");
            return (Criteria) this;
        }

        public Criteria andRolesIdNotEqualTo(Integer value) {
            addCriterion("roles_id <>", value, "rolesId");
            return (Criteria) this;
        }

        public Criteria andRolesIdGreaterThan(Integer value) {
            addCriterion("roles_id >", value, "rolesId");
            return (Criteria) this;
        }

        public Criteria andRolesIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("roles_id >=", value, "rolesId");
            return (Criteria) this;
        }

        public Criteria andRolesIdLessThan(Integer value) {
            addCriterion("roles_id <", value, "rolesId");
            return (Criteria) this;
        }

        public Criteria andRolesIdLessThanOrEqualTo(Integer value) {
            addCriterion("roles_id <=", value, "rolesId");
            return (Criteria) this;
        }

        public Criteria andRolesIdIn(List<Integer> values) {
            addCriterion("roles_id in", values, "rolesId");
            return (Criteria) this;
        }

        public Criteria andRolesIdNotIn(List<Integer> values) {
            addCriterion("roles_id not in", values, "rolesId");
            return (Criteria) this;
        }

        public Criteria andRolesIdBetween(Integer value1, Integer value2) {
            addCriterion("roles_id between", value1, value2, "rolesId");
            return (Criteria) this;
        }

        public Criteria andRolesIdNotBetween(Integer value1, Integer value2) {
            addCriterion("roles_id not between", value1, value2, "rolesId");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameIsNull() {
            addCriterion("roles_username is null");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameIsNotNull() {
            addCriterion("roles_username is not null");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameEqualTo(String value) {
            addCriterion("roles_username =", value, "rolesUsername");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameNotEqualTo(String value) {
            addCriterion("roles_username <>", value, "rolesUsername");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameGreaterThan(String value) {
            addCriterion("roles_username >", value, "rolesUsername");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("roles_username >=", value, "rolesUsername");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameLessThan(String value) {
            addCriterion("roles_username <", value, "rolesUsername");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameLessThanOrEqualTo(String value) {
            addCriterion("roles_username <=", value, "rolesUsername");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameLike(String value) {
            addCriterion("roles_username like", value, "rolesUsername");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameNotLike(String value) {
            addCriterion("roles_username not like", value, "rolesUsername");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameIn(List<String> values) {
            addCriterion("roles_username in", values, "rolesUsername");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameNotIn(List<String> values) {
            addCriterion("roles_username not in", values, "rolesUsername");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameBetween(String value1, String value2) {
            addCriterion("roles_username between", value1, value2, "rolesUsername");
            return (Criteria) this;
        }

        public Criteria andRolesUsernameNotBetween(String value1, String value2) {
            addCriterion("roles_username not between", value1, value2, "rolesUsername");
            return (Criteria) this;
        }

        public Criteria andRolesNameIsNull() {
            addCriterion("roles_name is null");
            return (Criteria) this;
        }

        public Criteria andRolesNameIsNotNull() {
            addCriterion("roles_name is not null");
            return (Criteria) this;
        }

        public Criteria andRolesNameEqualTo(String value) {
            addCriterion("roles_name =", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameNotEqualTo(String value) {
            addCriterion("roles_name <>", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameGreaterThan(String value) {
            addCriterion("roles_name >", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameGreaterThanOrEqualTo(String value) {
            addCriterion("roles_name >=", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameLessThan(String value) {
            addCriterion("roles_name <", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameLessThanOrEqualTo(String value) {
            addCriterion("roles_name <=", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameLike(String value) {
            addCriterion("roles_name like", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameNotLike(String value) {
            addCriterion("roles_name not like", value, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameIn(List<String> values) {
            addCriterion("roles_name in", values, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameNotIn(List<String> values) {
            addCriterion("roles_name not in", values, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameBetween(String value1, String value2) {
            addCriterion("roles_name between", value1, value2, "rolesName");
            return (Criteria) this;
        }

        public Criteria andRolesNameNotBetween(String value1, String value2) {
            addCriterion("roles_name not between", value1, value2, "rolesName");
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