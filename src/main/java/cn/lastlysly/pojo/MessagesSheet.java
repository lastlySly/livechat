package cn.lastlysly.pojo;

import java.util.Date;

public class MessagesSheet {
    private Integer messagesId;

    private Boolean messagesStatus;

    private Date messagesTime;

    private Integer messagesTypeid;

    private String messagesFromLoginid;

    private String messagesToLoginid;

    private String messagesPostmessages;

    public Integer getMessagesId() {
        return messagesId;
    }

    public void setMessagesId(Integer messagesId) {
        this.messagesId = messagesId;
    }

    public Boolean getMessagesStatus() {
        return messagesStatus;
    }

    public void setMessagesStatus(Boolean messagesStatus) {
        this.messagesStatus = messagesStatus;
    }

    public Date getMessagesTime() {
        return messagesTime;
    }

    public void setMessagesTime(Date messagesTime) {
        this.messagesTime = messagesTime;
    }

    public Integer getMessagesTypeid() {
        return messagesTypeid;
    }

    public void setMessagesTypeid(Integer messagesTypeid) {
        this.messagesTypeid = messagesTypeid;
    }

    public String getMessagesFromLoginid() {
        return messagesFromLoginid;
    }

    public void setMessagesFromLoginid(String messagesFromLoginid) {
        this.messagesFromLoginid = messagesFromLoginid == null ? null : messagesFromLoginid.trim();
    }

    public String getMessagesToLoginid() {
        return messagesToLoginid;
    }

    public void setMessagesToLoginid(String messagesToLoginid) {
        this.messagesToLoginid = messagesToLoginid == null ? null : messagesToLoginid.trim();
    }

    public String getMessagesPostmessages() {
        return messagesPostmessages;
    }

    public void setMessagesPostmessages(String messagesPostmessages) {
        this.messagesPostmessages = messagesPostmessages == null ? null : messagesPostmessages.trim();
    }
}