package Redis.redisTemplate.impl;

import Redis.redisTemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Spring-Redis整合：
 * 1. 注入RedisTemplate对象
 * 2. 通过RedisTemplate进行业务逻辑处理
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate<String ,String> redisTemplate;

    /**
     * redis String 类型测试
     * 查询某key，存在返回，不存在，从数据库返回
     */
    @Override
    public String getString(String key) {

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)){
            // 从redis取出，返回
            System.out.println("redis查询结果：");
            return valueOperations.get(key);
        }else {
            // 数据库返回
            System.out.println("数据库查询结果：");
            String result = "数据库";
            // 存入redis
            valueOperations.set(key,result);
            return result;
        }
    }
}
