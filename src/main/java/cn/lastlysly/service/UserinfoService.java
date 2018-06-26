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
}
