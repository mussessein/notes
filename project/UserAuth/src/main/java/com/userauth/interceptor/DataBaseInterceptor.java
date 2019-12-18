package com.userauth.interceptor;

import com.userauth.enums.Constant;
import com.userauth.enums.StatusCode;
import com.userauth.response.BaseResponse;
import com.userauth.service.CommonService;
import com.userauth.service.DataBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * token+database下的拦截器
 * 用户访问需要权限的url资源时：此拦截器负责拿到前端返回的Token进行解析验证
 */
@Component  // 注入组件
@Slf4j
public class DataBaseInterceptor implements HandlerInterceptor {
    /**
     * 重写三个方法
     * 1. preHandle：对于注册拦截的url，在进入url对应的方法之前，必须进入preHandle中，这样就对request进行了拦截
     * 在preHandle中，处理token的校验，校验通过，才能正常访问，校验不通过，处理；
     */

    @Autowired
    private CommonService commonService;
    @Autowired
    private DataBaseService dataBaseService;

    /**
     * 拿到requst，取出header中的token
     * token存在，开始验证
     * token不存在，返回失败
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            String accessToken = request.getHeader("accessToken");
            if (StringUtils.isBlank(accessToken)){
                log.error("=========database+token用户验证失败=========");
                // tokne为空的逻辑
                BaseResponse baseResponse = new BaseResponse(StatusCode.Fail.getCode(), "无效Token，请先登录");
                commonService.print(response,baseResponse);
            }else {
                log.info("==========database+token开始用户验证==========");
                // token存在，开始验证token
                BaseResponse result=dataBaseService.validateToken(accessToken);
                // 验证返回的结果为success，则返回true
                if (Objects.equals(result.getCode(),StatusCode.Success.getCode())){
                    return true;
                }else{
                    commonService.print(response,result);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 根据response返回对应的视图
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,@Nullable ModelAndView modelAndView) throws Exception {
        if (response.getStatus() == Constant.SERVER_ERROR) {
            modelAndView.setViewName("/error/500");
        }else if (response.getStatus()==Constant.PAGE_NOT_FOUND)
        {
            modelAndView.setViewName("/error/404");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
