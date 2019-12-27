package com.rabbitmq.mapper;

import com.rabbitmq.entity.OrderReport;

public interface OrderReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderReport record);

    int insertSelective(OrderReport record);

    OrderReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderReport record);

    int updateByPrimaryKey(OrderReport record);
}