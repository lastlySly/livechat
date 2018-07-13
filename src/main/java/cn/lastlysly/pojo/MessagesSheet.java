package cn.lastlysly.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MessagesSheet {
    private Integer messagesId;

    private Boolean messagesStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date messagesTime;

    private Integer messagesTypeid;

    private String messagesFromUserid;

    private String messagesToUserid;

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

    public String getMessagesFromUserid() {
        return messagesFromUserid;
    }

    public void setMessagesFromUserid(String messagesFromUserid) {
        this.messagesFromUserid = messagesFromUserid == null ? null : messagesFromUserid.trim();
    }

    public String getMessagesToUserid() {
        return messagesToUserid;
    }

    public void setMessagesToUserid(String messagesToUserid) {
        this.messagesToUserid = messagesToUserid == null ? null : messagesToUserid.trim();
    }

    public String getMessagesPostmessages() {
        return messagesPostmessages;
    }

    public void setMessagesPostmessages(String messagesPostmessages) {
        this.messagesPostmessages = messagesPostmessages == null ? null : messagesPostmessages.trim();
    }
}