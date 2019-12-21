package com.example.lock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    private String userName;

    private String email;

    private Date createTime;

    private Date updateTime;
}