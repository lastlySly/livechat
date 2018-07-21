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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
