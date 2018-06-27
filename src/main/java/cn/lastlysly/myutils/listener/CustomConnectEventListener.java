package cn.lastlysly.myutils.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-27 13:56
 * 功能描述：springboot使用，连接监听器
 **/
@Component
public class CustomConnectEventListener implements ApplicationListener<SessionConnectEvent> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(SessionConnectEvent sessionConnectEvent) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(sessionConnectEvent.getMessage());
        logger.info("CustomConnectEventListener监听器事件 类型={},StompHeaderAccessor={}",
                stompHeaderAccessor.getCommand().getMessageType(),stompHeaderAccessor.toString());
    }
}
