package com.example.lock.controller;

import com.example.lock.dto.UserDto;
import com.example.lock.entity.User;
import com.example.lock.mapper.UserMapper;
import com.example.lock.response.BaseResponse;
import com.example.lock.response.StatusCode;
import com.example.lock.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    /**
     * 通过注册接口演示：重复提交场景（用户不停点击同一请求）
     */
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    /**
     * 普通不加锁
     * 为userName添加唯一索引
     */
    @PostMapping("/register")
    public BaseResponse register(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try{
            log.info("user info:{}",userDto);
            // 业务逻辑
            User user = userMapper.selectByUserName(userDto.getUserName());
            if (user!=null){
                // 用户已存在
                return new BaseResponse(StatusCode.UserNameExist);
            }else {
                // 用户不存在，新增用户
                int res = userService.registerV1(userDto);
            }
        }catch (Exception e){
            log.error("register failed => {}",e.getMessage());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }













}

