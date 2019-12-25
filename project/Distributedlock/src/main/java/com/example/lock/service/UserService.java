package com.example.lock.service;

import com.example.lock.components.RedissonDistributeLock;
import com.example.lock.dto.UserDto;
import com.example.lock.entity.User;
import com.example.lock.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 普通注册，直接操作数据库
     * @param userDto
     * @return
     */
    public int registerV1(UserDto userDto){
        int res=0;
        User user = new User();
        user.setCreateTime(new Date());
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        res = userMapper.insertSelective(user);
        return res;
    }

    /**
     * 分布式锁：解决重复提交
     * 为共享资源加锁
     */

    @Autowired
    private RedissonDistributeLock redissonLock;

    private static final String lockKeyPrefix="redisson:userName:";
    public int registerV2(UserDto userDto){
        int result=0;
        /**
         * 为共享资源加锁——setKey
         */
        RLock rLock=redissonLock.acquireLock(userDto.getUserName());
        try {
            if (rLock!=null) {
                String key = lockKeyPrefix + userDto.getUserName();
                if (!redissonLock.existKey(key)) {
                    redissonLock.setKeyValue(key, userDto.getUserName());
                    User user = new User();
                    BeanUtils.copyProperties(userDto, user);
                    user.setCreateTime(new Date());
                    userMapper.insertSelective(user);
                } else {
                    log.info("已存在于redisson的set存储中:{}", key);
                }
            }else {

            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            redissonLock.realeaseLock(rLock);
        }
        return result;
    }

}
