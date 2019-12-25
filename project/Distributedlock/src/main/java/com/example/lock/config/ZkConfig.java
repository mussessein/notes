package com.example.lock.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.Closeable;
import java.util.Objects;

/**
 * zookeeper配置注入bean
 */
//@Configuration
//public class ZkConfig {
//    @Autowired
//    private Environment env;
//    @Bean
//    public CuratorFramework curatorFramework(){
//        /**
//         * 1. 设置host
//         * 2. 设置namespace
//         * 3. retryPolicy重试策略：重试5词，间隔1000ms
//         */
//        CuratorFramework client=
//                CuratorFrameworkFactory
//                        .builder()
//                        .connectString(env.getProperty("zk.host"))
//                        .namespace(env.getProperty("zk.namespace"))
//                        .retryPolicy(new RetryNTimes(5,1000))
//                        .build();
//        client.start();
//        return client;
//    }
//}
