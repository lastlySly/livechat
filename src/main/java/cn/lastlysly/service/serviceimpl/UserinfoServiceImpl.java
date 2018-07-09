package cn.lastlysly.service.serviceimpl;

import cn.lastlysly.mapper.*;
import cn.lastlysly.myutils.MyResult;
import cn.lastlysly.pojo.*;
import cn.lastlysly.service.UserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-26 20:18
 * loginId 即为 username ，登陆账号
 *
 * 用户操作
 **/
@Service
public class UserinfoServiceImpl implements UserinfoService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserinfoSheetMapper userinfoSheetMapper;

    @Autowired
    private RolesSheetMapper rolesSheetMapper;

    @Autowired
    private PermissionSheetMapper permissionSheetMapper;

    @Autowired
    private FriendgroupsSheetMapper friendgroupsSheetMapper;

    @Autowired
    private FriendsSheetMapper friendsSheetMapper;

    @Autowired
    private CustomMapper customMapper;

    /**
     * 通过 账号（用户名） 获取用户信息
     * @param loginId 登陆账号，即username用户名
     * @return
     */
    @Override
    public UserinfoSheet getUserinfoByLoginId(String loginId) {
        UserinfoSheetExample userinfoSheetExample = new UserinfoSheetExample();
        UserinfoSheetExample.Criteria criteria = userinfoSheetExample.createCriteria();
        criteria.andUserLoginIdEqualTo(loginId);
        List<UserinfoSheet> userinfoSheetList = userinfoSheetMapper.selectByExample(userinfoSheetExample);
        if(userinfoSheetList.size() > 0){
            return userinfoSheetList.get(0);
        }
        return null;
    }

    /**
     * 通过账号（用户名）获取用户角色
     * @param loginId 登陆账号，即username用户名
     * @return
     */
    @Override
    public List<String> listRolesByLoginId(String loginId) {
        RolesSheetExample rolesSheetExample = new RolesSheetExample();
        RolesSheetExample.Criteria criteria = rolesSheetExample.createCriteria();
        criteria.andRolesUsernameEqualTo(loginId);
        List<RolesSheet> rolesSheetsList = rolesSheetMapper.selectByExample(rolesSheetExample);
        List<String> rolesList = new ArrayList<String>();
        if (rolesSheetsList.size() > 0){
            for (RolesSheet rolesSheet : rolesSheetsList){
                rolesList.add(rolesSheet.getRolesName());
            }
            return rolesList;
        }

        return null;
    }

    /**
     * 通过账号（用户名）获取用户权限
     * @param loginId 登陆账号，即username用户名
     * @return
     */
    @Override
    public List<String> listPermissionsByLoginId(String loginId) {
        List<String> permissionList = new ArrayList<String>();
        /*通过用户名获取角色*/
        List<String> rolesList = listRolesByLoginId(loginId);

        /*通过角色名查询权限*/
        if (rolesList.size() > 0){
            PermissionSheetExample permissionSheetExample = new PermissionSheetExample();
            PermissionSheetExample.Criteria criteria = permissionSheetExample.createCriteria();
            criteria.andPermissionRolenameIn(rolesList);
            List<PermissionSheet> permissionSheetList = permissionSheetMapper.selectByExample(permissionSheetExample);
            if (permissionSheetList.size() > 0){
               for (PermissionSheet permissionSheet : permissionSheetList){
                   permissionList.add(permissionSheet.getPermissionName());
               }
               return permissionList;
            }
        }

        return null;
    }

    /**
     * 用户注册
     * @param userinfoSheet
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean saveUserinfo(UserinfoSheet userinfoSheet) {

        int insertUserRow = userinfoSheetMapper.insert(userinfoSheet);
        if (insertUserRow > 0){
            //为注册的用户添加角色身份
            RolesSheet rolesSheet = new RolesSheet();
            rolesSheet.setRolesUsername(userinfoSheet.getUserLoginId());
            rolesSheet.setRolesName("user");

            int insertRolesRow = rolesSheetMapper.insert(rolesSheet);
            if (insertRolesRow > 0){
                //创建用户默认好友分组
                FriendgroupsSheet friendgroupsSheet = new FriendgroupsSheet();
                friendgroupsSheet.setFriendgroupsName("好友");
                friendgroupsSheet.setFriendgroupsUserid(userinfoSheet.getUserId());
                friendgroupsSheetMapper.insert(friendgroupsSheet);
                friendgroupsSheet.setFriendgroupsName("家人");
                friendgroupsSheetMapper.insert(friendgroupsSheet);
                return true;
            }


        }
        return false;
    }

    /**
     * 根据用户登录账号查询用户信息
     * @param loginId
     * @return
     */
    @Override
    public UserinfoSheet getUserinfo(String loginId) {
        UserinfoSheetExample userinfoSheetExample = new UserinfoSheetExample();
        UserinfoSheetExample.Criteria criteria = userinfoSheetExample.createCriteria();
        criteria.andUserLoginIdEqualTo(loginId);
        List<UserinfoSheet> userinfoSheetList = userinfoSheetMapper.selectByExample(userinfoSheetExample);
        if (userinfoSheetList.size() > 0){
            UserinfoSheet resUser = userinfoSheetList.get(0);
            return resUser;
        }
        return null;
    }

    /**
     * 用户添加好友分组
     * @param friendgroupsSheet
     * @return
     */
    @Override
    public boolean saveFriendsGroup(FriendgroupsSheet friendgroupsSheet) {

        int row = friendgroupsSheetMapper.insert(friendgroupsSheet);
        if (row > 0){
            return true;
        }
        return false;
    }

    /**
     * 获取用户的分组
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<FriendgroupsSheet> listFriendsGroup(String userId) {
        FriendgroupsSheetExample friendgroupsSheetExample = new FriendgroupsSheetExample();
        FriendgroupsSheetExample.Criteria criteria = friendgroupsSheetExample.createCriteria();
        criteria.andFriendgroupsUseridEqualTo(userId);
        List<FriendgroupsSheet> friendgroupsSheetList = friendgroupsSheetMapper.selectByExample(friendgroupsSheetExample);
        return friendgroupsSheetList;
    }

    /**
     * 根据用户账号或者用户ID查询用户
     * @param userinfoSheet
     * @return
     */
    @Override
    public UserinfoSheet getUserInfoByUserIdOrLoginId(UserinfoSheet userinfoSheet) {

        UserinfoSheetExample userinfoSheetExample = new UserinfoSheetExample();
        UserinfoSheetExample.Criteria criteria = userinfoSheetExample.createCriteria();
        if (userinfoSheet.getUserId() != null){
//            logger.info("这里判断用户ID不为空{}",userinfoSheet.getUserId());
            criteria.andUserIdEqualTo(userinfoSheet.getUserId());
        }
        if (userinfoSheet.getUserLoginId() != null){
//            logger.info("这里判断用户账号不为空{}",userinfoSheet.getUserLoginId());
            criteria.andUserLoginIdEqualTo(userinfoSheet.getUserLoginId());
        }
        List<UserinfoSheet> resUserList = userinfoSheetMapper.selectByExample(userinfoSheetExample);
        if(resUserList.size() > 0){
            UserinfoSheet resUser = resUserList.get(0);
            return resUser;
        }
        return null;
    }

    /**
     * 通过当前登录用户ID查询其好友列表
     * @param userinfoSheet
     * @return
     */
    @Override
    public List<CustomFriendsInfo> listFriends(UserinfoSheet userinfoSheet) {
        Map<String,String> map = new HashMap<String,String>(16);
        map.put("userId",userinfoSheet.getUserId());
        List<CustomFriendsInfo> customFriendsInfoList = customMapper.selectCustomFriendsInfoByUserId(map);
        if (customFriendsInfoList.size() > 0){
            return customFriendsInfoList;
        }
        return null;
    }


}
