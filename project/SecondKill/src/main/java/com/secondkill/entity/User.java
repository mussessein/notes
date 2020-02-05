package com.secondkill.entity;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private Integer id;

    private String userName;

    private String password;

    private String phone;

    private String email;

    private Byte isActive;

    private Date createTime;

    private Date updateTime;


}