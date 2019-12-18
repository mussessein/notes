package com.userauth.controller;

import com.google.common.collect.Maps;
import com.userauth.dto.UpdatePsdDto;
import com.userauth.enums.StatusCode;
import com.userauth.response.BaseResponse;
import com.userauth.service.ShiroRedisService;
import com.userauth.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * shiro
 * @author :whr
 */
@RequestMapping("shiro")
@RestController
@Slf4j
public class ShiroRedisController {

    @Autowired
    private ShiroRedisService shiroRedisService;
    /**
     * 登录逻辑
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseResponse login(@RequestParam String userName, @RequestParam String password){
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            return new BaseResponse(StatusCode.UserNamePasswordNotBlank);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            /**
             * 用户的身份验证，交由shiro处理
             * 1. 使用用户的登录信息创建令牌token
             * 2. subject.login的底层会调用，UserRealm的doGetAuthenticationInfo的自定义认证逻辑
             */
            Subject subject= SecurityUtils.getSubject();
            if (!subject.isAuthenticated()){
                UsernamePasswordToken token=new UsernamePasswordToken(userName,password);
                subject.login(token);
            }
            if (subject.isAuthenticated()){
                response.setData(SecurityUtils.getSubject().getPrincipal());
            }
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * （1）需要权限的资源
     * （2）不需要权限资源
     * 所有URL权限问题，都配置在ShiroRedisConfig下
     * shiroFilter配置
      * @return
     */
    @RequestMapping(value = "auth",method = RequestMethod.GET)
    public BaseResponse tokenAuth(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            String info="============shiro成功访问需要被拦截的资源============";
            resMap.put("info",info);
            resMap.put("currUser",SecurityUtils.getSubject().getPrincipal());

            response.setData(resMap);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
    @RequestMapping(value = "unauth",method = RequestMethod.GET)
    public BaseResponse tokenUnauth(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            String info="============shiro成功访问不需要被拦截的资源============";
            response.setData(info);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //退出登录注销session
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public BaseResponse logout(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            SecurityUtils.getSubject().logout();

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    //修改密码
    @RequestMapping(value = "password/update",method = RequestMethod.POST)
    public BaseResponse updatePassword(@RequestBody @Validated UpdatePsdDto dto, BindingResult bindingResult){
        log.info("============shiro修改密码============");

        String res= ValidatorUtil.checkResult(bindingResult);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            shiroRedisService.updatePassword(dto);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


}
