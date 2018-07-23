package cn.lastlysly.mapper;

import cn.lastlysly.pojo.CustomFriendsInfo;
import cn.lastlysly.pojo.FriendApplicationSheet;
import cn.lastlysly.pojo.MessagesSheet;
import cn.lastlysly.pojo.UserinfoSheet;

import java.util.List;
import java.util.Map;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-09 14:31
 **/
public interface CustomMapper {

    /**
     * 通过用户ID获取好友列表
     * @param map
     * @return
     */
    List<CustomFriendsInfo> selectCustomFriendsInfoByUserId(Map<String,String> map);

    /**
     * 通过用户ID和朋友ID获取该好友详细信息
     * @param map
     * @return
     */
    List<CustomFriendsInfo> selectFriendsInfoByUserIdandFriendId(Map<String,String> map);

    /**
     * 通过用户登录账户或用户昵称获取详细信息
     * @param map
     * @return
     */
    List<UserinfoSheet> selectNewFriendsByLoginIdOrNickname(Map<String,String> map);

    /**
     * 根据登陆ID获取与其相关的好友申请
     * @return
     */
    List<FriendApplicationSheet> listApplicationByLoginId(Map<String,String> map);

    /**
     * 根据发送方和接收方获取消息
     * @param map
     * @return
     */
    List<MessagesSheet> selectLastMessageByFromAndTo(Map<String,String> map);

    /**
     * 获取两个用户之间的聊天记录
     * @param map
     * @return
     */
    List<MessagesSheet> listMessageByUserloginidOrFriendLoginid(Map<String,String> map);

    /**
     * 删除好友
     * @param map
     * @return
     */
    int delFriendByUserloginidAndFriendLoginid(Map<String,String> map);

    /**
     * 删除消息记录
     * @param map
     * @return
     */
    int delMessagesByUserloginidAndFriendLoginid(Map<String,String> map);

}
