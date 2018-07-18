package cn.lastlysly.controller;

import cn.lastlysly.handler.MyCustomException;
import cn.lastlysly.pojo.FriendApplicationSheet;
import cn.lastlysly.pojo.MessagesSheet;
import cn.lastlysly.service.CustomMessageService;
import cn.lastlysly.service.CustomWebSocketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-27 14:34
 *
 * 聊天控制器
 **/
@Controller
public class WebsocketController {

    @Autowired
    private CustomWebSocketService customWebSocketService;

    @Autowired
    private CustomMessageService customMessageService;

    /**
     * 定时传输系统JVM使用情况
     */
    @Scheduled(fixedRate = 2000)
    public void sendServerJVM(){
        customWebSocketService.sendServerInfo();
    }

    /**
     * 单聊
     * @param messagesSheet
     */
    @MessageMapping("/singlechat")
    public void singleChat(MessagesSheet messagesSheet) throws MyCustomException {
        try {
            customWebSocketService.singleChat(messagesSheet);
        } catch (JsonProcessingException e) {
            throw new MyCustomException("用户信息序列化失败");
        }
    }

    /**
     * (此处未启用，实现于UserInfoReviseController.sendFriendApplication)
     * 好友申请( 业务层操作查询数据库有没有该用户向该好友发送的好友申请，如果有则仅修改这条申请记录)
     * @param friendApplicationSheet
     */
    @MessageMapping("/applyfriend")
    public void friendApply(FriendApplicationSheet friendApplicationSheet){
//        Subject subject = SecurityUtils.getSubject();
//        friendApplicationSheet.setFriendApplicationFrom(subject.getPrincipal().toString());

        boolean isSave =  customMessageService.saveFriendApplication(friendApplicationSheet);
        if (isSave){
            customWebSocketService.applyFriend(friendApplicationSheet);
        }
//        return new MyResult(1,"发送好友申请成功",null);
    }

}
