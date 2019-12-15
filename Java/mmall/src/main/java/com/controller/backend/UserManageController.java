package com.controller.backend;
import com.common.Constant;
import com.common.ServerResponse;
import com.pojo.User;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

/**
 * 后台管理员的接口
 */
@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    /**
     * 后台管理员登录
     * 与普通用户同样的登录逻辑,但是要判断是否为管理员用户
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {

        ServerResponse<User> response = iUserService.login(username, password);
        /**
         * 如果返回的response的状态码为0，登陆成功
         * 就设置session
         * getData拿到登录用户的信息
         */
        if (response.isSuccess()) {
            User user = response.getData();
            // 判断用户是否为管理员
            if (user.getRole()==(Constant.Role.ROLE_ADMIN)) {
                session.setAttribute(Constant.CURRENT_USER, user);
                return response;
            }
            else
                return ServerResponse.createByErrorMessage("非管理员无法登陆");
        }
        return response;
    }
}
