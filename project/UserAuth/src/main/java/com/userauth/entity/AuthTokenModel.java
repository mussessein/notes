package com.userauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 构造一个Token的实体类：用于返回给前端
 * 1. 存储token
 * 2. 存储token的失效时间
 */
@Data
@AllArgsConstructor // 添加所有字段的构造函数
@NoArgsConstructor  //创建一个无参构造函数
public class AuthTokenModel implements Serializable {

    private String accessToken;

    private Long accessExpire;

}