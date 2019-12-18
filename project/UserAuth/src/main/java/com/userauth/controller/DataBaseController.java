package com.userauth.controller;

import com.userauth.dto.UpdatePsdDto;
import com.userauth.entity.User;
import com.userauth.enums.StatusCode;
import com.userauth.response.BaseResponse;
import com.userauth.service.DataBaseService;
import com.userauth.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("database")
@Slf4j
public class DataBaseController {

    @Autowired
    private DataBaseService dataBaseService;

    // 用户登录controller
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseResponse login(@RequestParam(value = "userName") String userName,
                              @RequestParam(value = "password") String password){
        // 非空判断
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            return new BaseResponse(StatusCode.UserNamePasswordNotBlank);
        }else {
            BaseResponse response = new BaseResponse(StatusCode.Success);
            try {
                /**
                 * 生成token的service逻辑：
                 * 内部存储：
                 * 1. 用户信息
                 * 2. 生成的token信息
                 * 3. token失效时间
                 */
                response.setData(dataBaseService.authAndCreateToken(userName, password));
            } catch (Exception e) {
                response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
            }
            // 携带token信息，返回给前端
            return response;
        }
    }

    /**
     * 访问需要被授权的资源
     * 具体拦截的业务逻辑，在拦截器中实现
     */
    @RequestMapping(value = "token/auth",method = RequestMethod.GET)
    public BaseResponse tokenAuth(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            String info="数据库+token===成功访问需要被拦截的资源";
            response.setData(info);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //
    /**
     * 访问不需要被授权的资源
     * 拦截器中不做处理，可以直接访问
     */
    @RequestMapping(value = "token/unauth",method = RequestMethod.GET)
    public BaseResponse tokenUnauth(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            String info="数据库+token===成功访问不需要被拦截的资源";
            response.setData(info);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
    /**
     * 修改密码
     * 参数：
     * 1. Header中的Token
     * 2. 与前端约定封装的密码对象：UpdatePsdDto（字段使用@NotBlank约定，需要验证是否为空）
     * 3. @Validated 是密码对象的验证结果
     * 4. @RequestBody @Validated的参数后面，一定需要再传入BindingResult参数
     */
    @RequestMapping(value = "token/password/update",method = RequestMethod.POST)
    public BaseResponse updatePassword(@RequestHeader String accessToken, @RequestBody @Validated UpdatePsdDto dto, BindingResult bindingResult){
        log.info("======token+数据库===修改密码======");

        if (StringUtils.isBlank(accessToken)){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        // 校验bindingResult
        String res= ValidatorUtil.checkResult(bindingResult);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
        }
        // 校验通过，修改密码
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            dataBaseService.updatePassword(accessToken,dto);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
    //退出注销登录:前端需要清除token并重新进行登录
    @RequestMapping(value = "token/logout",method = RequestMethod.GET)
    public BaseResponse logout(@RequestHeader String accessToken){
        if (StringUtils.isBlank(accessToken)){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            dataBaseService.invalidateByAccessToken(accessToken);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //实际的业务模块操作-新增用户
    @RequestMapping(value = "token/user/save",method = RequestMethod.POST)
    public BaseResponse saveUser(@RequestHeader String accessToken, @RequestBody @Validated User user, BindingResult bindingResult){
        if (StringUtils.isBlank(accessToken)){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        String res=ValidatorUtil.checkResult(bindingResult);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            dataBaseService.saveUser(accessToken,user);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }



}
