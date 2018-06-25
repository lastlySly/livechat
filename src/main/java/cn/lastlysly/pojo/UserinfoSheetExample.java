package cn.lastlysly.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserinfoSheetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserinfoSheetExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdIsNull() {
            addCriterion("user_login_id is null");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdIsNotNull() {
            addCriterion("user_login_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdEqualTo(String value) {
            addCriterion("user_login_id =", value, "userLoginId");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdNotEqualTo(String value) {
            addCriterion("user_login_id <>", value, "userLoginId");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdGreaterThan(String value) {
            addCriterion("user_login_id >", value, "userLoginId");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_login_id >=", value, "userLoginId");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdLessThan(String value) {
            addCriterion("user_login_id <", value, "userLoginId");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdLessThanOrEqualTo(String value) {
            addCriterion("user_login_id <=", value, "userLoginId");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdLike(String value) {
            addCriterion("user_login_id like", value, "userLoginId");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdNotLike(String value) {
            addCriterion("user_login_id not like", value, "userLoginId");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdIn(List<String> values) {
            addCriterion("user_login_id in", values, "userLoginId");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdNotIn(List<String> values) {
            addCriterion("user_login_id not in", values, "userLoginId");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdBetween(String value1, String value2) {
            addCriterion("user_login_id between", value1, value2, "userLoginId");
            return (Criteria) this;
        }

        public Criteria andUserLoginIdNotBetween(String value1, String value2) {
            addCriterion("user_login_id not between", value1, value2, "userLoginId");
            return (Criteria) this;
        }

        public Criteria andUserNicknameIsNull() {
            addCriterion("user_nickname is null");
            return (Criteria) this;
        }

        public Criteria andUserNicknameIsNotNull() {
            addCriterion("user_nickname is not null");
            return (Criteria) this;
        }

        public Criteria andUserNicknameEqualTo(String value) {
            addCriterion("user_nickname =", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotEqualTo(String value) {
            addCriterion("user_nickname <>", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameGreaterThan(String value) {
            addCriterion("user_nickname >", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("user_nickname >=", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameLessThan(String value) {
            addCriterion("user_nickname <", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameLessThanOrEqualTo(String value) {
            addCriterion("user_nickname <=", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameLike(String value) {
            addCriterion("user_nickname like", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotLike(String value) {
            addCriterion("user_nickname not like", value, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameIn(List<String> values) {
            addCriterion("user_nickname in", values, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotIn(List<String> values) {
            addCriterion("user_nickname not in", values, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameBetween(String value1, String value2) {
            addCriterion("user_nickname between", value1, value2, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserNicknameNotBetween(String value1, String value2) {
            addCriterion("user_nickname not between", value1, value2, "userNickname");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIsNull() {
            addCriterion("user_password is null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIsNotNull() {
            addCriterion("user_password is not null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordEqualTo(String value) {
            addCriterion("user_password =", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotEqualTo(String value) {
            addCriterion("user_password <>", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordGreaterThan(String value) {
            addCriterion("user_password >", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("user_password >=", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLessThan(String value) {
            addCriterion("user_password <", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLessThanOrEqualTo(String value) {
            addCriterion("user_password <=", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLike(String value) {
            addCriterion("user_password like", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotLike(String value) {
            addCriterion("user_password not like", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIn(List<String> values) {
            addCriterion("user_password in", values, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotIn(List<String> values) {
            addCriterion("user_password not in", values, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordBetween(String value1, String value2) {
            addCriterion("user_password between", value1, value2, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotBetween(String value1, String value2) {
            addCriterion("user_password not between", value1, value2, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserGenderIsNull() {
            addCriterion("user_gender is null");
            return (Criteria) this;
        }

        public Criteria andUserGenderIsNotNull() {
            addCriterion("user_gender is not null");
            return (Criteria) this;
        }

        public Criteria andUserGenderEqualTo(Boolean value) {
            addCriterion("user_gender =", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotEqualTo(Boolean value) {
            addCriterion("user_gender <>", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderGreaterThan(Boolean value) {
            addCriterion("user_gender >", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderGreaterThanOrEqualTo(Boolean value) {
            addCriterion("user_gender >=", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderLessThan(Boolean value) {
            addCriterion("user_gender <", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderLessThanOrEqualTo(Boolean value) {
            addCriterion("user_gender <=", value, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderIn(List<Boolean> values) {
            addCriterion("user_gender in", values, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotIn(List<Boolean> values) {
            addCriterion("user_gender not in", values, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderBetween(Boolean value1, Boolean value2) {
            addCriterion("user_gender between", value1, value2, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserGenderNotBetween(Boolean value1, Boolean value2) {
            addCriterion("user_gender not between", value1, value2, "userGender");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIsNull() {
            addCriterion("user_birthday is null");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIsNotNull() {
            addCriterion("user_birthday is not null");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayEqualTo(Date value) {
            addCriterion("user_birthday =", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotEqualTo(Date value) {
            addCriterion("user_birthday <>", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayGreaterThan(Date value) {
            addCriterion("user_birthday >", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("user_birthday >=", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayLessThan(Date value) {
            addCriterion("user_birthday <", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("user_birthday <=", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIn(List<Date> values) {
            addCriterion("user_birthday in", values, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotIn(List<Date> values) {
            addCriterion("user_birthday not in", values, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayBetween(Date value1, Date value2) {
            addCriterion("user_birthday between", value1, value2, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("user_birthday not between", value1, value2, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneIsNull() {
            addCriterion("user_telephone is null");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneIsNotNull() {
            addCriterion("user_telephone is not null");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneEqualTo(String value) {
            addCriterion("user_telephone =", value, "userTelephone");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNotEqualTo(String value) {
            addCriterion("user_telephone <>", value, "userTelephone");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneGreaterThan(String value) {
            addCriterion("user_telephone >", value, "userTelephone");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("user_telephone >=", value, "userTelephone");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneLessThan(String value) {
            addCriterion("user_telephone <", value, "userTelephone");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneLessThanOrEqualTo(String value) {
            addCriterion("user_telephone <=", value, "userTelephone");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneLike(String value) {
            addCriterion("user_telephone like", value, "userTelephone");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNotLike(String value) {
            addCriterion("user_telephone not like", value, "userTelephone");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneIn(List<String> values) {
            addCriterion("user_telephone in", values, "userTelephone");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNotIn(List<String> values) {
            addCriterion("user_telephone not in", values, "userTelephone");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneBetween(String value1, String value2) {
            addCriterion("user_telephone between", value1, value2, "userTelephone");
            return (Criteria) this;
        }

        public Criteria andUserTelephoneNotBetween(String value1, String value2) {
            addCriterion("user_telephone not between", value1, value2, "userTelephone");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNull() {
            addCriterion("user_email is null");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNotNull() {
            addCriterion("user_email is not null");
            return (Criteria) this;
        }

        public Criteria andUserEmailEqualTo(String value) {
            addCriterion("user_email =", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotEqualTo(String value) {
            addCriterion("user_email <>", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThan(String value) {
            addCriterion("user_email >", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThanOrEqualTo(String value) {
            addCriterion("user_email >=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThan(String value) {
            addCriterion("user_email <", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThanOrEqualTo(String value) {
            addCriterion("user_email <=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLike(String value) {
            addCriterion("user_email like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotLike(String value) {
            addCriterion("user_email not like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailIn(List<String> values) {
            addCriterion("user_email in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotIn(List<String> values) {
            addCriterion("user_email not in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailBetween(String value1, String value2) {
            addCriterion("user_email between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotBetween(String value1, String value2) {
            addCriterion("user_email not between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserMottoIsNull() {
            addCriterion("user_motto is null");
            return (Criteria) this;
        }

        public Criteria andUserMottoIsNotNull() {
            addCriterion("user_motto is not null");
            return (Criteria) this;
        }

        public Criteria andUserMottoEqualTo(String value) {
            addCriterion("user_motto =", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoNotEqualTo(String value) {
            addCriterion("user_motto <>", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoGreaterThan(String value) {
            addCriterion("user_motto >", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoGreaterThanOrEqualTo(String value) {
            addCriterion("user_motto >=", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoLessThan(String value) {
            addCriterion("user_motto <", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoLessThanOrEqualTo(String value) {
            addCriterion("user_motto <=", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoLike(String value) {
            addCriterion("user_motto like", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoNotLike(String value) {
            addCriterion("user_motto not like", value, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoIn(List<String> values) {
            addCriterion("user_motto in", values, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoNotIn(List<String> values) {
            addCriterion("user_motto not in", values, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoBetween(String value1, String value2) {
            addCriterion("user_motto between", value1, value2, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserMottoNotBetween(String value1, String value2) {
            addCriterion("user_motto not between", value1, value2, "userMotto");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisIsNull() {
            addCriterion("user_synopsis is null");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisIsNotNull() {
            addCriterion("user_synopsis is not null");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisEqualTo(String value) {
            addCriterion("user_synopsis =", value, "userSynopsis");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisNotEqualTo(String value) {
            addCriterion("user_synopsis <>", value, "userSynopsis");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisGreaterThan(String value) {
            addCriterion("user_synopsis >", value, "userSynopsis");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisGreaterThanOrEqualTo(String value) {
            addCriterion("user_synopsis >=", value, "userSynopsis");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisLessThan(String value) {
            addCriterion("user_synopsis <", value, "userSynopsis");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisLessThanOrEqualTo(String value) {
            addCriterion("user_synopsis <=", value, "userSynopsis");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisLike(String value) {
            addCriterion("user_synopsis like", value, "userSynopsis");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisNotLike(String value) {
            addCriterion("user_synopsis not like", value, "userSynopsis");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisIn(List<String> values) {
            addCriterion("user_synopsis in", values, "userSynopsis");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisNotIn(List<String> values) {
            addCriterion("user_synopsis not in", values, "userSynopsis");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisBetween(String value1, String value2) {
            addCriterion("user_synopsis between", value1, value2, "userSynopsis");
            return (Criteria) this;
        }

        public Criteria andUserSynopsisNotBetween(String value1, String value2) {
            addCriterion("user_synopsis not between", value1, value2, "userSynopsis");
            return (Criteria) this;
        }

        public Criteria andUserNationIdIsNull() {
            addCriterion("user_nation_id is null");
            return (Criteria) this;
        }

        public Criteria andUserNationIdIsNotNull() {
            addCriterion("user_nation_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserNationIdEqualTo(Integer value) {
            addCriterion("user_nation_id =", value, "userNationId");
            return (Criteria) this;
        }

        public Criteria andUserNationIdNotEqualTo(Integer value) {
            addCriterion("user_nation_id <>", value, "userNationId");
            return (Criteria) this;
        }

        public Criteria andUserNationIdGreaterThan(Integer value) {
            addCriterion("user_nation_id >", value, "userNationId");
            return (Criteria) this;
        }

        public Criteria andUserNationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_nation_id >=", value, "userNationId");
            return (Criteria) this;
        }

        public Criteria andUserNationIdLessThan(Integer value) {
            addCriterion("user_nation_id <", value, "userNationId");
            return (Criteria) this;
        }

        public Criteria andUserNationIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_nation_id <=", value, "userNationId");
            return (Criteria) this;
        }

        public Criteria andUserNationIdIn(List<Integer> values) {
            addCriterion("user_nation_id in", values, "userNationId");
            return (Criteria) this;
        }

        public Criteria andUserNationIdNotIn(List<Integer> values) {
            addCriterion("user_nation_id not in", values, "userNationId");
            return (Criteria) this;
        }

        public Criteria andUserNationIdBetween(Integer value1, Integer value2) {
            addCriterion("user_nation_id between", value1, value2, "userNationId");
            return (Criteria) this;
        }

        public Criteria andUserNationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_nation_id not between", value1, value2, "userNationId");
            return (Criteria) this;
        }

        public Criteria andUserProvinceIdIsNull() {
            addCriterion("user_province_id is null");
            return (Criteria) this;
        }

        public Criteria andUserProvinceIdIsNotNull() {
            addCriterion("user_province_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserProvinceIdEqualTo(Integer value) {
            addCriterion("user_province_id =", value, "userProvinceId");
            return (Criteria) this;
        }

        public Criteria andUserProvinceIdNotEqualTo(Integer value) {
            addCriterion("user_province_id <>", value, "userProvinceId");
            return (Criteria) this;
        }

        public Criteria andUserProvinceIdGreaterThan(Integer value) {
            addCriterion("user_province_id >", value, "userProvinceId");
            return (Criteria) this;
        }

        public Criteria andUserProvinceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_province_id >=", value, "userProvinceId");
            return (Criteria) this;
        }

        public Criteria andUserProvinceIdLessThan(Integer value) {
            addCriterion("user_province_id <", value, "userProvinceId");
            return (Criteria) this;
        }

        public Criteria andUserProvinceIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_province_id <=", value, "userProvinceId");
            return (Criteria) this;
        }

        public Criteria andUserProvinceIdIn(List<Integer> values) {
            addCriterion("user_province_id in", values, "userProvinceId");
            return (Criteria) this;
        }

        public Criteria andUserProvinceIdNotIn(List<Integer> values) {
            addCriterion("user_province_id not in", values, "userProvinceId");
            return (Criteria) this;
        }

        public Criteria andUserProvinceIdBetween(Integer value1, Integer value2) {
            addCriterion("user_province_id between", value1, value2, "userProvinceId");
            return (Criteria) this;
        }

        public Criteria andUserProvinceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_province_id not between", value1, value2, "userProvinceId");
            return (Criteria) this;
        }

        public Criteria andUserCityIdIsNull() {
            addCriterion("user_city_id is null");
            return (Criteria) this;
        }

        public Criteria andUserCityIdIsNotNull() {
            addCriterion("user_city_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserCityIdEqualTo(Integer value) {
            addCriterion("user_city_id =", value, "userCityId");
            return (Criteria) this;
        }

        public Criteria andUserCityIdNotEqualTo(Integer value) {
            addCriterion("user_city_id <>", value, "userCityId");
            return (Criteria) this;
        }

        public Criteria andUserCityIdGreaterThan(Integer value) {
            addCriterion("user_city_id >", value, "userCityId");
            return (Criteria) this;
        }

        public Criteria andUserCityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_city_id >=", value, "userCityId");
            return (Criteria) this;
        }

        public Criteria andUserCityIdLessThan(Integer value) {
            addCriterion("user_city_id <", value, "userCityId");
            return (Criteria) this;
        }

        public Criteria andUserCityIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_city_id <=", value, "userCityId");
            return (Criteria) this;
        }

        public Criteria andUserCityIdIn(List<Integer> values) {
            addCriterion("user_city_id in", values, "userCityId");
            return (Criteria) this;
        }

        public Criteria andUserCityIdNotIn(List<Integer> values) {
            addCriterion("user_city_id not in", values, "userCityId");
            return (Criteria) this;
        }

        public Criteria andUserCityIdBetween(Integer value1, Integer value2) {
            addCriterion("user_city_id between", value1, value2, "userCityId");
            return (Criteria) this;
        }

        public Criteria andUserCityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_city_id not between", value1, value2, "userCityId");
            return (Criteria) this;
        }

        public Criteria andUserUserstateIdIsNull() {
            addCriterion("user_userstate_id is null");
            return (Criteria) this;
        }

        public Criteria andUserUserstateIdIsNotNull() {
            addCriterion("user_userstate_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserUserstateIdEqualTo(Integer value) {
            addCriterion("user_userstate_id =", value, "userUserstateId");
            return (Criteria) this;
        }

        public Criteria andUserUserstateIdNotEqualTo(Integer value) {
            addCriterion("user_userstate_id <>", value, "userUserstateId");
            return (Criteria) this;
        }

        public Criteria andUserUserstateIdGreaterThan(Integer value) {
            addCriterion("user_userstate_id >", value, "userUserstateId");
            return (Criteria) this;
        }

        public Criteria andUserUserstateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_userstate_id >=", value, "userUserstateId");
            return (Criteria) this;
        }

        public Criteria andUserUserstateIdLessThan(Integer value) {
            addCriterion("user_userstate_id <", value, "userUserstateId");
            return (Criteria) this;
        }

        public Criteria andUserUserstateIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_userstate_id <=", value, "userUserstateId");
            return (Criteria) this;
        }

        public Criteria andUserUserstateIdIn(List<Integer> values) {
            addCriterion("user_userstate_id in", values, "userUserstateId");
            return (Criteria) this;
        }

        public Criteria andUserUserstateIdNotIn(List<Integer> values) {
            addCriterion("user_userstate_id not in", values, "userUserstateId");
            return (Criteria) this;
        }

        public Criteria andUserUserstateIdBetween(Integer value1, Integer value2) {
            addCriterion("user_userstate_id between", value1, value2, "userUserstateId");
            return (Criteria) this;
        }

        public Criteria andUserUserstateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_userstate_id not between", value1, value2, "userUserstateId");
            return (Criteria) this;
        }

        public Criteria andUserRealnameIsNull() {
            addCriterion("user_realname is null");
            return (Criteria) this;
        }

        public Criteria andUserRealnameIsNotNull() {
            addCriterion("user_realname is not null");
            return (Criteria) this;
        }

        public Criteria andUserRealnameEqualTo(String value) {
            addCriterion("user_realname =", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameNotEqualTo(String value) {
            addCriterion("user_realname <>", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameGreaterThan(String value) {
            addCriterion("user_realname >", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("user_realname >=", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameLessThan(String value) {
            addCriterion("user_realname <", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameLessThanOrEqualTo(String value) {
            addCriterion("user_realname <=", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameLike(String value) {
            addCriterion("user_realname like", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameNotLike(String value) {
            addCriterion("user_realname not like", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameIn(List<String> values) {
            addCriterion("user_realname in", values, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameNotIn(List<String> values) {
            addCriterion("user_realname not in", values, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameBetween(String value1, String value2) {
            addCriterion("user_realname between", value1, value2, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameNotBetween(String value1, String value2) {
            addCriterion("user_realname not between", value1, value2, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserAgeIsNull() {
            addCriterion("user_age is null");
            return (Criteria) this;
        }

        public Criteria andUserAgeIsNotNull() {
            addCriterion("user_age is not null");
            return (Criteria) this;
        }

        public Criteria andUserAgeEqualTo(Integer value) {
            addCriterion("user_age =", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotEqualTo(Integer value) {
            addCriterion("user_age <>", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeGreaterThan(Integer value) {
            addCriterion("user_age >", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_age >=", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeLessThan(Integer value) {
            addCriterion("user_age <", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeLessThanOrEqualTo(Integer value) {
            addCriterion("user_age <=", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeIn(List<Integer> values) {
            addCriterion("user_age in", values, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotIn(List<Integer> values) {
            addCriterion("user_age not in", values, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeBetween(Integer value1, Integer value2) {
            addCriterion("user_age between", value1, value2, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("user_age not between", value1, value2, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserVocationIsNull() {
            addCriterion("user_vocation is null");
            return (Criteria) this;
        }

        public Criteria andUserVocationIsNotNull() {
            addCriterion("user_vocation is not null");
            return (Criteria) this;
        }

        public Criteria andUserVocationEqualTo(String value) {
            addCriterion("user_vocation =", value, "userVocation");
            return (Criteria) this;
        }

        public Criteria andUserVocationNotEqualTo(String value) {
            addCriterion("user_vocation <>", value, "userVocation");
            return (Criteria) this;
        }

        public Criteria andUserVocationGreaterThan(String value) {
            addCriterion("user_vocation >", value, "userVocation");
            return (Criteria) this;
        }

        public Criteria andUserVocationGreaterThanOrEqualTo(String value) {
            addCriterion("user_vocation >=", value, "userVocation");
            return (Criteria) this;
        }

        public Criteria andUserVocationLessThan(String value) {
            addCriterion("user_vocation <", value, "userVocation");
            return (Criteria) this;
        }

        public Criteria andUserVocationLessThanOrEqualTo(String value) {
            addCriterion("user_vocation <=", value, "userVocation");
            return (Criteria) this;
        }

        public Criteria andUserVocationLike(String value) {
            addCriterion("user_vocation like", value, "userVocation");
            return (Criteria) this;
        }

        public Criteria andUserVocationNotLike(String value) {
            addCriterion("user_vocation not like", value, "userVocation");
            return (Criteria) this;
        }

        public Criteria andUserVocationIn(List<String> values) {
            addCriterion("user_vocation in", values, "userVocation");
            return (Criteria) this;
        }

        public Criteria andUserVocationNotIn(List<String> values) {
            addCriterion("user_vocation not in", values, "userVocation");
            return (Criteria) this;
        }

        public Criteria andUserVocationBetween(String value1, String value2) {
            addCriterion("user_vocation between", value1, value2, "userVocation");
            return (Criteria) this;
        }

        public Criteria andUserVocationNotBetween(String value1, String value2) {
            addCriterion("user_vocation not between", value1, value2, "userVocation");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitIsNull() {
            addCriterion("user_headportrait is null");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitIsNotNull() {
            addCriterion("user_headportrait is not null");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitEqualTo(String value) {
            addCriterion("user_headportrait =", value, "userHeadportrait");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitNotEqualTo(String value) {
            addCriterion("user_headportrait <>", value, "userHeadportrait");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitGreaterThan(String value) {
            addCriterion("user_headportrait >", value, "userHeadportrait");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitGreaterThanOrEqualTo(String value) {
            addCriterion("user_headportrait >=", value, "userHeadportrait");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitLessThan(String value) {
            addCriterion("user_headportrait <", value, "userHeadportrait");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitLessThanOrEqualTo(String value) {
            addCriterion("user_headportrait <=", value, "userHeadportrait");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitLike(String value) {
            addCriterion("user_headportrait like", value, "userHeadportrait");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitNotLike(String value) {
            addCriterion("user_headportrait not like", value, "userHeadportrait");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitIn(List<String> values) {
            addCriterion("user_headportrait in", values, "userHeadportrait");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitNotIn(List<String> values) {
            addCriterion("user_headportrait not in", values, "userHeadportrait");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitBetween(String value1, String value2) {
            addCriterion("user_headportrait between", value1, value2, "userHeadportrait");
            return (Criteria) this;
        }

        public Criteria andUserHeadportraitNotBetween(String value1, String value2) {
            addCriterion("user_headportrait not between", value1, value2, "userHeadportrait");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltIsNull() {
            addCriterion("user_password_salt is null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltIsNotNull() {
            addCriterion("user_password_salt is not null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltEqualTo(String value) {
            addCriterion("user_password_salt =", value, "userPasswordSalt");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltNotEqualTo(String value) {
            addCriterion("user_password_salt <>", value, "userPasswordSalt");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltGreaterThan(String value) {
            addCriterion("user_password_salt >", value, "userPasswordSalt");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltGreaterThanOrEqualTo(String value) {
            addCriterion("user_password_salt >=", value, "userPasswordSalt");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltLessThan(String value) {
            addCriterion("user_password_salt <", value, "userPasswordSalt");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltLessThanOrEqualTo(String value) {
            addCriterion("user_password_salt <=", value, "userPasswordSalt");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltLike(String value) {
            addCriterion("user_password_salt like", value, "userPasswordSalt");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltNotLike(String value) {
            addCriterion("user_password_salt not like", value, "userPasswordSalt");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltIn(List<String> values) {
            addCriterion("user_password_salt in", values, "userPasswordSalt");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltNotIn(List<String> values) {
            addCriterion("user_password_salt not in", values, "userPasswordSalt");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltBetween(String value1, String value2) {
            addCriterion("user_password_salt between", value1, value2, "userPasswordSalt");
            return (Criteria) this;
        }

        public Criteria andUserPasswordSaltNotBetween(String value1, String value2) {
            addCriterion("user_password_salt not between", value1, value2, "userPasswordSalt");
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