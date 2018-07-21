package cn.lastlysly.service.serviceimpl;

import cn.lastlysly.mapper.FriendApplicationSheetMapper;
import cn.lastlysly.mapper.FriendgroupsSheetMapper;
import cn.lastlysly.mapper.FriendsSheetMapper;
import cn.lastlysly.pojo.*;
import cn.lastlysly.service.FriendsGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-21 11:33
 **/
@Service
public class FriendsGroupServiceImpl implements FriendsGroupService {

    @Autowired
    private FriendgroupsSheetMapper friendgroupsSheetMapper;

    @Autowired
    private FriendsSheetMapper friendsSheetMapper;

    @Autowired
    private FriendApplicationSheetMapper friendApplicationSheetMapper;
    /**
     * 用户添加好友分组
     * @param friendgroupsSheet
     * @return
     */
    @Override
    public FriendgroupsSheet saveFriendsGroup(FriendgroupsSheet friendgroupsSheet) {
        friendgroupsSheet.setFriendgroupsGrade(2);
        int row = friendgroupsSheetMapper.insert(friendgroupsSheet);
        if (row > 0){
            FriendgroupsSheetExample friendgroupsSheetExample = new FriendgroupsSheetExample();
            FriendgroupsSheetExample.Criteria criteria = friendgroupsSheetExample.createCriteria();
            criteria.andFriendgroupsNameEqualTo(friendgroupsSheet.getFriendgroupsName());
            criteria.andFriendgroupsUserLoginidEqualTo(friendgroupsSheet.getFriendgroupsUserLoginid());
            List<FriendgroupsSheet> friendgroupsSheetList = friendgroupsSheetMapper.selectByExample(friendgroupsSheetExample);
            if(friendgroupsSheetList.size() > 0){
                FriendgroupsSheet res = friendgroupsSheetList.get(0);
                for(FriendgroupsSheet friendgroupsSheet1 : friendgroupsSheetList){
                    if(friendgroupsSheet1.getFriendgroupsId() > res.getFriendgroupsId()){
                        res = friendgroupsSheet1;
                    }
                }
                return res;
            }
        }
        return null;
    }

    /**
     * 获取用户的分组
     * @param userLoginId 用户登陆ID
     * @return
     */
    @Override
    public List<FriendgroupsSheet> listFriendsGroup(String userLoginId) {
        FriendgroupsSheetExample friendgroupsSheetExample = new FriendgroupsSheetExample();
        FriendgroupsSheetExample.Criteria criteria = friendgroupsSheetExample.createCriteria();
        criteria.andFriendgroupsUserLoginidEqualTo(userLoginId);
        List<FriendgroupsSheet> friendgroupsSheetList = friendgroupsSheetMapper.selectByExample(friendgroupsSheetExample);
        return friendgroupsSheetList;
    }

    /**
     * 根据用户登陆Id和分组Id修改分组名称
     * @param loginId
     * @param groupId
     * @param groupName
     * @return
     */
    @Override
    public boolean renameGroup(String loginId, Integer groupId, String groupName) {

        FriendgroupsSheet friendgroupsSheet = new FriendgroupsSheet();
        friendgroupsSheet.setFriendgroupsName(groupName);
        FriendgroupsSheetExample friendgroupsSheetExample = new FriendgroupsSheetExample();
        FriendgroupsSheetExample.Criteria criteria = friendgroupsSheetExample.createCriteria();
        criteria.andFriendgroupsUserLoginidEqualTo(loginId);
        criteria.andFriendgroupsIdEqualTo(groupId);

        int row = friendgroupsSheetMapper.updateByExampleSelective(friendgroupsSheet,friendgroupsSheetExample);
        if(row > 0){
            return true;
        }

        return false;
    }

