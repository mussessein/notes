package com.core;

import com.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFirstInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从session中拿到用户信息
        User user = (User)request.getSession().getAttribute("session_user");
        if(user==null) {
            System.out.println("第一个preHandle执行，返回false");
            //判断session中没有用户信息，重定向到login页面
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        return true;//会终止所有的请求
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("第一个postHandle");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("第一个afterCompletion执行，进行清理工作");
    }
}
