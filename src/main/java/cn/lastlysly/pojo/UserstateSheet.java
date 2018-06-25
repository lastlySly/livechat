package cn.lastlysly.pojo;

public class UserstateSheet {
    private Integer userstateId;

    private String userstateName;

    public Integer getUserstateId() {
        return userstateId;
    }

    public void setUserstateId(Integer userstateId) {
        this.userstateId = userstateId;
    }

    public String getUserstateName() {
        return userstateName;
    }

    public void setUserstateName(String userstateName) {
        this.userstateName = userstateName == null ? null : userstateName.trim();
    }
}