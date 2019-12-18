package com.userauth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * accessToken的内部字段信息
 * 生成的新的token，以此对象的方式存储
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenDto implements Serializable {

    private Integer userId;

    private String userName;

    private Long timestamp;

    private String randomStr;

    private Long expire;

    public AccessTokenDto(int i, String debug, long l, String s) {
    }
}

