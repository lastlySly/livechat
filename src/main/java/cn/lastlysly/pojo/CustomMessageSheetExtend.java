package cn.lastlysly.pojo;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-19 11:08
 **/
public class CustomMessageSheetExtend {

    private MessagesSheet messagesSheet;
    private Integer unreadNum;

    public CustomMessageSheetExtend(MessagesSheet messagesSheet, Integer unreadNum) {
        this.messagesSheet = messagesSheet;
        this.unreadNum = unreadNum;
    }

    public CustomMessageSheetExtend() {
    }

    public MessagesSheet getMessagesSheet() {
        return messagesSheet;
    }

    public void setMessagesSheet(MessagesSheet messagesSheet) {
        this.messagesSheet = messagesSheet;
    }

    public Integer getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(Integer unreadNum) {
        this.unreadNum = unreadNum;
    }
}
