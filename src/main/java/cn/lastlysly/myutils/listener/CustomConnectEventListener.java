package cn.lastlysly.myutils.listener;

import cn.lastlysly.mapper.FriendsSheetMapper;
import cn.lastlysly.myutils.CustomRedisTemplate;
import cn.lastlysly.pojo.CustomFriendsInfo;
import cn.lastlysly.pojo.FriendsSheet;
import cn.lastlysly.pojo.FriendsSheetExample;
import cn.lastlysly.pojo.MessagesSheet;
import cn.lastlysly.service.CustomWebSocketService;
import cn.lastlysly.service.UserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-27 13:56
 * 功能描述：springboot使用，连接监听器
 **/
@Component
public class CustomConnectEventListener implements ApplicationListener<SessionConnectEvent> {

    @Autowired
    private FriendsSheetMapper friendsSheetMapper;

    @Autowired
    private CustomWebSocketService customWebSocketService;

    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private CustomRedisTemplate customRedisTemplate;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(SessionConnectEvent sessionConnectEvent) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(sessionConnectEvent.getMessage());
//        logger.info("CustomConnectEventListener监听器事件 类型={},StompHeaderAccessor={}",
//                stompHeaderAccessor.getCommand().getMessageType(),stompHeaderAccessor.toString());
        Principal principal = stompHeaderAccessor.getUser();
        String sessionId = stompHeaderAccessor.getSessionAttributes().get("sessionId").toString();
        String loginId = principal.toString();
        String redisKey ="online:" + loginId;
        boolean isExist = customRedisTemplate.redisHasKey(redisKey);
        if(!isExist){
            customRedisTemplate.redisSave(redisKey,sessionId);
        }
        //推送上线通知
        pushOnlineOrOffline(loginId,"上线");

    }

    /**
     * 通过登陆ID查询添加了自己的好友，并向其推送自己的上下线通知
     * @param loginId
     */
    public void pushOnlineOrOffline(String loginId,String onLineOrOffLine){

        FriendsSheetExample friendsSheetExample = new FriendsSheetExample();
        FriendsSheetExample.Criteria criteria = friendsSheetExample.createCriteria();
        criteria.andFriendsFriendLoginidEqualTo(loginId);
        List<FriendsSheet> friendsSheetList = friendsSheetMapper.selectByExample(friendsSheetExample);

        if(friendsSheetList.size() > 0){
            for (FriendsSheet friendsSheet : friendsSheetList){

                CustomFriendsInfo customFriendsInfo = new CustomFriendsInfo();
                customFriendsInfo.setCustomFriendsUserId(friendsSheet.getFriendsUserLoginid());
                customFriendsInfo.setCustomFriendsFriendsId(loginId);
                //获取自己在其好友那边的信息（备注之类的）
                CustomFriendsInfo getCustomFriendsInfo = userinfoService.getFriendsInfo(customFriendsInfo);

                if(getCustomFriendsInfo != null){
                    MessagesSheet messagesSheet = new MessagesSheet();
                    messagesSheet.setMessagesToLoginid(friendsSheet.getFriendsUserLoginid());

                    String messageStr = getCustomFriendsInfo.getCustomFriendsRemark() + "("+loginId +
                            ")" + onLineOrOffLine + "了。";
                    messagesSheet.setMessagesPostmessages(messageStr);
                    messagesSheet.setMessagesFromLoginid(friendsSheet.getFriendsFriendLoginid());
                    messagesSheet.setMessagesTypeid(5);

                    Date date = new Date();//获得系统时间.
                    String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//将时间格式转换成符合Timestamp要求的格式.
                    Timestamp cTime =Timestamp.valueOf(nowTime);
                    messagesSheet.setMessagesTime(cTime);

                    //推送
                    customWebSocketService.adminPushTo(messagesSheet);

                }

            }
        }
    }

}
