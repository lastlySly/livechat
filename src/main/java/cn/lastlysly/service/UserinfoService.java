package cn.lastlysly.service;

import cn.lastlysly.pojo.CustomFriendsInfo;
import cn.lastlysly.pojo.FriendgroupsSheet;
import cn.lastlysly.pojo.FriendsSheet;
import cn.lastlysly.pojo.UserinfoSheet;

import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-26 19:49
 *
 * loginId 即为 username ，登陆账号
 **/
public interface UserinfoService {
    UserinfoSheet getUserinfoByLoginId(String loginId);

    List<String> listRolesByLoginId(String loginId);

    List<String> listPermissionsByLoginId(String loginId);

    /**
     * 用户注册
     * @param userinfoSheet
     * @return
     */
    boolean saveUserinfo(UserinfoSheet userinfoSheet);

    /**
     * 根据用户登录账号查询用户信息
     * @param loginId
     * @return
     */
    UserinfoSheet getUserinfo(String loginId);

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

    /**
     * 通过用户账号或者用户ID查询用户信息
     * @param userinfoSheet
     * @return
     */
    UserinfoSheet getUserInfoByUserIdOrLoginId(UserinfoSheet userinfoSheet);

    /**
     *通过用户id查询其好友列表（包含详细信息）
     * @param userinfoSheet
     * @return
     */
    List<CustomFriendsInfo> listFriends(UserinfoSheet userinfoSheet);

}
