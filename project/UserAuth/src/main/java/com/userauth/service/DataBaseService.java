package com.userauth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userauth.dto.AccessTokenDto;
import com.userauth.dto.UpdatePsdDto;
import com.userauth.entity.AuthToken;
import com.userauth.entity.AuthTokenModel;
import com.userauth.entity.Log;
import com.userauth.entity.User;
import com.userauth.enums.Constant;
import com.userauth.enums.StatusCode;
import com.userauth.mapper.AuthTokenMapper;
import com.userauth.mapper.UserMapper;
import com.userauth.response.BaseResponse;
import com.userauth.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Token+Database的service
 * 数据库存储token的service层
 * 主要方法：
 * 1. authAndCreateToken：登录认证完成之后：创建token
 * 2. 验证解析token
 * 3. 失效token处理
 * 4. 实际业务：修改密码
 * 5. 实际业务：新增用户
 */
@Service
@Slf4j
public class DataBaseService {

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AuthTokenMapper authTokenMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommonService commonService;
    /**
     * 登录创建token
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    public AuthTokenModel authAndCreateToken(String userName,String password) throws Exception {
        // 用户账号密码认证
        User user = userService.authUser(userName, password);
        // 用户认证通过，返回user!=null
        if (user != null) {
            // 每次登录，都确保之前的token失效，再重新创建
            // 使当前用户的token无效，isActice=0(新增dao层方法)
            authTokenMapper.invalidateTokenByUser(user.getId());
            /**
             * 创建token
             * 1. 拿到时间戳
             * 2. 组装一个当前用户的Token信息对象=>AccessTokenDto(userid,username,时间戳，雪花算法唯一id，token失效时间长度)
             * 3. 将组转好的对象序列化（jackson的databind）
             * 4. 对生成的json进行AES加密==>得到最终的Token
             */
            long timestamp = System.currentTimeMillis();
            AccessTokenDto tokenDto = new AccessTokenDto(user.getId(), user.getUserName(), timestamp, Constant.snowFlake.nextId().toString(), Constant.ACCESS_TOKEN_EXPIRE);
            String tokenDtoJson = objectMapper.writeValueAsString(tokenDto);
            log.info("===========tokenDtoJson：{}===========",tokenDtoJson);
            String accessToken = EncryptUtil.aesEncrypt(tokenDtoJson, Constant.TOKEN_AUTH_KEY);
            /**
             * 将生成的token，组装为当前用户的token记录
             */
            AuthToken authToken = new AuthToken();
            authToken.setUserId(user.getId());
            authToken.setAccessToken(accessToken);
            authToken.setAccessExpire(Constant.ACCESS_TOKEN_EXPIRE);
            authToken.setTokenTimestamp(timestamp);
            authToken.setCreateTime(DateTime.now().toDate());
            authTokenMapper.insertSelective(authToken);

            log.info("========成功生成Token记录到数据库========");
            /**
             * 生成model对象，返回给前端
             */
            AuthTokenModel authTokenModel = new AuthTokenModel(accessToken, Constant.ACCESS_TOKEN_EXPIRE);
            return authTokenModel;
        }
        return null;
    }

    /**
     * 验证-解析token
     * 用于在preHandler中，进行验证解析
     */
    public BaseResponse validateToken(final String accessToken){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            if (StringUtils.isBlank(accessToken)){
                return new BaseResponse(StatusCode.AccessTokenNotBlank);
            }
            // 数据库查询验证这个token是否存在
            AuthToken authToken=authTokenMapper.selectByAccessToken(accessToken);
            // token不存在
            if (authToken==null){
                return new BaseResponse(StatusCode.AccessTokenNotExist);
            }

            AccessTokenDto dto;
            //为了防止token的伪造，会主动额外做异步操作-解析token
            try {
                dto=parseAccessToken(accessToken);
            }catch (Exception e){
                return new BaseResponse(StatusCode.AccessTokenInvalidate);
            }

            if (dto!=null){
                //验证token是否过期：当前时间-token创建时间 > token失效时间
                if (System.currentTimeMillis() - dto.getTimestamp() > dto.getExpire()){
                    // token失效处理
                    this.invalidateByAccessToken(accessToken);
                    return new BaseResponse(StatusCode.TokenValidateExpireToken);
                }
            }
        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    //解密accessToken
    public AccessTokenDto parseAccessToken(final String accessToken) throws Exception{
        // AES解密出个人信息json
        String jsonStr=EncryptUtil.aesDecrypt(accessToken,Constant.TOKEN_AUTH_KEY);
        // 用jackson按照AccessTokenDto对象格式，反向解析，将json还原为对象，有效，则返回true
        return objectMapper.readValue(jsonStr,AccessTokenDto.class);
    }

    //失效access token
    public void invalidateByAccessToken(final String accessToken){
        if (StringUtils.isNotBlank(accessToken)){
            // 在数据库中，将此token设置为失效（is_actice=0）
            authTokenMapper.invalidateByToken(accessToken);
        }
    }

    /**
     * 修改密码
     * 需要：
     * 1. 从token中解析到用户信息
     * 2. 更新密码，
     * 3. 失效掉之前的token
     */
    @Transactional(rollbackFor = Exception.class)
    public void     updatePassword(final String accessToken, final UpdatePsdDto updatePsdDto)throws Exception{
        if (StringUtils.isNotBlank(accessToken)){
            //解析access token，获取用户信息
            AccessTokenDto tokenDto=parseAccessToken(accessToken);

            //修改密码
            int res=userMapper.updatePassword(tokenDto.getUserName(),updatePsdDto.getOldPassword(),updatePsdDto.getNewPassword());
            if (res<=0){
                throw new RuntimeException("修改密码失败~旧密码不正确。。。！");
            }

            //失效掉以前有效的token
            authTokenMapper.invalidateTokenByUser(tokenDto.getUserId());
        }
    }
    //实际业务模块操作-新增用户
    public void saveUser(final String accessToken,User user) throws Exception{
        //解析token 获取用户信息
        AccessTokenDto tokenDto=this.parseAccessToken(accessToken);

        //新增用户
        user.setCreateTime(DateTime.now().toDate());
        userMapper.insertSelective(user);

        //记录日志
        Log entity=new Log(null,tokenDto.getUserId(),tokenDto.getUserName(),new Date(),"新增用户");
        commonService.insertLog(entity);
    }

}
