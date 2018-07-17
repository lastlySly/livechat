package cn.lastlysly.service.serviceimpl;

import cn.lastlysly.pojo.FriendApplicationSheet;
import cn.lastlysly.pojo.MessagesSheet;
import cn.lastlysly.service.CustomWebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-27 14:17
 * websocket简单消息模板，用来推送消息
 **/
@Service
public class CustomWebSocketServiceImpl implements CustomWebSocketService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 推送服务器的JVM负载，已用内存等消息
     */
    @Override
    public void sendServerInfo() {
        //获取可用处理器
        int processors = Runtime.getRuntime().availableProcessors();
        //获取空闲内存
        Long freeMem = Runtime.getRuntime().freeMemory();
        //获取最大内存
        Long maxMem = Runtime.getRuntime().maxMemory();

        String systemInfo = String.format("服务器可用处理器:%s; 虚拟机空闲内容大小: %s; 最大内存大小: %s",processors,freeMem,maxMem);
//        logger.info("系统JVM负载：{}",systemInfo);
        MessagesSheet messagesSheet = new MessagesSheet();
        messagesSheet.setMessagesPostmessages(systemInfo);
        simpMessagingTemplate.convertAndSend("/mysystem/server_info",messagesSheet);
    }

    /**
     * 单聊
     * @param messagesSheet
     */
    @Override
    public void singleChat(MessagesSheet messagesSheet) {
        simpMessagingTemplate.convertAndSend("/chat/single/" + messagesSheet.getMessagesToLoginid(),messagesSheet);
    }

    /**
     * 好友申请
     * @param friendApplicationSheet
     */
    @Override
    public void applyFriend(FriendApplicationSheet friendApplicationSheet) {
        simpMessagingTemplate.convertAndSend("/mysystem/applyfriend/" + friendApplicationSheet.getFriendApplicationTo(),friendApplicationSheet);
    }

    /**
     * 回复好友申请
     * @param friendApplicationSheet
     */
    @Override
    public void replyFriendApplication(FriendApplicationSheet friendApplicationSheet) {
        simpMessagingTemplate.convertAndSend("/mysystem/applyfriend/" + friendApplicationSheet.getFriendApplicationFrom(),friendApplicationSheet);
    }


}
