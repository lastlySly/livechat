package cn.lastlysly.myutils.listener;

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
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-18 19:19
 **/
@Component
public class CustomDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {
    @Autowired
    private FriendsSheetMapper friendsSheetMapper;

    @Autowired
    private CustomWebSocketService customWebSocketService;

    @Autowired
    private UserinfoService userinfoService;


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
        Principal principal = stompHeaderAccessor.getUser();
        String loginId = principal.toString();
        //推送下线通知
        pushOnlineOrOffline(loginId,"下线");
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
