package com.dataType;

import com.ReidiscodeApplication;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author whr
 * @date 2020/1/15 13:52
 * @Description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReidiscodeApplication.class)
public class HashTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void hashExample() {
        log.info("=================hash测试=================");
        final String key = "reids:hash:test:key";
        // redisTemplate.delete(key);

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        /**
         * 直接添加值到 hash表
         */
        hashOperations.put(key, "10001", "zhangsan");
        hashOperations.put(key, "10002", "lilei");

        /**
         * 添加map到 hash表
         */
        HashMap<String, String> dataMap = Maps.newHashMap();
        dataMap.put("10003", "qiaofeng");
        dataMap.put("10004", "duanyu");
        hashOperations.putAll(key, dataMap);

        /**
         * 获取元素
         */
        Map<String, String> entries = hashOperations.entries(key);
        log.info("获取列表元素：{}", entries);

        String value = hashOperations.get(key, "10001");
        log.info("获取单个元素值：{}", value);

        Set<String> keys = hashOperations.keys(key);
        log.info("获取所有的key：{}", keys);

        Boolean hasKey = hashOperations.hasKey(key, "10004");
        log.info("判断key是否存在：{}", hasKey);

        Long res = hashOperations.delete(key, "10002");
        log.info("删除key：{}", res);

        Map<String, String> result = hashOperations.entries(key);
        log.info("获取列表元素：{}", result);
    }
}
