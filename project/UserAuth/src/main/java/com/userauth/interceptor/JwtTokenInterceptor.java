package com.userauth.interceptor;

import com.userauth.enums.Constant;
import com.userauth.enums.StatusCode;
import com.userauth.response.BaseResponse;
import com.userauth.service.CommonService;
import com.userauth.service.JwtTokenService;
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
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private CommonService commonService;
    @Autowired
    private JwtTokenService jwtTokenService;
    //Controller方法处理之前
    //获取前端塞在请求头header里面的accessToken字段的值，然后进行验证与解析
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            String accessToken=request.getHeader("accessToken");
            if (StringUtils.isBlank(accessToken)){
                log.error("===============jwt用户验证失败===============");
                BaseResponse baseResponse=new BaseResponse(StatusCode.Fail.getCode(),"accessToken必填请先进行登录,并将生成的accessToken塞入http请求头的accessToken字段中");
                commonService.print(response,baseResponse);
            }else{
                log.info("===============jwt用户验证开始进行===============");
                BaseResponse result=jwtTokenService.validateToken(accessToken);
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
    // preHandle返回true，才会执行此方法，也就是请求通过了拦截器，才会调用postHandle
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (response.getStatus()== Constant.SERVER_ERROR){
            modelAndView.setViewName("/error/500");
        }else if (response.getStatus()==Constant.PAGE_NOT_FOUND){
            modelAndView.setViewName("/error/404");
        }
    }

    /**
     * preHandle返回true ，
     * 视图渲染完成之后，会调用次方法，一般是用于清理资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
