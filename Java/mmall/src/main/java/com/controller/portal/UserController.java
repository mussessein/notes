package com.controller.portal;

import com.common.Constant;
import com.common.ResponseCode;
import com.common.ServerResponse;
import com.pojo.User;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 用户登录
     * 指定method只能通过POST请求
     * ResponseBody:返回的时候，自动通过jackson插件序列化为json格式
     * 插件位置：dispatcher-servlet.xml
     *
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
            session.setAttribute(Constant.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 登出功能：
     * 只需要传入当前用户Session，
     * 删除Constant.CURRENT_USER这个key，就可以了
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Constant.CURRENT_USER);
        return ServerResponse.createBySuccess("退出成功");
    }

    /**
     * 注册功能(复用校验功能)：
     * 具体查看IUserServiceImpl
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return iUserService.register(user);
    }

    /**
     * 校验功能
     *
     * @param str
     * @param type
     * @return
     */
    @RequestMapping(value = "check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type) {
        return iUserService.checkValid(str, type);
    }

    /**
     * 通过session获取用户信息
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        //
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录,无法获取信息");
    }

    /**
     * 拿到密码提示问题,
     * 也就是点击找回密码,然后进入输入密码提示问题的答案
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return iUserService.selectQuesion(username);
    }

    /**
     * 拿到密码提示问题之后
     * 输入问题答案
     * 这个方法就是对username,question,answer三个字段进行匹配,匹配通过,才能修改密码
     *
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return iUserService.checkAnswer(username, question, answer);
    }

    /**
     * 匹配了问题之后
     * 可以开始重置密码,这里需要验证在上一步中生成的Token是否一致
     * 防止横向越权(同级用户修改别的用户的密码)
     *
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {

        return iUserService.forgetResetPassword(username, passwordNew, forgetToken);
    }

    /**
     * 登录状态 修改密码
     * 1.先判断用户是否在登录状态(从session拿到user)
     * 2.将passwordOld,passwordNew,user传入service层的方法进行更新
     *
     * @param session
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.resetPassword(passwordOld, passwordNew, user);
    }

    /**
     * 登录状态更新个人信息
     * 这里有两个user对象
     * currentuser:一个是当前登录的用户
     * user:一个是更新后的信息用户对象
     * 更新成功之后:更新session
     * @param session
     * @return
     */
    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> update_infomation(HttpSession session, User user) {
        User currentuser = (User) session.getAttribute(Constant.CURRENT_USER);
        if (currentuser == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        /**
         * 不能更新用户id,username,直接将当前用户的id,username传给新信息用户
         */
        user.setId(currentuser.getId());
        user.setUsername(currentuser.getUsername());
        ServerResponse<User> response = iUserService.updateInformation(user);
        // 更新成功,设置session
        if (response.isSuccess()){
            // Data中存的就是user对象
            response.getData().setUsername(currentuser.getUsername());
            // 将session当前用户,设置为新的user
            session.setAttribute(Constant.CURRENT_USER,response.getData());
        }
        // 更新失败,直接返回
        return response;
    }

    /**
     * 显示个人信息
     * 1.从session中拿到用户对象
     * 2.如果对象为null,则返回未登录信息
     * 3.存在用户,则调用Service方法,返回对象信息
     * @param session
     * @return
     */
    @RequestMapping(value = "get_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> get_information(HttpSession session){
        User currentUser = (User) session.getAttribute(Constant.CURRENT_USER);
        if (currentUser==null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录");
        }
        return iUserService.get_information(currentUser.getId());
    }
}
