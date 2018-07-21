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
    FriendgroupsSheet saveFriendsGroup(FriendgroupsSheet friendgroupsSheet);

    /**
     * 获取用户好友分组
     * @param userId 用户ID
     * @return
     */
    List<FriendgroupsSheet> listFriendsGroup(String userId);

    /**
     * 根据用户登陆Id和分组Id修改分组名称
     * @param loginId
     * @param groupId
     * @param groupName
     * @return
     */
    boolean renameGroup(String loginId,Integer groupId,String groupName);

    /**
     * 删除分组
     * @param friendgroupsSheet
     * @return
     */
    boolean delGroup(FriendgroupsSheet friendgroupsSheet);


    /**
     * 根据用户登陆Id和分组Id查询分组
     * @param friendgroupsSheet
     * @return
     */
    FriendgroupsSheet getGroupByLoginIdAndGroupId(FriendgroupsSheet friendgroupsSheet);

}
