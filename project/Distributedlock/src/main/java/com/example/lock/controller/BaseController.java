package com.example.lock.controller;

import com.example.lock.entity.ProductLock;
import com.example.lock.mapper.ProductLockMapper;
import com.example.lock.response.BaseResponse;
import com.example.lock.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/base")
@Slf4j
public class BaseController {


    private static final String prefix="/hello";

    @Autowired
    private ProductLockMapper productLockMapper;

    /**
     * hello world测试
     * @return
     */
    @RequestMapping(value = prefix+"world",method = RequestMethod.GET)
    public BaseResponse helloWorld(){
        BaseResponse response=new BaseResponse(StatusCode.Ok);
        try {
            //TODO：我们真正的处理逻辑

            response.setData("hello-world");

        }catch (Exception e){
            log.error("发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }


    @RequestMapping(value = "/product/detail/{id}",method = RequestMethod.GET)
    public BaseResponse detail(@PathVariable Integer id){
        BaseResponse response=new BaseResponse(StatusCode.Ok);
        if (id==null || id<=0){
            return new BaseResponse(StatusCode.InvalidParam);
        }
        try {
            //TODO：我们真正的处理逻辑
            ProductLock lock=productLockMapper.selectByPrimaryKey(id);
            response.setData(lock);

        }catch (Exception e){
            log.error("发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

}
