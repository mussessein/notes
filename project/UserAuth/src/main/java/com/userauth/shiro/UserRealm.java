package com.userauth.shiro;

import com.userauth.entity.User;
import com.userauth.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义用户的认证、授权realm
 * @author :whr
 */
@Component
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用于授权资源
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 用户身份验证的逻辑，在这里统一处理
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        final String userName = userToken.getUsername();
        final String password = String.valueOf(userToken.getPassword());
        log.info("===================shiro用户认证{}===================",userName);
        User user = userMapper.selectByUserName(userName);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        } else if (!password.equals(user.getPassword())) {
            throw new RuntimeException("密码不正确");
        }else {
            // 密码只用来认证，认证完成，不能向token中保存密码信息，所以要设置为null
            user.setPassword(null);
            return new SimpleAuthenticationInfo(user, password, this.getName());
        }
    }
}
