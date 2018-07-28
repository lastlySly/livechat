package cn.lastlysly.myutils.shiro;

import cn.lastlysly.pojo.MessagesSheet;
import cn.lastlysly.pojo.UserinfoSheet;
import cn.lastlysly.service.CustomWebSocketService;
import cn.lastlysly.service.UserinfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-26 21:51
 **/
public class CustomRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private CustomWebSocketService customWebSocketService;

    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        /*从数据库或者缓存中获取角色数据*/
        Set<String> rolesSet = listRolesByUserName(userName);
        /*从数据库或者缓存中获取权限数据*/
        Set<String> permissionsSet = listPermissionsByUserName(userName);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissionsSet);
        simpleAuthorizationInfo.setRoles(rolesSet);
        return simpleAuthorizationInfo;
    }


    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1,从主体传过来的认证信息中获取用户名
        String userName = (String) authenticationToken.getPrincipal();
        //2,通过用户名到数据库中获取凭证
        UserinfoSheet userinfoSheet = getPasswordByUsername(userName);
        if (userinfoSheet == null){
            return null;
        }


        //apache shiro获取所有在线用户
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for(Session session:sessions){
            String loginUsername = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));//获得session中已经登录用户的名字

            if(userinfoSheet.getUserLoginId().equals(loginUsername)){ //这里的username也就是当前登录的username

                MessagesSheet messagesSheet = new MessagesSheet();
                messagesSheet.setMessagesToLoginid(userinfoSheet.getUserLoginId());
                messagesSheet.setMessagesTypeid(7);
                messagesSheet.setMessagesPostmessages("该账户在其他机器被登陆");
                customWebSocketService.adminPushTo(messagesSheet);
//                session.setTimeout(0); //这里就把session清除，
            }
        }


        /*获取盐值*/
        ByteSource credentialsSalt = ByteSource.Util.bytes(userinfoSheet.getUserPasswordSalt());

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userinfoSheet.getUserLoginId(),
                userinfoSheet.getUserPassword(),credentialsSalt,this.getName());


        return simpleAuthenticationInfo;
    }

    /**
     * 通过用户名从数据库中获取凭证
     * @param userName
     * @return
     */
    private UserinfoSheet getPasswordByUsername(String userName) {
        UserinfoSheet userinfoSheet = userinfoService.getUserinfoByLoginId(userName);

        if (userinfoSheet != null){
            return userinfoSheet;
        }

        return null;
    }

    /**
     * 从数据库中获取角色数据
     * @param userName
     * @return
     */
    private Set<String> listRolesByUserName(String userName) {
        List<String> rolesList = userinfoService.listRolesByLoginId(userName);
        Set<String> rolesSet = new HashSet<String>(rolesList);

        return rolesSet;
    }


    /**
     * 从数据库中获取权限数据
     * @param userName
     * @return
     */
    private Set<String> listPermissionsByUserName(String userName) {
        List<String> permissionsList = userinfoService.listPermissionsByLoginId(userName);
        Set<String> permissionsSet = new HashSet<String>(permissionsList);
        return permissionsSet;
    }

}
