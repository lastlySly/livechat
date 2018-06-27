package cn.lastlysly.controller;

import cn.lastlysly.service.CustomWebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Scheduled(fixedRate = 2000)
    public void sendServerJVM(){
        customWebSocketService.sendServerInfo();
    }

}
