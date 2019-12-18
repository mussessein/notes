package com.userauth.service;

import com.userauth.entity.User;
import com.userauth.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户service层
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 用户认证service
     * 验证用户账号密码
     */
    public User authUser(String userName,String password){
        if (StringUtils.isBlank(userName)||StringUtils.isBlank(password)){
            throw new RuntimeException("用户名或密码不能为空！");
        }
        User user = userMapper.selectByUserName(userName);
        if (user==null){
            throw new RuntimeException("当前用户不存在");
        }
        if (!password.equals(user.getPassword())){
            throw new RuntimeException("用户密码错误！");
        }
        return user;
    }
}
