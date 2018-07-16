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
     * 发送好友申请
     * @param friendApplicationSheet
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/sendfriendapplication",method = RequestMethod.POST)
    @ResponseBody
    public MyResult sendFriendApplication(FriendApplicationSheet friendApplicationSheet){
        Subject subject = SecurityUtils.getSubject();
        friendApplicationSheet.setFriendApplicationFrom(subject.getPrincipal().toString());
        System.out.println(friendApplicationSheet.getFriendApplicationTo()+"11111");
        customWebSocketService.applyFriend(friendApplicationSheet);
        System.out.println(friendApplicationSheet+"22222");
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
