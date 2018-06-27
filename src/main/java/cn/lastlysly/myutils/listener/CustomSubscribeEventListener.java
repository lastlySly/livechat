package cn.lastlysly.myutils.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-27 14:06
 * 功能描述：springboot使用，订阅的监听器
 **/
@Component
public class CustomSubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 在事件触发的时候调用这个方法
     *
     * StompHeaderAccessor  简单消息传递协议中处理消息头的基类，
     * 通过这个类，可以获取消息类型(例如:发布订阅，建立连接断开连接)，会话id等
     *
     */
    @Override
    public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
        logger.info("CustomSubscribeEventListener 订阅监听器 stompHeaderAccessor={}",stompHeaderAccessor.toString());
    }
}
