package com.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 本地缓存类,
 * 将UUID作为value加入缓存中,即可开始计时.
 */
public class TokenCache {

    public static final String TOKEN_PREFIX = "token_";
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);
    /**
     * 本地缓存的初始化
     * 缓存初始大小1000
     * 缓存数量最大值10000,超过使用LRU算法,淘汰最少使用项
     * 有效期:12 HOURS
     */
    private static LoadingCache<String, String> localCahe = CacheBuilder.newBuilder()
            .initialCapacity(1000).maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                @Override
                // 默认的数据加载实现,没有找到key的时候,会调用此方法
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key, String value) {
        localCahe.put(key, value);
    }

    public static String getKey(String key) {
        String value = null;
        try {
            value = localCahe.get(key);
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (Exception e) {
            logger.error("localCache get error");
        }
        return null;
    }
}
