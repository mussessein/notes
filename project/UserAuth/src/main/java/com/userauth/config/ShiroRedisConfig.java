package com.userauth.config;

import com.userauth.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author whr
 */
@Configuration
public class ShiroRedisConfig implements EnvironmentAware {

    private Environment env;
    @Override
    public void setEnvironment(Environment environment) {
        this.env=environment;
    }

    //securityManager-管理subject
    @Bean
    public SecurityManager securityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm( userRealm);
        securityManager.setRememberMeManager(null);
       //自定义缓存管理器-redis
        securityManager.setCacheManager(cacheManager());
        //自定义一个存储session的管理器
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
    /**
     * shiro的过滤连（拦截器）
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter=new ShiroFilterFactoryBean();
        // 1. 必须先设置securityManager
        shiroFilter.setSecurityManager(securityManager);
        // 2. 设定没有登陆的用户，自动跳转到登录界面
        shiroFilter.setLoginUrl("/error/unauth");
        // 登录成功后要跳转的链接
        //shiroFilter.setSuccessUrl("/auth/index");
        /**
         * anon：不进行拦截的路径
         * authc：需要授权的路径
         */
        Map<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/shiro/login","anon");
        filterChainDefinitionMap.put("/shiro/unauth","anon");
        filterChainDefinitionMap.put("/shiro/logout","anon");
        filterChainDefinitionMap.put("/**","authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }
    //shiro bean生命周期的管理
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
    //自定义session缓存管理器
    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager cacheManager=new RedisCacheManager();
        cacheManager.setRedisManager(redisManager());
        return cacheManager;
    }
    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager=new RedisManager();
        //缓存key的超时
        //redisManager.setExpire(600000);
        return redisManager;
    }
    //自定义session管理器
    private DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
    //shiro sessionDao层的实现，使用redission作为sessionManager的Dao
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        //设置存储在缓存中session的Key的前缀
        redisSessionDAO.setKeyPrefix("spring_shiro_redis_session:");
        return redisSessionDAO;
    }
}



