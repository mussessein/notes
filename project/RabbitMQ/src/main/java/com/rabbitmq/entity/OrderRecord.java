package com.rabbitmq.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OrderRecord {
    private Integer id;

    private String orderNo;

    private String orderType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    public OrderRecord(Integer id, String orderNo, String orderType, Date createTime, Date updateTime) {
        this.id = id;
        this.orderNo = orderNo;
        this.orderType = orderType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public OrderRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}