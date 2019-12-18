package com.userauth.enums;
import com.userauth.utils.SnowFlake;

public class Constant {

    //token
    public static final int RESCODE_REFTOKEN_MSG = 1006;		//刷新TOKEN(有返回数据)
    public static final int RESCODE_REFTOKEN = 1007;			//刷新TOKEN

    public static final int JWT_ERRCODE_NULL = 4000;			//Token不存在
    public static final int JWT_ERRCODE_EXPIRE = 4001;			//Token过期
    public static final int JWT_ERRCODE_FAIL = 4002;			//验证不通过

    // JWT密匙
    public static final String JWT_SECRET = "8677df7fc3a34e26a61c034d5ec8245d";
    // token有效时间 60m
    public static final long JWT_TTL = 3600 * 1000;

    public static final String TOKEN_ISSUER="userAuth";

    //token失效的时间：ms为单位
    public static final Long ACCESS_TOKEN_EXPIRE=600000L;
    public static final Long REFRESH_TOKEN_EXPIRE=60000L;
    //session失效的时间：s为单位
    public static final Integer SESSION_EXPIRE=30;

    public static final SnowFlake snowFlake=new SnowFlake(3,2);

    public static final String TOKEN_AUTH_KEY="e2bd6cee47e0402db80862a09ff4dfd6";

    public static final String TOKEN_REDIS_KEY_PREFIX="UserAuth:";

    public static final String JWT_TOKEN_REDIS_KEY_PREFIX="UserAuth:";

    public static final Integer SERVER_ERROR=500;

    public static final Integer PAGE_NOT_FOUND=404;
}
