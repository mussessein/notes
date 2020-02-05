package com.secondkill.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 被列入秒杀商品的商品信息
 * 1. id
 * 2. 商品id
 * 3. 秒杀数量
 * 4. 秒杀开始时间
 * 5. 秒杀结束时间
 * 6. 此商品是否活跃
 * 7. 创建时间
 * 8. canKill：代表商品在秒杀时间段内
 */
@Data
public class ItemKill {
    private Integer id;

    private Integer itemId;

    private Integer total;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    private Byte isActive;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String itemName;

    private Integer canKill;

}