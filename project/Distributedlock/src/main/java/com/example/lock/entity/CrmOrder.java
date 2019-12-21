package com.example.lock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrmOrder {
    private Integer id;

    private Integer userId;

    private String mobile;

    private Date createTime;

    private Date updateTime;

    private Integer isActive;
}