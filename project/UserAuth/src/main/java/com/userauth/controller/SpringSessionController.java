package com.userauth.controller;

import com.google.common.collect.Maps;
import com.userauth.dto.UpdatePsdDto;
import com.userauth.entity.User;
import com.userauth.enums.StatusCode;
import com.userauth.response.BaseResponse;
import com.userauth.service.SpringSessionService;
import com.userauth.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = {"spring/session"})
public class SpringSessionController {

    @Autowired
    private SpringSessionService springSessionService;
    @PostMapping(value = {"login"})
    public BaseResponse login(@RequestParam String userName,
                              @RequestParam String password,
                              HttpSession session) {
        if (StringUtils.isBlank(userName) || StringUtils.isNotBlank(password)) {
            return new BaseResponse(StatusCode.UserNamePasswordNotBlank);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            User user = springSessionService.authUser(userName, password, session);
            response.setData(user);
        } catch (Exception e) {
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //访问需要被授权的资源
    @RequestMapping(value = "auth",method = RequestMethod.GET)
    public BaseResponse tokenAuth(HttpSession session){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            String info="spring session~成功访问需要被拦截的链接/资源";
            resMap.put("info",info);
            resMap.put("currUser",session.getAttribute(session.getId()));

            response.setData(resMap);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
    //访问不需要被授权的资源
    @RequestMapping(value = "unauth",method = RequestMethod.GET)
    public BaseResponse tokenUnauth(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            String info="spring session~成功访问不需要被拦截的链接/资源";
            response.setData(info);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    @PostMapping(value = {"/password/update"})
    public BaseResponse updatePassword(HttpSession session,
                                       @RequestBody @Validated UpdatePsdDto dto,
                                       BindingResult bindingResult){
        log.info("==============SpringSession==Update Password================");
        String result = ValidatorUtil.checkResult(bindingResult);
        if (StringUtils.isNotBlank(result)) {
            return new BaseResponse(StatusCode.InvalidParams.getCode(),result);
        }else
        {
            BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
            try {
                springSessionService.updatePassword(dto, session);
            } catch (Exception e) {
                baseResponse = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
            }
            return baseResponse;
        }
    }

    /**
     * 退出登录逻辑
     */
    @GetMapping(value = {"logout"})
    public BaseResponse logout(HttpSession session) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            springSessionService.invalidateSession(session);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }









































}
