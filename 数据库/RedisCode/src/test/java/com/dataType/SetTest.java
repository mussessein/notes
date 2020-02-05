package com.dataType;

import com.ReidiscodeApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @author whr
 * @date 2020/1/15 14:36
 * @Description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReidiscodeApplication.class)
public class SetTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * Set：无序集和
     * 一个key，多个value
     * 全局去重，不存在重复
     */
    @Test
    public void setExample() {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        final String KEY = "Redis:set:test:key";
        String[] value1 = {"v1", "v2", "v3", "v4"};
        String[] value2 = {"v1", "v2"};
        /**
         * 创建了 2个set集和
         */
        setOperations.add(KEY + "-1", value1);
        setOperations.add(KEY + "-2", value2);
        // scan 扫描 key
        Cursor<String> scan = setOperations.scan(KEY, ScanOptions.NONE);
        while (scan.hasNext()) {
            String v = scan.next();
            log.info("scan:{}", v);
        }
        // 求差集
        Set<String> difference = setOperations.difference(KEY + "-1", KEY + "-2");
        log.info("set差集：{}", difference);
        //
        Set<String> union = setOperations.union(KEY + "-1", KEY + "-2");
        log.info("set并集：{}", union);
    }
}
