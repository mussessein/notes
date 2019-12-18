package com.userauth.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 统一的验参工具
 **/
public class ValidatorUtil {

    //统一处理加注解后校验的结果
    public static String checkResult(BindingResult result){
        StringBuilder sb=new StringBuilder("");

        if (result!=null && result.hasErrors()){
            List<ObjectError> errors=result.getAllErrors();
            //java8 stream的写法
            errors.forEach(error -> sb.append(error.getDefaultMessage()).append("\n"));
        }
        return sb.toString();
    }
}