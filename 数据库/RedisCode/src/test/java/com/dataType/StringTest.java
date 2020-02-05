package com.dataType;

import com.ReidiscodeApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author whr
 * @date 2020/1/15 14:27
 * @Description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReidiscodeApplication.class)
public class StringTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void stringExample() {

        final String key = "Redis:string:test:key";
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        /**
         * 添加一个key
         */
        valueOperations.set(key + "-1", "zhangsan");

        /**
         * 添加key，并设置过期时间
         */
        valueOperations.set(key + "-2", "duanyu", 30, TimeUnit.SECONDS);

        /**
         * 重新设置值
         */
        valueOperations.getAndSet("key" + "-1", "zhangsi");
        /**
         * setIfAbsent：用于做分布式锁
         * 如果存在，返回false；
         * 如果key不存在，创建key，并设置过期时间；
         */
        Boolean ifAbsent = valueOperations.setIfAbsent(key + "-3", "dddd", 30, TimeUnit.SECONDS);
    }
}
