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

    /**
     * 过用户ID和朋友ID获取该好友详细信息
     * @param customFriendsInfo
     * @return
     */
    CustomFriendsInfo getFriendsInfo(CustomFriendsInfo customFriendsInfo);

    /**
     * 查找新好友（通过用户登录账号或者用户昵称查询用户信息）
     * @param loginIdOrNickname
     * @return
     */
    List<UserinfoSheet> selectUserInfoByLoginIdOrNickname(String loginIdOrNickname);

    /**
     * 修改用户信息
     * @param userinfoSheet
     * @return
     */
    boolean updateUserinfo(UserinfoSheet userinfoSheet);

    /**
     * 修改好友信息（备注，分组）
     * @param friendsSheet
     * @return
     */
    boolean reviseFriendInfo(FriendsSheet friendsSheet);

    /**
     * 删除好友
     * @param friendLoginId
     * @param userLoginId
     * @return
     */
    boolean delFriend(String friendLoginId,String userLoginId);

}
