package cn.lastlysly.controller;

import cn.lastlysly.handler.MyCustomException;
import cn.lastlysly.myutils.MyResult;
import cn.lastlysly.pojo.FriendgroupsSheet;
import cn.lastlysly.service.FriendsGroupService;
import cn.lastlysly.service.UserinfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-21 11:29
 **/

@Controller
@RequestMapping("groupingdeal")
public class FriendsGroupController {


    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private FriendsGroupService friendsGroupService;


    /**
     * 获取好友分组()
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/listgroup",method = RequestMethod.POST)
    @ResponseBody
    public MyResult listGroup() throws MyCustomException {
        Subject subject = SecurityUtils.getSubject();
        List<FriendgroupsSheet> friendgroupsSheetList =  friendsGroupService.listFriendsGroup(subject.getPrincipal().toString());
        return new MyResult(1,"获取用户列表成功",friendgroupsSheetList);

    }

    /**
     * 根据用户名和分组Id修改分组名称
     * @param groupId
     * @param groupName
     * @param loginId
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/renamegroup",method = RequestMethod.POST)
    @ResponseBody
    public MyResult renameGroup(@RequestParam(value = "groupId") Integer groupId,
                                @RequestParam(value = "groupName") String groupName,
                                @RequestParam(value = "loginId") String loginId){
        String userLoginId = SecurityUtils.getSubject().getPrincipal().toString();
        if(loginId.equals(userLoginId)){
            boolean isRename = friendsGroupService.renameGroup(loginId,groupId,groupName);
            if(isRename){
                return new MyResult(1,"修改成功",null);
            }

        }
        return new MyResult(0,"修改失败",null);
    }

    /**
     * 新建分组（该接口可简化，其中返回的数据已经不使用）
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "addnewgroup",method = RequestMethod.POST)
    @ResponseBody
    public MyResult addNewGroup(@RequestParam(value = "loginId") String loginId,
                                @RequestParam(value = "groupName") String groupName){
        String userLoginId = SecurityUtils.getSubject().getPrincipal().toString();
        if(userLoginId.equals(loginId)){
            FriendgroupsSheet friendgroupsSheet = new FriendgroupsSheet();
            friendgroupsSheet.setFriendgroupsName(groupName);
            friendgroupsSheet.setFriendgroupsUserLoginid(loginId);
            FriendgroupsSheet friendsGroup = friendsGroupService.saveFriendsGroup(friendgroupsSheet);
            if (friendsGroup != null){
                return new MyResult(1,"新建分组成功",friendsGroup);
            }
        }
        return new MyResult(0,"新建分组失败",null);
    }

    /**
     * 删除分组
     * @param loginId
     * @param
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "delgroup",method = RequestMethod.POST)
    @ResponseBody
    public MyResult delGroup(@RequestParam(value = "loginId") String loginId,
                             @RequestParam(value = "groupId") Integer groupId){

        FriendgroupsSheet friendgroupsSheet = new FriendgroupsSheet();
        friendgroupsSheet.setFriendgroupsUserLoginid(loginId);
        friendgroupsSheet.setFriendgroupsId(groupId);

        FriendgroupsSheet getThisFriendgroups = friendsGroupService.getGroupByLoginIdAndGroupId(friendgroupsSheet);
        if(getThisFriendgroups == null){
            return new MyResult(0,"删除失败,用户Id与分组Id不匹配",null);
        }
        if(getThisFriendgroups.getFriendgroupsGrade() == 1){
            return new MyResult(0,"删除失败,系统默认分组不可删除",null);
        }

        boolean isDel = friendsGroupService.delGroup(friendgroupsSheet);

        if(isDel){
            return new MyResult(1,"删除成功",null);
        }

        return new MyResult(0,"删除失败",null);
    }


}
