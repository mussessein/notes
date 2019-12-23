package com.example.lock.controller;

import com.example.lock.dto.ProductLockDto;
import com.example.lock.response.BaseResponse;
import com.example.lock.response.StatusCode;
import com.example.lock.service.DataLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通过此controller测试数据库锁
 * 1. 不加锁
 * 2. 悲观锁
 * 3. 乐观锁
 *
 */
@RestController
@Slf4j
@RequestMapping(value = "/datalock")
public class DataLockController {

    @Autowired
    private DataLockService dataLockService;

    /**
     * 普通不加锁数据库操作
     * 出现：
     * 1. 库存减为负数的情况
     * 2. 库存数量大于 实际数量
     */
    @PostMapping(value = "/update_1",consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updateStockV1(@RequestBody @Validated ProductLockDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new BaseResponse(StatusCode.InvalidParam);
        }
        BaseResponse response=new BaseResponse(StatusCode.Ok);
        try {
            log.debug("不加锁数据：{} ",dto);
            int res = dataLockService.updateStock_1(dto);
            if (res <= 0) {
                return new BaseResponse(StatusCode.Fail);
            }
        }catch (Exception e){
            log.error("发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
    /**
     * 乐观锁实现：版本号机制
     * 1. 添加版本号字段
     * 2. 每次更新，判断版本号，
     * （1）一致则更新库存，并且版本号version+1
     * （2）不一致，则不更新
     */
    @PostMapping(value = "/update_2",consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updateStockV2(@RequestBody @Validated ProductLockDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new BaseResponse(StatusCode.InvalidParam);
        }
        BaseResponse response=new BaseResponse(StatusCode.Ok);
        try {
            log.debug("乐观锁数据：{} ",dto);
            int res = dataLockService.updateStock_2(dto);
            if (res <= 0) {
                return new BaseResponse(StatusCode.Fail);
            }
        }catch (Exception e){
            log.error("发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    /**
     * 悲观锁实现——for update
     *
     */
    @PostMapping(value = "/update_3",consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updateStockV3(@RequestBody @Validated ProductLockDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new BaseResponse(StatusCode.InvalidParam);
        }
        BaseResponse response=new BaseResponse(StatusCode.Ok);
        try {
            log.debug("悲观锁数据：{} ",dto);
            int res = dataLockService.updateStock_3(dto);
            if (res <= 0) {
                return new BaseResponse(StatusCode.Fail);
            }
        }catch (Exception e){
            log.error("发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
    /**
     * 分布式锁——redis
     */
    @PostMapping(value = "/update_4",consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updateStockV4(@RequestBody @Validated ProductLockDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new BaseResponse(StatusCode.InvalidParam);
        }
        BaseResponse response=new BaseResponse(StatusCode.Ok);
        try {
            log.debug("分布式锁Redis数据：{} ",dto);
            int res = dataLockService.updateStock_4(dto);
            if (res <= 0) {
                return new BaseResponse(StatusCode.Fail);
            }
        }catch (Exception e){
            log.error("发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
    /**
     * 分布式锁——zookeeper
     */
//    @PostMapping(value = "/update_5",consumes = MediaType.APPLICATION_JSON_VALUE)
//    public BaseResponse updateStockV5(@RequestBody @Validated ProductLockDto dto, BindingResult bindingResult){
//        if (bindingResult.hasErrors()){
//            return new BaseResponse(StatusCode.InvalidParam);
//        }
//        BaseResponse response=new BaseResponse(StatusCode.Ok);
//        try {
//            log.debug("分布式锁Zookeeper数据：{} ",dto);
//            int res = dataLockService.updateStock_5(dto);
//            if (res <= 0) {
//                return new BaseResponse(StatusCode.Fail);
//            }
//        }catch (Exception e){
//            log.error("发生异常：",e.fillInStackTrace());
//            response=new BaseResponse(StatusCode.Fail);
//        }
//        return response;
//    }
    /**
     * 分布式锁——Redisson
     */
    @PostMapping(value = "/update_6",consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updateStockV6(@RequestBody @Validated ProductLockDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new BaseResponse(StatusCode.InvalidParam);
        }
        BaseResponse response=new BaseResponse(StatusCode.Ok);
        try {
            log.debug("分布式锁Redisson数据：{} ",dto);
            int res = dataLockService.updateStock_6(dto);
            if (res <= 0) {
                return new BaseResponse(StatusCode.Fail);
            }
        }catch (Exception e){
            log.error("发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
}
