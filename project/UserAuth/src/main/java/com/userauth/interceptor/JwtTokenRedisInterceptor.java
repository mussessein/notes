package com.userauth.interceptor;

import com.userauth.enums.StatusCode;
import com.userauth.response.BaseResponse;
import com.userauth.service.CommonService;
import com.userauth.service.JwtTokenRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
public class JwtTokenRedisInterceptor implements HandlerInterceptor {
    @Autowired
    private CommonService commonService;
    @Autowired
    private JwtTokenRedisService jwtTokenRedisService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            String accessToken = request.getHeader("accessToken");
            if (StringUtils.isBlank(accessToken)) {
                log.error("=========jwtRedis用户验证失败=========");
                BaseResponse baseResponse = new BaseResponse(StatusCode.Fail.getCode(), "token不存在");
            } else {
                log.info("=========jwtRedis开始验证=========");
                BaseResponse baseResponse = jwtTokenRedisService.validateToken(accessToken);
                if (Objects.equals(baseResponse.getCode(), StatusCode.Success.getCode())) {
                    return true;
                } else {
                    commonService.print(response,baseResponse);
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
