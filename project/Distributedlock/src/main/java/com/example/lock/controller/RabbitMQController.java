package com.example.lock.controller;

import com.example.lock.dto.RobbingDto;
import com.example.lock.entity.User;
import com.example.lock.rabbitmq.RabbitMQService;
import com.example.lock.response.BaseResponse;
import com.example.lock.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/rabbitmq")
public class RabbitMQController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PostMapping(value = "/sendBasicMsg")
    public BaseResponse sendMessage(@RequestParam String msg) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            rabbitMQService.sendMessage(msg);
            log.info("Send Message Success => {}",msg);
        } catch (Exception e) {
            e.printStackTrace();
            response =new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
    /**
     * crm系统抢单请求
     * @return
     */
    @PostMapping(value = "/sendCrmMsg")
    public BaseResponse crmOrderInfo(@RequestBody RobbingDto dto){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            rabbitMQService.sendCrmOrderInfo(dto);
        }catch (Exception e){
            e.printStackTrace();
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
}
