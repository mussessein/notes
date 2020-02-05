package com.secondkill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 秒杀成功的记录
 * 1. 秒杀的订单号
 * 2. 商品id
 * 3. 秒杀id
 * 4. 用户id
 * 5. 状态：秒杀未付款，已付款，已取消，失效（超时）
 * 6. 创建时间
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemKillSuccess {
    private String code;

    private Integer itemId;

    private Integer killId;

    private String userId;

    private Byte status;

    private Date createTime;

    private Integer diffTime;

    public ItemKillSuccess(String code, Integer itemId, Integer killId, String userId, Byte status, Date createTime) {
        this.code = code;
        this.itemId = itemId;
        this.killId = killId;
        this.userId = userId;
        this.status = status;
        this.createTime = createTime;
    }
}