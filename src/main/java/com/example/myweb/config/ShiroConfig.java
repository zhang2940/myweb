package com.example.myweb.config;

import com.example.myweb.Realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;


@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
      ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
//      设置安全管理器
      shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /**
         * 添加shiro的内置过滤器
         * anon:无需认证就可以访问
         * authc:必须拥有了认证才可以访问
         * user:只要rememberme就可以访问
         * perms:拥有某个资源的权限才可以访问
         * role:拥有某个角色权限才可以访问
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/log/region","perms[users:add]");
//        filterMap.put("/log/region","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("realm") UserRealm userRealm){
//        关联UserRealm
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }
    @Bean
    public UserRealm realm(){
        return new UserRealm();
    }


}
