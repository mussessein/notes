package com.userauth.controller;
import com.userauth.dto.UpdatePsdDto;
import com.userauth.enums.StatusCode;
import com.userauth.response.BaseResponse;
import com.userauth.service.JwtTokenRedisService;
import com.userauth.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@Slf4j
@RequestMapping("jwtRedis")
public class JwtTokenRedisController {

    @Autowired
    private JwtTokenRedisService jwtTokenRedisService;

    // 用户登录
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseResponse login(@RequestParam String userName, @RequestParam String password){
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            return new BaseResponse(StatusCode.UserNamePasswordNotBlank);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            response.setData(jwtTokenRedisService.authAndCreateToken(userName,password));
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //访问需要被授权的资源
    @RequestMapping(value = "token/auth",method = RequestMethod.GET)
    public BaseResponse tokenAuth(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            String info="jwt+redis~成功访问需要被拦截的链接/资源";
            response.setData(info);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //访问不需要被授权的资源
    @RequestMapping(value = "token/unauth",method = RequestMethod.GET)
    public BaseResponse tokenUnauth(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            String info="jwt+redis~成功访问不需要被拦截的链接/资源";
            response.setData(info);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
    //修改密码
    @RequestMapping(value = "token/password/update",method = RequestMethod.POST)
    public BaseResponse updatePassword(@RequestHeader String accessToken, @RequestBody @Validated UpdatePsdDto dto, BindingResult bindingResult){
        log.info("============jwtRedis修改密码============");

        if (StringUtils.isBlank(accessToken)){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        String res= ValidatorUtil.checkResult(bindingResult);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            jwtTokenRedisService.updatePassword(accessToken,dto);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
    //退出注销登录~前端需要清除token并重新进行登录
    @RequestMapping(value = "token/logout",method = RequestMethod.GET)
    public BaseResponse logout(@RequestHeader String accessToken){
        if (StringUtils.isBlank(accessToken)){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            jwtTokenRedisService.invalidateByAccessToken(accessToken);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


}
