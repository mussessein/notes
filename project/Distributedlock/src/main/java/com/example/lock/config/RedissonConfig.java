<<<<<<< HEAD
package com.example.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.IOException;

@Configuration
@PropertySource("classpath:redisson.properties")
public class RedissonConfig {
    @Value("${redisson.host}")
    private String host;
    @Bean
    public RedissonClient redissonClient() throws IOException {
        Config config=new Config();
        config.useSingleServer().setAddress(host);
        RedissonClient client = Redisson.create(config);
        return client;
    }
}
=======
package com.example.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.IOException;

@Configuration
@PropertySource("classpath:redisson.properties")
public class RedissonConfig {
    @Value("${redisson.host}")
    private String host;
    @Bean
    public RedissonClient redissonClient() throws IOException {
        Config config=new Config();
        config.useSingleServer().setAddress(host);
        RedissonClient client = Redisson.create(config);
        return client;
    }
}
>>>>>>> project
