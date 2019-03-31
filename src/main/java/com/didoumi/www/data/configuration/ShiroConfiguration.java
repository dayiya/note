package com.didoumi.www.data.configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfiguration {

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("index");
        //没有权限访问的界面
        bean.setUnauthorizedUrl("/unanthorized");
        // 定制相关表单是否需要相关权限的设定，具体配置信息请看：Shiro-内置的FilterChain
        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        //index界面需要鉴权
        filterChainDefinitionMap.put("/index", "authc");
        // login、loginUser表单不需要验证
        filterChainDefinitionMap.put("/layui/**","anon");
        filterChainDefinitionMap.put("/login/**","anon");
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/loginUser","anon");
        // admin表单需要角色 admin 才能访问
        filterChainDefinitionMap.put("/admin", "roles[admin]");
        // edit表单需要权限 edit 才能访问
        filterChainDefinitionMap.put("/edit", "perms[edit]");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**", "user");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm)
    {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    @Bean("authRealm")
    public AuthRealm authRealm()
    {
        AuthRealm authRealm = new AuthRealm();

        /**
         * shiro自带的MemoryConstrainedCacheManager作缓存只能用于本机，那么在集群时就无法使用，
         * 如果使用ehcache、redis等其他缓存，可以参考https://www.cnblogs.com/lic309/p/4072848.html
         */
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());

        // 用com.mmall.demo2.CredentialMatcher中自定义的密码比较器对密码进行比较
        authRealm.setCredentialsMatcher(new CredentialMatcher());
        return authRealm;
    }


    // 把shiro和spring进行绑定
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    // 开启自动代码，设置为true即可
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator()
    {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
