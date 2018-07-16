package cn.lastlysly.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FriendApplicationSheet {
    private Integer friendApplicationId;

    private String friendApplicationFrom;

    private String friendApplicationTo;

    private String friendApplicationRemark;

    private Integer friendApplicationGroup;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date friendApplicationTime;

    private String friendApplicationMessage;

    private String friendApplicationStatus;

    public Integer getFriendApplicationId() {
        return friendApplicationId;
    }

    public void setFriendApplicationId(Integer friendApplicationId) {
        this.friendApplicationId = friendApplicationId;
    }

    public String getFriendApplicationFrom() {
        return friendApplicationFrom;
    }

    public void setFriendApplicationFrom(String friendApplicationFrom) {
        this.friendApplicationFrom = friendApplicationFrom == null ? null : friendApplicationFrom.trim();
    }

    public String getFriendApplicationTo() {
        return friendApplicationTo;
    }

    public void setFriendApplicationTo(String friendApplicationTo) {
        this.friendApplicationTo = friendApplicationTo == null ? null : friendApplicationTo.trim();
    }

    public String getFriendApplicationRemark() {
        return friendApplicationRemark;
    }

    public void setFriendApplicationRemark(String friendApplicationRemark) {
        this.friendApplicationRemark = friendApplicationRemark == null ? null : friendApplicationRemark.trim();
    }

    public Integer getFriendApplicationGroup() {
        return friendApplicationGroup;
    }

    public void setFriendApplicationGroup(Integer friendApplicationGroup) {
        this.friendApplicationGroup = friendApplicationGroup;
    }

    public Date getFriendApplicationTime() {
        return friendApplicationTime;
    }

    public void setFriendApplicationTime(Date friendApplicationTime) {
        this.friendApplicationTime = friendApplicationTime;
    }

    public String getFriendApplicationMessage() {
        return friendApplicationMessage;
    }

    public void setFriendApplicationMessage(String friendApplicationMessage) {
        this.friendApplicationMessage = friendApplicationMessage == null ? null : friendApplicationMessage.trim();
    }

    public String getFriendApplicationStatus() {
        return friendApplicationStatus;
    }

    public void setFriendApplicationStatus(String friendApplicationStatus) {
        this.friendApplicationStatus = friendApplicationStatus == null ? null : friendApplicationStatus.trim();
    }
}