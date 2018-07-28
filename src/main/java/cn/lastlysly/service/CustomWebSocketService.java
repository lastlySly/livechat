package cn.lastlysly.service;

import cn.lastlysly.pojo.CustomMessageSheetExtend;
import cn.lastlysly.pojo.FriendApplicationSheet;
import cn.lastlysly.pojo.FriendsSheet;
import cn.lastlysly.pojo.MessagesSheet;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-27 14:13
 * websocket简单消息模板，用来推送消息
 **/
public interface CustomWebSocketService {

    /**
     * 推送服务器的JVM负载，已用内存等消息
     */
    void sendServerInfo();

    /**
     * 单聊
     * @param messagesSheet
     */
    void singleChat(MessagesSheet messagesSheet) throws JsonProcessingException;

    /**
     * 好友申请
     * @param friendApplicationSheet
     */
    void applyFriend(FriendApplicationSheet friendApplicationSheet);

    /**
     * 回复好友申请
     * @param friendApplicationSheet
     */
    void replyFriendApplication(FriendApplicationSheet friendApplicationSheet);

    /**
     * 系统推送给指定用户的消息（好友上下线通知等）
     * @param messagesSheet
     */
    void adminPushTo(MessagesSheet messagesSheet);

    /**
     * 推送未读消息
     * @param customMessageSheetExtend
     */
    void pushUnreadMessage(CustomMessageSheetExtend customMessageSheetExtend);

//    /**
//     * 推送在其他机器被登录
//     * @param messagesSheet
//     */
//    void pushRepeatLogin(MessagesSheet messagesSheet);
//

//    /**
//     * 推送在线好友（登陆时已经在线的好友）
//     * @param friendsSheet
//     */
//    void pushOnlineFriend(FriendsSheet friendsSheet);

}
