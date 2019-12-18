package com.userauth.controller;

import com.userauth.dto.UpdatePsdDto;
import com.userauth.entity.AuthTokenModel;
import com.userauth.enums.StatusCode;
import com.userauth.response.BaseResponse;
import com.userauth.service.JwtTokenService;
import com.userauth.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = {"jwt"})
public class JwtTokenController {

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping(value = {"login"})
    public BaseResponse login(@RequestParam("userName") String userName,
                              @RequestParam("password") String password) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return new BaseResponse(StatusCode.UserNamePasswordNotBlank);
        }
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        try{
            // jwt权限认证，创建token的service
            AuthTokenModel authTokenModel = jwtTokenService.authAndCreateToken(userName, password);
        }catch (Exception e){
            baseResponse=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return baseResponse;
    }

    /**
     * 访问授权资源
     */
    @RequestMapping(value = {"token/auth"})
    public BaseResponse tokenAuth(){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            log.info("================Jwt拦截了资源================");
            response.setData("没有访问权限");
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }
    /**
     * 访问非权限资源
     */
    @RequestMapping(value = {"token/unauth"})
    public BaseResponse tokenUnauth(){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            log.info("================非授权资源================");
            response.setData("没有访问权限");
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    //修改密码
    @RequestMapping(value = "token/password/update",method = RequestMethod.POST)
    public BaseResponse updatePassword(@RequestHeader String accessToken, @RequestBody @Validated UpdatePsdDto dto, BindingResult bindingResult){
        log.info("================Jwt修改密码================");
        if (StringUtils.isBlank(accessToken)){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        String res= ValidatorUtil.checkResult(bindingResult);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            jwtTokenService.updatePassword(accessToken,dto);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //退出注销登录，前端需要清除token并重新进行登录
    @RequestMapping(value = "token/logout",method = RequestMethod.GET)
    public BaseResponse logout(@RequestHeader String accessToken){
        if (StringUtils.isBlank(accessToken)){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        return new BaseResponse(StatusCode.Success);
    }



















}
