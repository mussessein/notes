package com.userauth.config;


import com.userauth.interceptor.SpringSessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * session认证模式，注册拦截器
 */
@Configuration
public class SessionConfig implements WebMvcConfigurer {

    @Bean
    public SpringSessionInterceptor springSessionInterceptor(){
        return new SpringSessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] inPatterns=new String[]{"/spring/session/auth","/spring/session/password/update","/spring/session/logout"};
        registry.addInterceptor(springSessionInterceptor())
                .addPathPatterns(inPatterns);
    }
}
