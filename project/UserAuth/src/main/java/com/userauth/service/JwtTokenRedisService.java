package com.userauth.service;

import com.userauth.dto.UpdatePsdDto;
import com.userauth.entity.AuthTokenModel;
import com.userauth.entity.User;
import com.userauth.enums.Constant;
import com.userauth.enums.StatusCode;
import com.userauth.mapper.UserMapper;
import com.userauth.response.BaseResponse;
import com.userauth.utils.JwtRedisUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class JwtTokenRedisService {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserMapper userMapper;
    /**
     * JWT+Redis登录
     * 定义JwtRedisUtil方法，生成Token
     */
    public AuthTokenModel authAndCreateToken(String userName, String password) {
        User user = userService.authUser(userName, password);
        if (user != null) {
            String accessToken = JwtRedisUtil.createJWT(user.getId().toString(), userName);
            // 在jwt的基础上，将token放入redis
            ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
            valueOperations.set(Constant.JWT_TOKEN_REDIS_KEY_PREFIX+userName,accessToken,Constant.ACCESS_TOKEN_EXPIRE, TimeUnit.MILLISECONDS);
            log.info("============jwtRedis认证完成============");
            AuthTokenModel authTokenModel = new AuthTokenModel(accessToken, Constant.ACCESS_TOKEN_EXPIRE);
            return authTokenModel;
        }
        return null;
    }

    // jwt验证解析token
    public BaseResponse validateToken(final String accessToken){
        try {
            //验证token的合法性全部交给jwt
            Claims claims=JwtRedisUtil.validateJWT(accessToken);
            if (claims==null || StringUtils.isBlank(claims.getId())){
                return new BaseResponse(StatusCode.AccessTokenInvalidate);
            }
            //失效过期等判断交给缓存中间件redis
            final String key=Constant.JWT_TOKEN_REDIS_KEY_PREFIX+claims.getSubject();
            if (!stringRedisTemplate.hasKey(key)){
                return new BaseResponse(StatusCode.AccessTokenNotExistRedis);
            }
            //最后是校验一下当前过来的Token是否是之前采用加密算法生成的Token并存于缓存中 ~ 直接演示
            ValueOperations<String,String> valueOperations=stringRedisTemplate.opsForValue();
            String redisToken=valueOperations.get(key);
            if (!accessToken.equals(redisToken)){
                return new BaseResponse(StatusCode.AccessTokenInvalidate);
            }
            //执行到下面这一步时，即代表验证成功
            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    /**
     * 修改密码
     */
    @Transactional(rollbackFor = {Exception.class})
    public void updatePassword(String accessToken, UpdatePsdDto dto) throws Exception {
        if (StringUtils.isNotBlank(accessToken)) {
            Claims claims = JwtRedisUtil.validateJWT(accessToken);
            String userName = claims.getSubject();
            User user = userMapper.selectByUserName(userName);
            if (user == null) {
                throw new RuntimeException("无效Token");
            } else if (!user.getPassword().equals(dto.getOldPassword())) {
                // 密码不对
                throw new RuntimeException("旧密码不正确");
            }
            int result = userMapper.updatePassword(userName, dto.getOldPassword(), dto.getNewPassword());
            if (result < 0) {
                throw new RuntimeException("修改失败");
            }
            // 修改完成，失效之前的token
            this.invalidateByAccessToken(accessToken);
        }
    }

    //失效access token
    public void invalidateByAccessToken(final String accessToken) throws Exception{
        if (StringUtils.isNotBlank(accessToken)){
            //正确解析access token
            Claims claims=JwtRedisUtil.validateJWT(accessToken);
            final String key=Constant.JWT_TOKEN_REDIS_KEY_PREFIX+claims.getSubject();
            if (stringRedisTemplate.hasKey(key)){
                stringRedisTemplate.delete(key);
            }
        }
    }
}
