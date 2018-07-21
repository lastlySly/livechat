package cn.lastlysly.service;

import cn.lastlysly.pojo.FriendgroupsSheet;

import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-21 11:33
 **/
public interface FriendsGroupService {


    /**
     * 添加好友分组
     * @param friendgroupsSheet
     * @return
     */
    boolean saveFriendsGroup(FriendgroupsSheet friendgroupsSheet);

    /**
     * 获取用户好友分组
     * @param userId 用户ID
     * @return
     */
    List<FriendgroupsSheet> listFriendsGroup(String userId);


}
