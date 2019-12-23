package com.secondkill.mapper;

import com.secondkill.dto.KillSuccessUserInfo;
import com.secondkill.entity.ItemKillSuccess;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemKillSuccessMapper {
    int deleteByPrimaryKey(String code);

    int insert(ItemKillSuccess record);

    int insertSelective(ItemKillSuccess record);

    ItemKillSuccess selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ItemKillSuccess record);

    int updateByPrimaryKey(ItemKillSuccess record);
    // 查询此商品，此用户，是否已经完成了订单，并且没有被取消
    int countByKillUserId(@Param("killId") Integer killId, @Param("userId") Integer userId);
    // 通过秒杀成功的订单号，查询用户信息，发送邮件
    KillSuccessUserInfo selectByCode(@Param("orderNo")String orderNo);

    int expireOrder(@Param("code") String code);

    List<ItemKillSuccess> selectExpireOrders();
}