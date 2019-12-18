package com.userauth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/*
User 用户实体类
 */
@Data
public class User  implements Serializable {
    private Integer id;

    private String userName;

    private String password;

    private String phone;

    private String email;

    private Byte isActive;

    private Date createTime;

    private Date updateTime;
}