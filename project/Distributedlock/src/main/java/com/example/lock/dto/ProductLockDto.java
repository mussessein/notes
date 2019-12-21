package com.example.lock.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 用来更新商品库存的DTo
 * 模拟高并发场景下的商品库存更新
 * 1. 商品id
 * 2， 库存数
 */
@Data
@ToString
public class ProductLockDto {

    @NotNull
    private Integer id;

    @NotNull
    private Integer stock=1;
}
