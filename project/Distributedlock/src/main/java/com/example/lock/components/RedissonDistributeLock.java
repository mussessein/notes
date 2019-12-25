package com.example.lock.components;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redisson组件
 * 自定义Util方法
 */
@Component
@Slf4j
public class RedissonDistributeLock implements InitializingBean {

    @Autowired
    private RedissonClient redissonClient;
    // Redisson是唯一实现RedissonClient的类
    private static Redisson redisson;
    @Override
    public void afterPropertiesSet() throws Exception {
        redisson=(Redisson) redissonClient;
    }

    /**
     * 定义Redisson
     * 1. 获取锁的方法
     * 2. 释放锁的方法
     */
    public RLock acquireLock(String lockName){
        RLock rlock = redisson.getLock(lockName);
        rlock.lock(10L, TimeUnit.SECONDS);  // 10s自动释放锁，防止宕机无法释放锁
        return rlock;
    }
    public void realeaseLock(RLock rLock){
        rLock.unlock();
    }



    /**
     * 解决重复提交
     * 1. 为共享资源设置keyValue
     * 2. 查看共享资源key是否存在
     */
    public RSet setKeyValue(final String key,final String value){
        // guava的Strings工具类
        if (!Strings.isNullOrEmpty(key)){
            RSet<Object> rSet = redisson.getSet(key);
            rSet.add(value);
            return rSet;
        }
        return null;
    }
    public Boolean existKey(final String key){
        Boolean flag = false;
        RSet<Object> rSet = redisson.getSet(key);
        if (rSet!=null && rSet.size()>0){
            flag=true;
        }
        return flag;
    }


}
