package cn.lastlysly.service;

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
}
