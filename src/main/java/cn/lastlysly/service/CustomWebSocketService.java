package cn.lastlysly.service;

import cn.lastlysly.pojo.FriendApplicationSheet;
import cn.lastlysly.pojo.MessagesSheet;

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
    void singleChat(MessagesSheet messagesSheet);

    /**
     * 好友申请
     * @param friendApplicationSheet
     */
    void applyFriend(FriendApplicationSheet friendApplicationSheet);
}
