package cn.lastlysly.service;

import cn.lastlysly.pojo.UserinfoSheet;

import java.util.List;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-26 19:49
 *
 * loginId 即为 username ，登陆账号
 **/
public interface UserinfoService {
    UserinfoSheet getUserinfoByLoginId(String loginId);

    List<String> listRolesByLoginId(String loginId);

    List<String> listPermissionsByLoginId(String loginId);

    /**
     * 用户注册
     * @param userinfoSheet
     * @return
     */
    boolean saveUserinfo(UserinfoSheet userinfoSheet);

    /**
     * 根据用户登录账号查询用户信息
     * @param loginId
     * @return
     */
    UserinfoSheet getUserinfo(String loginId);

}
