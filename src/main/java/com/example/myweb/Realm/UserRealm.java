package com.example.myweb.Realm;

import com.example.myweb.pojo.Permissions;
import com.example.myweb.pojo.Role;
import com.example.myweb.pojo.User;
import com.example.myweb.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Iterator;


public class UserRealm extends AuthorizingRealm {
    private static final Logger logger= LoggerFactory.getLogger(UserRealm.class);
    @Resource
    private LoginService loginService;


//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
//        logger.info("开始授权==============="+user.toString());
        Iterator<Role> iterator = user.getRoles().iterator();
        while (iterator.hasNext()){
            Role next = iterator.next();
            Iterator<Permissions> iterator1 = next.getPermissions().iterator();
            while (iterator1.hasNext()){
                simpleAuthorizationInfo.addStringPermission(iterator1.next().getPermissionsName());
            }
        }
        return simpleAuthorizationInfo;
    }
//  认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) authenticationToken;
        User login = loginService.login(usernamePasswordToken.getUsername());
        if (login==null){
            return null;
        }
        return new SimpleAuthenticationInfo(login,login.getPassword(),"");
    }
}
