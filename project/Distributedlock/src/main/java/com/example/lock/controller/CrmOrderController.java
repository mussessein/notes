package com.example.lock.controller;

import com.example.lock.dto.RobbingDto;
import com.example.lock.entity.User;
import com.example.lock.mapper.UserMapper;
import com.example.lock.rabbitmq.RabbitMQService;
import com.example.lock.response.BaseResponse;
import com.example.lock.response.StatusCode;
import com.example.lock.service.CrmOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.representer.BaseRepresenter;

/**
 * 外卖抢单Controller
 * 外卖抢单，业务逻辑步骤：
 * 1. 确认外卖小哥信息（用户验证）
 * 2.
 */
@RestController
@Slf4j
@RequestMapping("/crm")
public class CrmOrderController {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CrmOrderService crmOrderService;
    @Autowired
    private RabbitMQService rabbitMQService;
    @PostMapping("/robbing")
    public BaseResponse robbing(@RequestBody @Validated RobbingDto dto,BindingResult bindingResult) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try{
            log.info("user info:{}",dto);
            // 抢单逻辑
            User user = userMapper.selectByPrimaryKey(dto.getUserId());
            if (user==null){
                // 用户不存在
                return new BaseResponse(StatusCode.UserNameExist);
            }else {
                // 用户存在，可以抢单
                // int res =crmOrderService.robbingV3(dto);

                // 消息发送至MQ，达到限流的效果
                rabbitMQService.sendCrmOrderInfo(dto);
            }
        }catch (Exception e){
            log.error("Robbing Failed => {}",e.getMessage());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
}

