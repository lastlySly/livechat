package cn.lastlysly.mapper;

import cn.lastlysly.pojo.CustomFriendsInfo;

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
}
