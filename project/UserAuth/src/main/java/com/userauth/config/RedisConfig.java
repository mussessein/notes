package com.userauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redisc操作的自定义配置类：
 * 1. SpringRedisTemplate
 * 2. redisTemplate
 * 3. stringRedisTemplate
 */
@Configuration
public class RedisConfig {

    // 注入Redis的连接工厂
    @Autowired
    private RedisConnectionFactory connectionFactory;

    /**
     * 配置StringRedisTemplate（用来存String）
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate=new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(connectionFactory);
        return stringRedisTemplate;
    }

    /**
     * 配置redisTemplate(value用来存对象，序列化方式设置为json，)
     * 主要是配置序列化策略
     * 不用的序列化策略，存储的速度，空间，都是不同的；
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        // 获取实例
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        // 1. 配置连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        // 2. 设置key-value序列化策略——StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // 4. 设置hash存储的key-value的策略
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 返回redisTemplate实例
        return redisTemplate;
    }

}


