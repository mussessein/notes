package com.web;

import com.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){

        return "login";
    }


    @RequestMapping("/logined")
    public String logined(@RequestParam("account")String account,
                          @RequestParam("password")String password,
                          HttpSession session){
        if(account.equals("whr") && password.equals("123456")) {
            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            //将用户名密码装载到session中，以供其他方法进行验证
            session.setAttribute("session_user",user);
            //重定向到user/search
            return "redirect:user/search";
        }else{
            return "redirect:login";
        }
    }
}
