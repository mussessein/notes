package com.userauth.config;


import com.userauth.interceptor.DataBaseInterceptor;
import com.userauth.interceptor.JwtTokenInterceptor;
import com.userauth.interceptor.JwtTokenRedisInterceptor;
import com.userauth.interceptor.RedisInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 1. 向SpringBoot注册所有的Interceptor
 * 2. 实现跨域
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 手动注入各个拦截器的bean
     */
    @Bean
    public DataBaseInterceptor dataBaseInterceptor(){
        return new DataBaseInterceptor();
    }
    @Bean
    public RedisInterceptor redisInterceptor(){return new RedisInterceptor();}
    @Bean
    public JwtTokenInterceptor jwtTokenInterceptor(){return new JwtTokenInterceptor();}
    @Bean
    public JwtTokenRedisInterceptor jwtTokenRedisInterceptor(){ return new JwtTokenRedisInterceptor();}

    /*
    添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        final String[] inPatterns=new String[]{"/jwtRedis/token/auth"};
        registry.addInterceptor(jwtTokenRedisInterceptor())
                .addPathPatterns(inPatterns);
    }
    /**
     * 访问静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**")
                .addResourceLocations("classpath:/statics/");
    }
    /**
     * 跨域的设置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**"). /*可以跨域的路径*/
                 allowedMethods("*").   /*允许所有方法跨域：POST，GET等等*/
                allowedOrigins("*").     /*允许所有的请求访问跨域资源*/
                allowedMethods("*");     /*允许所有的请求头（header）访问*/
    }
}
