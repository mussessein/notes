package com.userauth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userauth.dto.AccessTokenDto;
import com.userauth.dto.UpdatePsdDto;
import com.userauth.entity.AuthTokenModel;
import com.userauth.entity.User;
import com.userauth.enums.Constant;
import com.userauth.enums.StatusCode;
import com.userauth.mapper.UserMapper;
import com.userauth.response.BaseResponse;
import com.userauth.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserMapper userMapper;

    /**
     * Redis+token：
     * 创建token
     * 将token存入redis中
     */
    @Transactional(rollbackFor = Exception.class)
    public AuthTokenModel authAndCreateToken(String userName, String password) throws Exception{
        // 数据库验证登录
        User user=userService.authUser(userName,password);
        if (user!=null){
            //创建token
            Long timestamp=System.currentTimeMillis();
            AccessTokenDto tokenDto=new AccessTokenDto(user.getId(),userName,timestamp,Constant.snowFlake.nextId().toString(), Constant.ACCESS_TOKEN_EXPIRE);
            String jsonStr=objectMapper.writeValueAsString(tokenDto);
            log.info("----json格式的access token字符串：{}",jsonStr);
            // AES加密
            String accessToken= EncryptUtil.aesEncrypt(jsonStr,Constant.TOKEN_AUTH_KEY);
            // redis缓存存储token
            ValueOperations<String,String> valueOperations=stringRedisTemplate.opsForValue();
            // key：UserAuth:userName；value：失效时间
            valueOperations.set(Constant.TOKEN_REDIS_KEY_PREFIX+userName,accessToken,Constant.ACCESS_TOKEN_EXPIRE, TimeUnit.MILLISECONDS);

            log.info("--token+redis用户认证成功，成功生成accessToken--");
            AuthTokenModel tokenModel=new AuthTokenModel(accessToken,Constant.ACCESS_TOKEN_EXPIRE);
            return tokenModel;
        }
        return null;
    }
    //验证-解析token
    public BaseResponse validateToken(final String accessToken){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if (StringUtils.isBlank(accessToken)){
                return new BaseResponse(StatusCode.AccessTokenNotBlank);
            }
            //解析token(为了防止别人伪造token)
            AccessTokenDto tokenDto=this.parseAccessToken(accessToken);
            if (StringUtils.isBlank(tokenDto.getUserName())){
                return new BaseResponse(StatusCode.AccessTokenInvalidate);
            }
            //定义缓存的key
            final String key=Constant.TOKEN_REDIS_KEY_PREFIX+tokenDto.getUserName();
            //判断key是否存在，如果存在的话，那就意味着accessToken是存在
            Boolean exist=stringRedisTemplate.hasKey(key);
            if (!exist){
                return new BaseResponse(StatusCode.AccessTokenNotExist);
            }
            //最后是校验一下当前过来的Token是否是之前采用加密算法生成的Token并存于缓存中 ~ 直接演示
            ValueOperations<String,String> valueOperations=stringRedisTemplate.opsForValue();
            String redisToken=valueOperations.get(key);
            if (!accessToken.equals(redisToken)){
                return new BaseResponse(StatusCode.AccessTokenInvalidate);
            }
        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //解密accessToken
    public AccessTokenDto parseAccessToken(final String accessToken) throws Exception{
        String jsonStr=EncryptUtil.aesDecrypt(accessToken,Constant.TOKEN_AUTH_KEY);
        return objectMapper.readValue(jsonStr,AccessTokenDto.class);
    }


    //修改密码
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(final String accessToken, final UpdatePsdDto dto)throws Exception{
        if (StringUtils.isNotBlank(accessToken)){
            //解析access token，获取用户信息
            AccessTokenDto tokenDto=this.parseAccessToken(accessToken);

            //核心业务逻辑：修改密码
            User user=userMapper.selectByUserName(tokenDto.getUserName());
            if (user==null){
                throw new RuntimeException("当前Token对应的是无效的用户！");
            }
            if (!user.getPassword().equals(dto.getOldPassword())){
                throw new RuntimeException("旧密码不匹配！");
            }
            //修改密码
            int res=userMapper.updatePassword(tokenDto.getUserName(),dto.getOldPassword(),dto.getNewPassword());
            if (res<=0){
                throw new RuntimeException("修改密码失败~请重新尝试或者联系管理员！");
            }

            //失效掉以前有效的token
            invalidateByAccessToken(accessToken);
        }
    }

    //失效access token
    public void invalidateByAccessToken(final String accessToken) throws Exception{
        if (StringUtils.isNotBlank(accessToken)){
            //正确解析access token
            AccessTokenDto tokenDto=this.parseAccessToken(accessToken);
            if (StringUtils.isNotBlank(tokenDto.getUserName())){
                final String key=Constant.TOKEN_REDIS_KEY_PREFIX+tokenDto.getUserName();
                if (stringRedisTemplate.hasKey(key)){
                    stringRedisTemplate.delete(key);
                }
            }
        }
    }
}
