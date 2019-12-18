package com.userauth.controller;

import com.userauth.enums.StatusCode;
import com.userauth.response.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一错误页面
 */
@Controller
@RequestMapping("error")
public class ErrorController {

    @RequestMapping(value = "500",method = RequestMethod.GET)
    public String error500(){
        return "500";
    }

    @RequestMapping(value = "404",method = RequestMethod.GET)
    public String error404(){
        return "404";
    }

    @RequestMapping("unauth")
    @ResponseBody
    public BaseResponse unauth(){
        return new BaseResponse(StatusCode.AccessSessionNotExist);
    }
}