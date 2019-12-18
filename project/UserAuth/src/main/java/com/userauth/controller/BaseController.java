package com.userauth.controller;


import com.google.common.collect.Lists;
import com.userauth.enums.StatusCode;
import com.userauth.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

/**
 * 状态响应码接口测试
 * @Slf4j:免实例化logger对象，直接使用log
 * @author :whr
 */
@RestController
@RequestMapping("base")
@Slf4j
public class BaseController {

    //private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping("info")
    public BaseResponse info()
    {
        log.info("==================请求响应数据测试==================");
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try{

            String str1 = "test pass";
            String str2 = "success";
            String str3 = "automake";
            LinkedList<String> list = Lists.newLinkedList();
            list.add(str1);
            list.add(str2);
            list.add(str3);
            response.setData(list);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
    /**
     * 测试Redis
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("redisInfo")
    public BaseResponse redisInfo()
    {
        log.info("==================Redis数据测试==================");
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try{
            String key = "RedisKey";
            String value = "RedisValue";
            stringRedisTemplate.opsForValue().set(key,value);
            response.setData(stringRedisTemplate.opsForValue().get(key));
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}
