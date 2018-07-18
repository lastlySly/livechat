package cn.lastlysly.myutils.interceptor;

import cn.lastlysly.mapper.FriendsSheetMapper;
import cn.lastlysly.pojo.CustomFriendsInfo;
import cn.lastlysly.pojo.FriendsSheet;
import cn.lastlysly.pojo.FriendsSheetExample;
import cn.lastlysly.pojo.MessagesSheet;
import cn.lastlysly.service.CustomWebSocketService;
import cn.lastlysly.service.UserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-27 13:32
 * ChannelInterceptorAdapter过时
 * ChannelInterceptor
 **/
public class CustomSocketChannelInterceptor implements ChannelInterceptor {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 在消息被实际发送到频道之前调用
     * @param message
     * @param channel
     * @return
     */
    @Nullable
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        return message;
    }

    /**
     * 发送消息调用后立即调用
     * @param message
     * @param channel
     * @param sent
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        logger.info("CustomSocketChannelInterceptor - postSend");
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(message);
        if (stompHeaderAccessor.getCommand() == null) return;// 避免非stomp消息类型，例如心跳检测
        logger.info("CustomSocketChannelInterceptor - postSend:StompHeaderAccessor={}",stompHeaderAccessor.toString());

        String sessionId = stompHeaderAccessor.getSessionAttributes().get("sessionId").toString();
        //获取连接用户
        Principal principal = stompHeaderAccessor.getUser();
        String loginId = principal.toString();
        switch (stompHeaderAccessor.getCommand()){
            case CONNECT:

                logger.info("sessionId为{}的用户{}上线了",sessionId,loginId);
                break;
            case DISCONNECT:

                logger.info("sessionId为{}的用户下线了",sessionId);
                break;
            case SUBSCRIBE:
                //获取订阅的地址
                String destination = stompHeaderAccessor.getDestination();
                logger.info("sessionId为{}的用户订阅了{}",sessionId,destination);
                break;
            case UNSUBSCRIBE:
                logger.info("sessionId为{}的用户取消订阅==",sessionId);
                break;
            case ERROR:
                logger.info("发生错误");
                break;
        }
    }

    /**
     * 在完成发送之后进行调用，不管是否有异常发生，一般用于资源清理
     * @param message
     * @param channel
     * @param sent
     * @param ex
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, @Nullable Exception ex) {

    }




}
