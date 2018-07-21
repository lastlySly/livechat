package cn.lastlysly.service.serviceimpl;

import cn.lastlysly.mapper.FriendgroupsSheetMapper;
import cn.lastlysly.pojo.FriendgroupsSheet;
import cn.lastlysly.pojo.FriendgroupsSheetExample;
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


}
