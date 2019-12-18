package com.userauth.service;

import com.userauth.dto.UpdatePsdDto;
import com.userauth.entity.User;
import com.userauth.enums.Constant;
import com.userauth.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class SpringSessionService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    /**
     * session登录配置
     */
    public User authUser(String userName, String password, HttpSession session) {
        User user = userService.authUser(userName, password);
        if (user != null) {
            user.setPassword(null);
            // 配置跟踪会话的 session属性；key：sessionId，value：用户对象信息
            session.setAttribute(session.getId(),user);
            /**
             * 设置session超时时间，
             * session默认在用户有动作的情况下自动延长时间
             * 在用户没有动作的情况下，超时，自动清除
             */
            session.setMaxInactiveInterval(Constant.SESSION_EXPIRE);
            return user;
        }
        return null;
    }
    /**
     * 修改密码
     */
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(final UpdatePsdDto dto, final HttpSession session) {
        // sessioinId为key，user对象为value
        if (session != null && session.getAttribute(session.getId()) != null) {
            User user = (User) session.getAttribute(session.getId());
            if (user == null)
            {
                throw new RuntimeException("当前用户不存在");
            } else if (!user.getPassword().equals(dto.getOldPassword())) {
                throw new RuntimeException("用户密码不正确");
            }else {
                int result = userMapper.updatePassword(user.getUserName(), dto.getOldPassword(), dto.getNewPassword());
                if (result<=0){
                    throw new RuntimeException("修改失败");
                }
            }
        }
        // 失效当前sesion
        this.invalidateSession(session);
    }
    /**
     * 失效当前session
     * @param session
     */
    public void invalidateSession(HttpSession session) {
        String sessionId=session.getId();
        if (session.getAttribute(sessionId) != null) {
            // 清空session
            session.removeAttribute(sessionId);
        }
    }
}
