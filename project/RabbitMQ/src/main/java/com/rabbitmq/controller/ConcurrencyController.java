package com.rabbitmq.controller;

import com.rabbitmq.response.BaseResponse;
import com.rabbitmq.response.StatusCode;
import com.rabbitmq.service.concurrency.InitService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通过多线程来下单
 * 1. 使用CountDownLatch来为所有线程设置统一起跑线，每个线程启动一个下单任务
 * 2.
 */
@RestController
@RequestMapping("/concurrency")
@Slf4j
public class ConcurrencyController {

    @Autowired
    private InitService initService;

    @RequestMapping(value = "/robbing/thread",method = RequestMethod.GET)
    public BaseResponse robbingThread(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        initService.generateMultiThread();
        return response;
    }

}







































