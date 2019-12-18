package com.userauth.service;

import com.userauth.dto.UpdatePsdDto;
import com.userauth.entity.AuthTokenModel;
import com.userauth.entity.User;
import com.userauth.enums.Constant;
import com.userauth.mapper.UserMapper;
import com.userauth.response.BaseResponse;
import com.userauth.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class JwtTokenService {
    @Autowired
    private UserMapper userMapper;
    // 登录认证，创建token
    @Transactional(rollbackFor = Exception.class)
    public AuthTokenModel authAndCreateToken(String userName, String password) {
        // 数据库比对账号密码
        User user = userMapper.selectByUserName(userName);
        // 用户存在，创建token
        if (user != null) {
            String accessToken = JwtUtil.createJWT(user.getId().toString(), userName, Constant.ACCESS_TOKEN_EXPIRE);
            log.info("==========jwt用户认证成功生成accessToken==========");
            AuthTokenModel tokenModel = new AuthTokenModel(accessToken, Constant.ACCESS_TOKEN_EXPIRE);
            return tokenModel;
        }
        return null;
    }

    //jwt验证解析token
    public BaseResponse validateToken(final String accessToken){
        return JwtUtil.validateJWT(accessToken);
    }

    /**
     * 修改密码
     * */
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(final String accessToken, final UpdatePsdDto dto)throws Exception{
        if (StringUtils.isNotBlank(accessToken)){
            // 解析access token，获取用户信息
            Claims claims=JwtUtil.parseJWT(accessToken);
            if (claims==null){
                throw new RuntimeException("当前Token无效！");
            }
            //核心业务逻辑：修改密码
            User user=userMapper.selectByUserName(claims.getSubject());
            if (user==null){
                throw new RuntimeException("当前Token对应的是无效的用户！");
            }
            if (!user.getPassword().equals(dto.getOldPassword())){
                throw new RuntimeException("旧密码不匹配！");
            }
            //修改密码
            int res=userMapper.updatePassword(claims.getSubject(),dto.getOldPassword(),dto.getNewPassword());
            if (res<=0){
                throw new RuntimeException("修改密码失败请重新尝试或者联系管理员！");
            }
        }
    }
}
