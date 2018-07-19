package cn.lastlysly.controller;

import cn.lastlysly.handler.MyCustomException;
import cn.lastlysly.myutils.CustomRedisTemplate;
import cn.lastlysly.myutils.MyResult;
import cn.lastlysly.pojo.*;
import cn.lastlysly.service.AddressService;
import cn.lastlysly.service.CustomMessageService;
import cn.lastlysly.service.CustomWebSocketService;
import cn.lastlysly.service.UserinfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-07-12 10:02
 **/
@Controller
@RequestMapping("/userinforevise")
public class UserInfoReviseController {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private AddressService addressService;

    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private CustomRedisTemplate customRedisTemplate;

    @Autowired
    private CustomWebSocketService customWebSocketService;

    @Autowired
    private CustomMessageService customMessageService;

    /**
     * 修改用户信息功能
     *
     * @param userinfoSheet
     * @return
     * @throws MyCustomException
     */
    @CrossOrigin
    @RequestMapping(value="/updateuserinfo",method = RequestMethod.POST)
    @ResponseBody
    public MyResult updateUserInfo(UserinfoSheet userinfoSheet) throws MyCustomException {
        boolean isSuccess = userinfoService.updateUserinfo(userinfoSheet);
        if (isSuccess){
            Subject subject = SecurityUtils.getSubject();
            String loginId = subject.getPrincipal().toString();
            UserinfoSheet resUser = userinfoService.getUserinfo(loginId);
            String userInfoKey = "userinfo:"+subject.getPrincipal().toString();
            String userInfoVal = null;
            try {
                userInfoVal = objectMapper.writeValueAsString(resUser);
                customRedisTemplate.redisSave(userInfoKey,userInfoVal,subject.getSession().getTimeout());
                return new MyResult(1,"修改成功",null);
            } catch (JsonProcessingException e) {
                throw new MyCustomException("用户信息序列化失败");
            }

        }
        return new MyResult(0,"修改失败",null);
    }


    /**
     * 好友申请( 业务层操作查询数据库有没有该用户向该好友发送的好友申请，如果有则仅修改这条申请记录)
     * @param friendApplicationSheet
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/sendfriendapplication",method = RequestMethod.POST)
    @ResponseBody
    public MyResult sendFriendApplication(FriendApplicationSheet friendApplicationSheet){
        Subject subject = SecurityUtils.getSubject();
        friendApplicationSheet.setFriendApplicationFrom(subject.getPrincipal().toString());
        boolean isSave =  customMessageService.saveFriendApplication(friendApplicationSheet);
        if (isSave){
            customWebSocketService.applyFriend(friendApplicationSheet);
        }
        return new MyResult(1,"发送请求成功",null);
    }

    /**
     * 处理好友请求
     * @param friendApplicationSheet
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/dealfriendapplication",method = RequestMethod.POST)
    @ResponseBody
    public MyResult dealFriendApplication(FriendApplicationSheet friendApplicationSheet){
        boolean isDeal = customMessageService.dealFriendApplication(friendApplicationSheet);
        if (isDeal){
            return new MyResult(1,"操作成功",null);
        }
        return new MyResult(0,"操作失败",null);

    }

    /**
     * 根据登陆ID获取与其相关的好友请求
     * @param
     * @param page
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/listfriendapplication",method = RequestMethod.POST)
    @ResponseBody
    public MyResult listApplicationByLoginId(@RequestParam(value = "page",required = false) Integer page){

        String loginId = SecurityUtils.getSubject().getPrincipal().toString();
        if(page == null){
            page = 1;
        }
        List<FriendApplicationSheet> friendApplicationSheetList = customMessageService.listApplicationByLoginId(page,loginId);
        return new MyResult(1,"查询好友申请信息成功",friendApplicationSheetList);
    }


    /**
     * 查询两个用户之间的聊天记录
     * @param loginId_2
     * @param page
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/listmessagebetweenusers",method = RequestMethod.POST)
    @ResponseBody
    public MyResult listMessageBetweenUsers(@RequestParam(value = "userLoginId",required = false) String loginId_1,
                                            @RequestParam(value = "friendLoginId",required = false) String loginId_2,
                                            @RequestParam(value = "page",required = false) Integer page){
        String loginId = SecurityUtils.getSubject().getPrincipal().toString();
        if(page == null){
            page = 1;
        }
        if(loginId_1.equals(loginId)){
            List<MessagesSheet> messagesSheetList =
                    customMessageService.listMessageByUserloginIdandFriendLoginId(loginId,loginId_2,page);
            return new MyResult(1,"查询聊天记录成功",messagesSheetList);
        }else{
            return new MyResult(0,"您的身份可能已过期，查询聊天记录失败",null);
        }

    }


    @CrossOrigin
    @RequestMapping(value = "/delunreadnum",method = RequestMethod.POST)
    @ResponseBody
    public MyResult removeUnreadInRedis(@RequestParam(value = "userLoginId") String userLoginId,
                                        @RequestParam(value = "friendLoginId") String friendLoginId){

        String redisKey = "unreadnumber:" + userLoginId + ":"+friendLoginId;
        boolean isDel = customRedisTemplate.redisDelByKey(redisKey);
        if (isDel){
            return new MyResult(1,"移除未读消息成功",null);
        }else{
            return new MyResult(0,"出错了，移除未读消息失败",null);
        }

    }



    /**
     * 查询国家列表
     * @returne
     */
    @CrossOrigin
    @RequestMapping(value="/listnation",method = RequestMethod.POST)
    @ResponseBody
    public MyResult listNation(){
        List<NationSheet> nationSheetList = addressService.listNation();
        return new MyResult(1,"查询国家列表成功",nationSheetList);
    }

    /**
     * 根据国家ID获取省份
     * @param nationId
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/listprovince",method = RequestMethod.POST)
    @ResponseBody
    public MyResult listProvince(@RequestParam(value = "nationID",required = false) Integer nationId){
        List<ProvinceSheet> provinceSheetList = addressService.listProvince(nationId);
        return new MyResult(1,"根据国家ID查询省份列表成功",provinceSheetList);
    }

    /**
     * 根据省份ID查询城市
     * @param provinceId
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/listcity",method = RequestMethod.POST)
    @ResponseBody
    public MyResult listCity(@RequestParam(value = "provinceId",required = false) Integer provinceId){
        List<CitySheet> citySheetList = addressService.listCity(provinceId);
        return new MyResult(1,"根据省份ID查询城市列表成功",citySheetList);
    }

}
