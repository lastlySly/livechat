package cn.lastlysly.service.serviceimpl;

import cn.lastlysly.mapper.CustomMapper;
import cn.lastlysly.mapper.FriendApplicationSheetMapper;
import cn.lastlysly.mapper.FriendsSheetMapper;
import cn.lastlysly.mapper.MessagesSheetMapper;
import cn.lastlysly.myutils.CustomRedisTemplate;
import cn.lastlysly.pojo.*;
import cn.lastlysly.service.CustomMessageService;
import cn.lastlysly.service.CustomWebSocketService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private CustomWebSocketService customWebSocketService;

    @Autowired
    private CustomMapper customMapper;

    @Autowired
    private MessagesSheetMapper messagesSheetMapper;

    @Autowired
    private CustomRedisTemplate customRedisTemplate;

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
            friendApplicationSheet.setFriendApplicationRefuseMessage(" ");
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

        boolean isRefuse = false;
        Date replayTime = friendApplicationSheet.getFriendApplicationTime();
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
//                //可能存在拒绝后后再申请
//                friendApplicationSheet.setFriendApplicationRefuseMessage(" ");
                break;
            case "拒绝":
                isRefuse = true;
                break;
        }
        //置空，updateByExampleSelective不会修改被置空的属性
        friendApplicationSheet.setFriendApplicationGroup(null);
        friendApplicationSheet.setFriendApplicationMessage(null);
        friendApplicationSheet.setFriendApplicationRemark(null);
        friendApplicationSheet.setFriendApplicationTime(null);
        int row = friendApplicationSheetMapper.updateByExampleSelective(friendApplicationSheet,friendApplicationSheetExample);
        //如果被拒绝推送消息给发送者
        if(isRefuse){
            List<FriendApplicationSheet> listFriendApplicationSheet = friendApplicationSheetMapper.selectByExample(friendApplicationSheetExample);
            if(listFriendApplicationSheet.size() > 0){
                customWebSocketService.replyFriendApplication(listFriendApplicationSheet.get(0));
            }
        }

        if(row > 0){
            return true;
        }

        return false;
    }


    /**
     * 根据登陆ID获取与其相关的好友申请
     * @return
     */
    @Override
    public List<FriendApplicationSheet> listApplicationByLoginId(int page, String loginId) {

        Map<String,String> map = new HashMap<String,String>();
        map.put("loginId",loginId);
        PageHelper.startPage(page,20);
        List<FriendApplicationSheet> friendApplicationSheetList = customMapper.listApplicationByLoginId(map);
        if (friendApplicationSheetList.size() > 0 ){
            return friendApplicationSheetList;
        }
        return null;
    }

    /**
     * 保存聊天记录
     * @param messagesSheet
     * @return
     */
    @Override
    public boolean saveChatMessage(MessagesSheet messagesSheet) {

        int row = messagesSheetMapper.insert(messagesSheet);
        if(row > 0){
            return true;
        }
        return false;
    }

    /**
     * 获取两个用户之间的聊天记录（分页）
     * @param loginId_1 用户1
     * @param loginId_2 用户2
     * @return
     */
    @Override
    public List<MessagesSheet> listMessageByUserloginIdandFriendLoginId(String loginId_1,String loginId_2,int page) {
        Map<String,String> map = new HashMap<String,String>(16);
        map.put("userLoginId",loginId_1);
        map.put("friendLoginId",loginId_2);
        PageHelper.startPage(page,20);
        List<MessagesSheet> messagesSheetList = customMapper.listMessageByUserloginidOrFriendLoginid(map);
        if(messagesSheetList.size() > 0 ){
            return messagesSheetList;
        }
        return null;
    }


    /**
     * 据用户登陆Id获取其好友并判断是否在线
     * @param userLoginId
     * @return
     */
    @Override
    public List<FriendsSheet> listOnlineFriendsByUserId(String userLoginId) {

        List<FriendsSheet> olineFriendsSheetList = new ArrayList<>(16);
        FriendsSheetExample friendsSheetExample = new FriendsSheetExample();
        FriendsSheetExample.Criteria criteria = friendsSheetExample.createCriteria();
        criteria.andFriendsUserLoginidEqualTo(userLoginId);
        List<FriendsSheet> friendsSheetList = friendsSheetMapper.selectByExample(friendsSheetExample);
        if(friendsSheetList.size() > 0){
            for(FriendsSheet friendsSheet : friendsSheetList){
                String redisKey ="online:" + friendsSheet.getFriendsUserLoginid();
                boolean isExist = customRedisTemplate.redisHasKey(redisKey);
                if(isExist){
                    olineFriendsSheetList.add(friendsSheet);
                }
            }

            return olineFriendsSheetList;
        }
        return null;
    }
}
