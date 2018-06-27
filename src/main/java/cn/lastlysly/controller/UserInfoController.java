package cn.lastlysly.controller;

import cn.lastlysly.handler.MyCustomLoginException;
import cn.lastlysly.myutils.MyResult;
import cn.lastlysly.pojo.UserinfoSheet;
import cn.lastlysly.service.UserinfoService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private UserinfoService userinfoService;

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
    @RequestMapping(value = "/loginidisuse")
    @ResponseBody
    public MyResult loginIdIsUse(String userLoginId,HttpServletRequest request){
        if (userLoginId != null || userLoginId != ""){
            UserinfoSheet userinfoSheet = userinfoService.getUserinfoByLoginId(userLoginId);

            if (userinfoSheet != null){
                return new MyResult(0,"该账号已被注册",null);
            }
            return new MyResult(1,"该账号可用",null);
        }
        return new MyResult(0,"账号不能为空",null);
    }

}
