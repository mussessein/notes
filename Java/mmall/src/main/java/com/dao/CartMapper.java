package com.dao;

import com.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);
    // 整个对象的字段，都插入
    int insert(Cart record);
    // 可选字段插入
    int insertSelective(Cart record);
    // 根据主键查询对象
    Cart selectByPrimaryKey(Integer id);
    // 根据主键更新,字段可选
    int updateByPrimaryKeySelective(Cart record);
    // 根据主键，更新整个对象
    int updateByPrimaryKey(Cart record);
    // 查询此user是否添加了某个商品
    Cart selectCartByUserIdProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);
    // 通过userId,查询到此user,添加的所有商品,返回一个商品集合
    List<Cart> selectCartByUserId(Integer userId);
    // 查询有没有 未被勾选的单位
    int selectCartProductCheckedStatusByUserId(Integer userId);
    // 删除商品:就是删除Cart表中的记录
    int deleteByUserIdProductIds(@Param("userId") Integer userId, @Param("productIdList") List<String> productIdList);
    // 全选:修改当前用户下所有记录的checked字段
    int checkedOrUncheckProduct(@Param("userId") Integer userId, @Param("checked") Integer checked,@Param("productId") Integer productId);
    // 查询此用户的购物车全部商品数量
    int getCartProductCount(@Param("userId")Integer userId);

    List<Cart> selectCheckedCartByUserId(Integer userId);
}