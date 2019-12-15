package com.dao;

import com.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);
    // 删除地址:需要userId,防止横向越权
    int deleteByShippingIdUserId(@Param("userId")Integer userId,@Param("shippingId")Integer shippingId);
    // 通过userId,更新
    int updateByShipping(Shipping record);
    // 查询,返回shipping对象
    Shipping selectByShippingIdUserId(@Param("userId") Integer userId, @Param("shippingId") Integer shippingId);
    // 查询此user下的所有地址,返回一个list
    List<Shipping> selectByUserId(@Param("userId") Integer userId);
}