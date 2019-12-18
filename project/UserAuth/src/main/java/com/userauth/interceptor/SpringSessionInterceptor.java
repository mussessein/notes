package com.userauth.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userauth.enums.Constant;
import com.userauth.enums.StatusCode;
import com.userauth.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * session认证模式的拦截器
 */
@Component  // 注入组件
@Slf4j
public class SpringSessionInterceptor implements HandlerInterceptor {
    /**
     * 拦截逻辑
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        String sessionId = session.getId();
        log.info(String.format("===========SpringSession当前登录用户:{}",sessionId));
        if (session.getAttribute(sessionId)!=null){
            return true;
        }
        // 没有session
        else {
            try {
                response.setStatus(HttpStatus.OK.value());
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.setHeader("Cache-Control", "no-cache, must-revalidate");
                PrintWriter writer = response.getWriter();
                writer.write(new ObjectMapper().writeValueAsString(new BaseResponse(StatusCode.AccessSessionNotExist)));
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        if (response.getStatus()== Constant.SERVER_ERROR){
            modelAndView.setViewName("/error/500");
        }else if (response.getStatus()==Constant.PAGE_NOT_FOUND){
            modelAndView.setViewName("/error/404");
        }
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
