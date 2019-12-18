package com.userauth.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户操作日志记录表
 */
@Data
public class Log {
    private Integer id;
    private Integer userId;
    private String userName;
    private Date createTime;
    private String memo;

    public Log(Object o, Integer userId, String userName, Date date, String 新增用户) {
    }
}