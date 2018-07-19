package cn.lastlysly.myutils.listener;

import cn.lastlysly.handler.MyCustomException;
import cn.lastlysly.mapper.CustomMapper;
import cn.lastlysly.mapper.MessagesSheetMapper;
import cn.lastlysly.myutils.CustomRedisTemplate;
import cn.lastlysly.pojo.CustomMessageSheetExtend;
import cn.lastlysly.pojo.MessagesSheet;
import cn.lastlysly.service.CustomWebSocketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-27 14:06
 * 功能描述：springboot使用，订阅的监听器
 **/
@Component
public class CustomSubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CustomWebSocketService customWebSocketService;

    @Autowired
    private CustomRedisTemplate customRedisTemplate;

    @Autowired
    private CustomMapper customMapper;

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
//        logger.info("CustomSubscribeEventListener 订阅监听器 stompHeaderAccessor={}",stompHeaderAccessor.toString());
        String destination = stompHeaderAccessor.getDestination();

        Principal principal = stompHeaderAccessor.getUser();
        String loginId = principal.toString();
        logger.info("{}当前订阅了{}",loginId,destination);
        if(destination.equals("/mysystem/unread/" + loginId)){
            sendUnReadMessageToUser(loginId);
        }

    }

    /**
     * 推送未读消息
     */
    public void sendUnReadMessageToUser(String loginId){
        String redisKey = "unreadnumber:" + loginId + ":*";
//        List<String> vals = customRedisTemplate.redisGetValueByKeys(redisKey);
        Set<String> keys = customRedisTemplate.redisFuzzyQueryKeys(redisKey);
        for (String key : keys){
            String fromLoginId = key.split(":")[2];
            String redisDetailKey = "unreadnumber:" + loginId + ":"+fromLoginId;
            String unreadNum = customRedisTemplate.redisGet(redisDetailKey);
            Map<String,String> map = new HashMap<String,String>(16);
            map.put("fromLoginId",fromLoginId);
            map.put("toLoginId",loginId);
            List<MessagesSheet> messagesSheetList = customMapper.selectLastMessageByFromAndTo(map);
            if (messagesSheetList.size() > 0 ){
                CustomMessageSheetExtend customMessageSheetExtend = new CustomMessageSheetExtend(messagesSheetList.get(0),Integer.parseInt(unreadNum));
                customWebSocketService.pushUnreadMessage(customMessageSheetExtend);
            }


        }
    }

}
