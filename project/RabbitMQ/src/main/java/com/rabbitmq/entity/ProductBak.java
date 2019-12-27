package com.rabbitmq.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProductBak {
    private Integer id;

    private String name;

    private Integer stock;

    private Date purchaseDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    private Integer isActive;

    public ProductBak(Integer id, String name, Integer stock, Date purchaseDate, Date createTime, Date updateTime, Integer isActive) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.purchaseDate = purchaseDate;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isActive = isActive;
    }

    public ProductBak() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}