package cn.lastlysly.controller;

import cn.lastlysly.handler.MyCustomException;
import cn.lastlysly.handler.MyCustomLoginException;
import cn.lastlysly.myutils.CustomRedisTemplate;
import cn.lastlysly.myutils.MyResult;
import cn.lastlysly.pojo.FriendgroupsSheet;
import cn.lastlysly.pojo.UserinfoSheet;
import cn.lastlysly.service.UserinfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-27 16:05
 **/
@Controller
@RequestMapping("/userdeal")
public class UserInfoController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private CustomRedisTemplate customRedisTemplate;

    /**
     * 用户注册
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/userregister",method = RequestMethod.POST)
    @ResponseBody
    public MyResult userRegister(UserinfoSheet userinfoSheet, HttpServletRequest request) throws MyCustomLoginException {

//        logger.info("userinfoSheet = {},userLoginID={}",userinfoSheet.toString(),userinfoSheet.getUserLoginId());

        //设置用户主键id
        userinfoSheet.setUserId(UUID.randomUUID().toString());
        //设置用户默认头像（存于html项目中）
        userinfoSheet.setUserHeadportrait("img/default_head.png");
        /*设置盐值为用户登录账号*/
        userinfoSheet.setUserPasswordSalt(userinfoSheet.getUserLoginId());
        String credentialsSalt = userinfoSheet.getUserPasswordSalt();
        String password = userinfoSheet.getUserPassword();

        String newPassword = new SimpleHash("MD5",password,credentialsSalt,1024).toString();
        userinfoSheet.setUserPassword(newPassword);

        try {
            boolean isRegister = userinfoService.saveUserinfo(userinfoSheet);
            if (isRegister){
                logger.info("用户{}注册成功,通信地址为{}",userinfoSheet.getUserLoginId(),userinfoSheet.getUserId());
                return new MyResult(1,"注册成功",null);
            }
        }catch (Exception e){
            throw new MyCustomLoginException("注册失败：" + e.getMessage());
        }

        return new MyResult(0,"注册失败",userinfoSheet);

    }


    /**
     * 判断登录账号是否已被注册
     * @param userLoginId
     * @param request
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/loginidisuse",method = RequestMethod.POST)
    @ResponseBody
    public MyResult loginIdIsUse(String userLoginId,HttpServletRequest request){
//
//        Subject subject = SecurityUtils.getSubject();
//        Session session = subject.getSession();
//        logger.info("测试：{}，{},{}",subject.getPrincipal(),session.getLastAccessTime(),session.getId());

        if (userLoginId != null || userLoginId != ""){
            UserinfoSheet userinfoSheet = userinfoService.getUserinfoByLoginId(userLoginId);

            if (userinfoSheet != null){
                return new MyResult(0,"该账号已被注册",null);
            }
            return new MyResult(1,"该账号可用",null);
        }
        return new MyResult(0,"账号不能为空",null);
    }


    /**
     * 登陆
     * @param userinfoSheet
     * @param request
     * @return
     * @throws MyCustomLoginException
     */
    @CrossOrigin
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public MyResult userLogin(UserinfoSheet userinfoSheet,HttpServletRequest request) throws MyCustomLoginException {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userinfoSheet.getUserLoginId(),userinfoSheet.getUserPassword());
        try{
            subject.login(usernamePasswordToken);
            UserinfoSheet resUser = userinfoService.getUserinfo(userinfoSheet.getUserLoginId());

            //用户密码，盐值置空
            resUser.setUserPassword("");
            resUser.setUserPasswordSalt("");
            //获取用户session(如果当前用户没有常见session的话,true则创建一个并返回,false为返回null)
            Session session = subject.getSession();
            session.setAttribute("userInfo",resUser);
            //设置session超时时间
            session.setTimeout(1500000);
            //访问时间(创建session的时间和最后访问session的时间)
            logger.info("session获取主机号：{}，session获取sessionID：{}，创建session时间,最后访问session的时间：{}，更新会话时间：{}",
                    session.getHost(),session.getId(),session.getLastAccessTime(),session.getStartTimestamp());

            String redisKey = "online:"+subject.getPrincipal().toString();
            String redisVal = session.getId() + "";
//            session.stop();销毁session
            //上线用户保存于redis,
            customRedisTemplate.redisSave(redisKey,redisVal,session.getTimeout());

            //用户信息保存于redis(代替session)
            String userInfoKey = "userinfo:"+subject.getPrincipal().toString();
            String userInfoVal = objectMapper.writeValueAsString(resUser);
            customRedisTemplate.redisSave(userInfoKey,userInfoVal,session.getTimeout());

            return new MyResult(1,"登录成功",resUser);

        }catch (Exception e){
            throw new MyCustomLoginException("登陆失败，用户名或密码错误");
        }
    }

    /**
     * 获取登陆用户的信息
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/myuserinfo",method = RequestMethod.POST)
    @ResponseBody
    public MyResult getUserInfo() throws MyCustomException {
        Subject subject = SecurityUtils.getSubject();
        String redisKey = "userinfo:"+subject.getPrincipal().toString();
        String val = customRedisTemplate.redisGet(redisKey);
        try {
            UserinfoSheet userinfoSheet = objectMapper.readValue(val,UserinfoSheet.class);
            return new MyResult(1,"获取用户信息成功",userinfoSheet);
        } catch (IOException e) {
            throw new MyCustomException("用户信息序列化失败");
        }
    }

    /**
     * 根据登陆账号或者用户ID查询用户信息
     * @param userinfoSheet
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getuserinfobyuserid",method = RequestMethod.POST)
    @ResponseBody
    public MyResult getUserInfoByUserId(UserinfoSheet userinfoSheet){
        UserinfoSheet resUser = userinfoService.getUserInfoByUserIdOrLoginId(userinfoSheet);
        resUser.setUserPasswordSalt("");
        resUser.setUserPassword("");
        return new MyResult(1,"查询成功",resUser);
    }

    /**
     * 获取好友分组
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/listgroup",method = RequestMethod.POST)
    @ResponseBody
    public MyResult listGroup() throws MyCustomException {
        Subject subject = SecurityUtils.getSubject();
        String redisKey = "userinfo:"+subject.getPrincipal().toString();
        String val = customRedisTemplate.redisGet(redisKey);
        try {
            UserinfoSheet userinfoSheet = objectMapper.readValue(val,UserinfoSheet.class);

            List<FriendgroupsSheet> friendgroupsSheetList =  userinfoService.listFriendsGroup(userinfoSheet.getUserId());
            return new MyResult(1,"获取用户列表成功",friendgroupsSheetList);

        } catch (IOException e) {
            throw new MyCustomException("用户分组序列化失败");
        }
    }

}
