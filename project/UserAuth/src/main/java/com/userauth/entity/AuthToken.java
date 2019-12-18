package com.userauth.entity;

import lombok.Data;

import java.util.Date;

/**
 * User——token实体类
 * 存储：
 * 1. token的状态
 * 2. token过期时间
 * 3. token的创建时间
 * 4. token的状态 isActive
 * 5. 此条记录的创建时间
 * 6. 记录的更新时间
 */
@Data
public class AuthToken {
    private Integer id;

    private Integer userId;

    private String accessToken;

    private Long accessExpire;

    private Long tokenTimestamp;

    private Byte isActive;

    private Date createTime;

    private Date updateTime;
}