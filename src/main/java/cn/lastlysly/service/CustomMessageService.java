package cn.lastlysly.service;

import cn.lastlysly.pojo.FriendApplicationSheet;
import cn.lastlysly.pojo.FriendsSheet;
import cn.lastlysly.pojo.MessagesSheet;

import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-16 13:56
 * 处理消息
 **/

public interface CustomMessageService {
    /**
     * 好友申请消息保存
     * @param friendApplicationSheet
     * @return
     */
    boolean saveFriendApplication(FriendApplicationSheet friendApplicationSheet);

    /**
     * 好友申请的处理（同意，拒绝等）
     * @param friendApplicationSheet
     * @return
     */
    boolean dealFriendApplication(FriendApplicationSheet friendApplicationSheet);

    /**
     * 根据登陆ID获取与其相关的好友申请
     * @return
     */
    List<FriendApplicationSheet> listApplicationByLoginId(int page,String loginId);

    /**
     * 保存聊天记录
     * @param messagesSheet
     * @return
     */
    boolean saveChatMessage(MessagesSheet messagesSheet);

    /**
     * 获取两个用户之间的聊天记录（分页）
     * @param loginId_1 用户1
     * @param loginId_2 用户2
     * @return
     */
    List<MessagesSheet> listMessageByUserloginIdandFriendLoginId(String loginId_1,String loginId_2,int page);

    /**
     * 根据用户登陆Id获取其好友并判断是否在线
     * @param userLoginId
     * @return
     */
    List<FriendsSheet> listOnlineFriendsByUserId(String userLoginId);

}
