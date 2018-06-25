package cn.lastlysly.pojo;

import java.util.Date;

public class UserinfoSheet {
    private String userId;

    private String userLoginId;

    private String userNickname;

    private String userPassword;

    private Boolean userGender;

    private Date userBirthday;

    private String userTelephone;

    private String userEmail;

    private String userMotto;

    private String userSynopsis;

    private Integer userNationId;

    private Integer userProvinceId;

    private Integer userCityId;

    private Integer userUserstateId;

    private String userRealname;

    private Integer userAge;

    private String userVocation;

    private String userHeadportrait;

    private String userPasswordSalt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId == null ? null : userLoginId.trim();
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Boolean getUserGender() {
        return userGender;
    }

    public void setUserGender(Boolean userGender) {
        this.userGender = userGender;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone == null ? null : userTelephone.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserMotto() {
        return userMotto;
    }

    public void setUserMotto(String userMotto) {
        this.userMotto = userMotto == null ? null : userMotto.trim();
    }

    public String getUserSynopsis() {
        return userSynopsis;
    }

    public void setUserSynopsis(String userSynopsis) {
        this.userSynopsis = userSynopsis == null ? null : userSynopsis.trim();
    }

    public Integer getUserNationId() {
        return userNationId;
    }

    public void setUserNationId(Integer userNationId) {
        this.userNationId = userNationId;
    }

    public Integer getUserProvinceId() {
        return userProvinceId;
    }

    public void setUserProvinceId(Integer userProvinceId) {
        this.userProvinceId = userProvinceId;
    }

    public Integer getUserCityId() {
        return userCityId;
    }

    public void setUserCityId(Integer userCityId) {
        this.userCityId = userCityId;
    }

    public Integer getUserUserstateId() {
        return userUserstateId;
    }

    public void setUserUserstateId(Integer userUserstateId) {
        this.userUserstateId = userUserstateId;
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname == null ? null : userRealname.trim();
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserVocation() {
        return userVocation;
    }

    public void setUserVocation(String userVocation) {
        this.userVocation = userVocation == null ? null : userVocation.trim();
    }

    public String getUserHeadportrait() {
        return userHeadportrait;
    }

    public void setUserHeadportrait(String userHeadportrait) {
        this.userHeadportrait = userHeadportrait == null ? null : userHeadportrait.trim();
    }

    public String getUserPasswordSalt() {
        return userPasswordSalt;
    }

    public void setUserPasswordSalt(String userPasswordSalt) {
        this.userPasswordSalt = userPasswordSalt == null ? null : userPasswordSalt.trim();
    }
}