    /**
     * 删除分组
     * @param friendgroupsSheet
     * @return
     */
    @Override
    public boolean delGroup(FriendgroupsSheet friendgroupsSheet) {


        //一，先将该分组下的好友放入默认分组
        //1,查询该用户所有分组，取得其第一个默认分组
        FriendgroupsSheetExample friendgroupsSheetExample = new FriendgroupsSheetExample();
        FriendgroupsSheetExample.Criteria criteria = friendgroupsSheetExample.createCriteria();
        criteria.andFriendgroupsUserLoginidEqualTo(friendgroupsSheet.getFriendgroupsUserLoginid());
        List<FriendgroupsSheet> friendgroupsSheetList = friendgroupsSheetMapper.selectByExample(friendgroupsSheetExample);
        FriendgroupsSheet defaultGroup = new FriendgroupsSheet();
        if(friendgroupsSheetList.size() > 0){
            defaultGroup = friendgroupsSheetList.get(0);
            for (FriendgroupsSheet fgs : friendgroupsSheetList){
                if(fgs.getFriendgroupsId() < defaultGroup.getFriendgroupsId()){
                    defaultGroup = fgs;
                }
            }
            Integer defaultGroupId = defaultGroup.getFriendgroupsId();

            //2,将要被删除得好友移入该默认分组
            //要修改得值
            FriendsSheet friendsSheet = new FriendsSheet();
            friendsSheet.setFriendsFriendgroupsid(defaultGroupId);
            //修改条件
            FriendsSheetExample friendsSheetExample = new FriendsSheetExample();
            FriendsSheetExample.Criteria friendsSheetCriteria = friendsSheetExample.createCriteria();
            friendsSheetCriteria.andFriendsUserLoginidEqualTo(friendgroupsSheet.getFriendgroupsUserLoginid());
            friendsSheetCriteria.andFriendsFriendgroupsidEqualTo(friendgroupsSheet.getFriendgroupsId());
            int frinendsRow = friendsSheetMapper.updateByExampleSelective(friendsSheet,friendsSheetExample);

            //3,修改好友申请表里得添加成功后分组
            //要修改的值
            FriendApplicationSheet friendApplicationSheet = new FriendApplicationSheet();
            friendApplicationSheet.setFriendApplicationGroup(defaultGroupId);
            //修改条件
            FriendApplicationSheetExample friendApplicationSheetExample = new FriendApplicationSheetExample();
            FriendApplicationSheetExample.Criteria fasCriteria = friendApplicationSheetExample.createCriteria();
            fasCriteria.andFriendApplicationFromEqualTo(friendgroupsSheet.getFriendgroupsUserLoginid());
            fasCriteria.andFriendApplicationGroupEqualTo(friendgroupsSheet.getFriendgroupsId());
            int applicationRow = friendApplicationSheetMapper.updateByExampleSelective(friendApplicationSheet,friendApplicationSheetExample);


            //二，删除分组
            FriendgroupsSheetExample delFriendgroupsSheetExample = new FriendgroupsSheetExample();
            FriendgroupsSheetExample.Criteria delCriteria = delFriendgroupsSheetExample.createCriteria();
            delCriteria.andFriendgroupsIdEqualTo(friendgroupsSheet.getFriendgroupsId());
            delCriteria.andFriendgroupsUserLoginidEqualTo(friendgroupsSheet.getFriendgroupsUserLoginid());
            int delRow = friendgroupsSheetMapper.deleteByExample(delFriendgroupsSheetExample);

            return true;

        }

        return false;
    }

    /**
     * 根据用户登陆Id和分组Id查询分组
     * @param friendgroupsSheet
     * @return
     */
    @Override
    public FriendgroupsSheet getGroupByLoginIdAndGroupId(FriendgroupsSheet friendgroupsSheet) {
        FriendgroupsSheetExample friendgroupsSheetExample = new FriendgroupsSheetExample();
        FriendgroupsSheetExample.Criteria criteria = friendgroupsSheetExample.createCriteria();
        criteria.andFriendgroupsIdEqualTo(friendgroupsSheet.getFriendgroupsId());
        criteria.andFriendgroupsUserLoginidEqualTo(friendgroupsSheet.getFriendgroupsUserLoginid());
        List<FriendgroupsSheet> friendgroupsSheetList = friendgroupsSheetMapper.selectByExample(friendgroupsSheetExample);
        if(friendgroupsSheetList.size() > 0){
            FriendgroupsSheet res = friendgroupsSheetList.get(0);
            return res;
        }
        return null;
    }


}
