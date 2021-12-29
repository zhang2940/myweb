package com.example.myweb.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        设置用户没有登录时跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/log/login");
//        设置用户没有权限时跳转的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/log/login");
//      所有页面都必须通过authc认证才可以访问，anon:所有页面都可以访问
        Map<String,String> filterDefineMap=new HashMap<>();
        filterDefineMap.put("/log/login","anon");
        filterDefineMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterDefineMap);
        return shiroFilterFactoryBean;

    }
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm());
        return defaultWebSecurityManager;
    }
    @Bean
    public SimpleAccountRealm realm(){
        SimpleAccountRealm realm=new SimpleAccountRealm();
        //使用hash密码匹配
        realm.setCredentialsMatcher(realm.getCredentialsMatcher());
        return realm;
    }




}
