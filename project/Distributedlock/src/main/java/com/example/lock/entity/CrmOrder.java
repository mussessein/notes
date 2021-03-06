package com.example.lock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * 抢单记录表
 * 抢的就是mobile手机号
 */
@Data
public class CrmOrder {
    private Integer id;

    private Integer userId;

    private String mobile;

    private Date createTime;

    private Date updateTime;

    private Integer isActive;
}