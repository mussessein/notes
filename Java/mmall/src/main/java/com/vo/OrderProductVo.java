package com.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车中,被勾选产品的一个vo对象
 */
public class OrderProductVo {
    // 被勾选的对象vo集合
    private List<OrderItemVo> orderItemVoList;
    // 总价
    private BigDecimal productTotalPrice;
    // 图片url
    private String imageHost;

    public List<OrderItemVo> getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(List<OrderItemVo> orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}
