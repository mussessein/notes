package com.example.lock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLock {
    private Integer id;

    private String productNo;

    private Integer stock;

    private Integer version;

    private Date createTime;

    private Date updateTime;
}