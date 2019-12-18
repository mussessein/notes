package com.userauth.utils;

import com.userauth.enums.Constant;
import com.userauth.enums.StatusCode;
import com.userauth.response.BaseResponse;
import io.jsonwebtoken.*;
import org.bouncycastle.util.encoders.Base64;
import org.joda.time.DateTime;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;


public class JwtUtil {
    // 生成密钥
    public static SecretKey generalKey(){
        byte[] encodeKey = Base64.decode(Constant.JWT_SECRET);
        SecretKeySpec key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
        return key;
    }
    // 创建token
    public static String createJWT(final String id ,final String subject,final Long expireMills){
        // 生成签名的算法 HS256
        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
        // 生成签名的密钥
        SecretKey key = generalKey();
        DateTime now = DateTime.now().toDateTime();
        // 第三方组件JWT API
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuer(Constant.TOKEN_ISSUER)
                .signWith(hs256, key);
        // 设置过期时间，当前时间+失效时间
        if (expireMills >= 0) {
            Long realExpire = System.currentTimeMillis() + expireMills;
            jwtBuilder.setExpiration(new Date(realExpire));
        }
        // 返回accessToken的字符串
        return jwtBuilder.compact();
    }

    // jwt验证解析token
    public static BaseResponse validateJWT(final String accessToken){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Claims claims=null;
        try {
            claims=parseJWT(accessToken);
            response.setData(claims);
        }catch (ExpiredJwtException e){
            response=new BaseResponse(StatusCode.TokenValidateExpireToken);
        }catch (SignatureException e){
            response=new BaseResponse(StatusCode.TokenValidateCheckFail);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.TokenValidateCheckFail);
        }
        return response;
    }

    // jwt解析token
    public static Claims parseJWT(final String accessToken) throws Exception{
        SecretKey key=generalKey();
        return Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken).getBody();
    }

}
