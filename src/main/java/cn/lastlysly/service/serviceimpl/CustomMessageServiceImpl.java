package cn.lastlysly.service.serviceimpl;

import cn.lastlysly.mapper.FriendApplicationSheetMapper;
import cn.lastlysly.mapper.FriendsSheetMapper;
import cn.lastlysly.pojo.FriendApplicationSheet;
import cn.lastlysly.pojo.FriendApplicationSheetExample;
import cn.lastlysly.pojo.FriendsSheet;
import cn.lastlysly.service.CustomMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-16 14:00
 **/
@Service
public class CustomMessageServiceImpl implements CustomMessageService {

    @Autowired
    private FriendApplicationSheetMapper friendApplicationSheetMapper;

    @Autowired
    private FriendsSheetMapper friendsSheetMapper;

    /**
     * 保存好友申请消息( 查询数据库有没有该用户向该好友发送的好友申请，如果有则仅修改这条申请记录)
     * @param friendApplicationSheet
     * @return
     */
    @Override
    public boolean saveFriendApplication(FriendApplicationSheet friendApplicationSheet) {
        FriendApplicationSheetExample friendApplicationSheetExample = new FriendApplicationSheetExample();
        FriendApplicationSheetExample.Criteria criteria = friendApplicationSheetExample.createCriteria();
        criteria.andFriendApplicationToEqualTo(friendApplicationSheet.getFriendApplicationTo());
        criteria.andFriendApplicationFromEqualTo(friendApplicationSheet.getFriendApplicationFrom());
        //查询数据库有没有该用户向该好友发送的好友申请，如果有则仅修改这条申请记录
        List<FriendApplicationSheet> friendApplicationSheetList = friendApplicationSheetMapper.selectByExample(friendApplicationSheetExample);
        int row = 0;
        if (friendApplicationSheetList.size() > 0){
            friendApplicationSheet.setFriendApplicationStatus(" ");
            row = friendApplicationSheetMapper.updateByExampleSelective(friendApplicationSheet,friendApplicationSheetExample);
        }else{
           row = friendApplicationSheetMapper.insert(friendApplicationSheet);
        }

        if (row>0){
            return true;
        }
        return false;
    }

    /**
     * 好友申请的处理（同意，拒绝等）
     * @param friendApplicationSheet
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean dealFriendApplication(FriendApplicationSheet friendApplicationSheet) {

        FriendApplicationSheetExample friendApplicationSheetExample = new FriendApplicationSheetExample();
        FriendApplicationSheetExample.Criteria criteria = friendApplicationSheetExample.createCriteria();
        criteria.andFriendApplicationFromEqualTo(friendApplicationSheet.getFriendApplicationFrom());
        criteria.andFriendApplicationToEqualTo(friendApplicationSheet.getFriendApplicationTo());
        //设置请求状态
        switch (friendApplicationSheet.getFriendApplicationStatus()){
            case "同意":
                //添加其为自己好友
                FriendsSheet friendsSheet = new FriendsSheet();
                friendsSheet.setFriendsFriendgroupsid(friendApplicationSheet.getFriendApplicationGroup());
                friendsSheet.setFriendsFriendLoginid(friendApplicationSheet.getFriendApplicationFrom());
                friendsSheet.setFriendsRemarks(friendApplicationSheet.getFriendApplicationRemark());
                friendsSheet.setFriendsUserLoginid(friendApplicationSheet.getFriendApplicationTo());
                int myInsertRow = friendsSheetMapper.insert(friendsSheet);

                //获取对方发送的好友请求信息
                List<FriendApplicationSheet> getFriendApplicationSheet = friendApplicationSheetMapper.selectByExample(friendApplicationSheetExample);
                //将自己添加为其好友
                FriendsSheet itFriendsSheet = new FriendsSheet();
                itFriendsSheet.setFriendsUserLoginid(getFriendApplicationSheet.get(0).getFriendApplicationFrom());
                itFriendsSheet.setFriendsFriendLoginid(getFriendApplicationSheet.get(0).getFriendApplicationTo());
                itFriendsSheet.setFriendsFriendgroupsid(getFriendApplicationSheet.get(0).getFriendApplicationGroup());
                itFriendsSheet.setFriendsRemarks(getFriendApplicationSheet.get(0).getFriendApplicationRemark());
                int itInsertRow = friendsSheetMapper.insert(itFriendsSheet);
                break;
            case "拒绝":
                break;
        }
        //置空，updateByExampleSelective不会修改被置空的属性
        friendApplicationSheet.setFriendApplicationGroup(null);
        friendApplicationSheet.setFriendApplicationMessage(null);
        friendApplicationSheet.setFriendApplicationRemark(null);
        friendApplicationSheet.setFriendApplicationRemark(null);
        int row = friendApplicationSheetMapper.updateByExampleSelective(friendApplicationSheet,friendApplicationSheetExample);
        if(row > 0){
            return true;
        }

        return false;
    }
}
