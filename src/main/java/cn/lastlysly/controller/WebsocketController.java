package cn.lastlysly.controller;

import cn.lastlysly.pojo.MessagesSheet;
import cn.lastlysly.service.CustomWebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 定时传输系统JVM使用情况
     */
    @Scheduled(fixedRate = 2000)
    public void sendServerJVM(){
        customWebSocketService.sendServerInfo();
    }

    @MessageMapping("/singlechat")
    public void singleChat(MessagesSheet messagesSheet){
        System.out.println(messagesSheet.getMessagesFromUserid()+":"+messagesSheet.getMessagesToUserid()+":"+messagesSheet.getMessagesPostmessages());
        customWebSocketService.singleChat(messagesSheet);
    }

}
