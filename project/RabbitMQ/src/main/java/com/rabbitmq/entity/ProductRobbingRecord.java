package com.rabbitmq.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProductRobbingRecord {
    private Integer id;

    private String mobile;

    private Integer productId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date robbingTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    public ProductRobbingRecord(Integer id, String mobile, Integer productId, Date robbingTime, Date updateTime) {
        this.id = id;
        this.mobile = mobile;
        this.productId = productId;
        this.robbingTime = robbingTime;
        this.updateTime = updateTime;
    }

    public ProductRobbingRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getRobbingTime() {
        return robbingTime;
    }

    public void setRobbingTime(Date robbingTime) {
        this.robbingTime = robbingTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}