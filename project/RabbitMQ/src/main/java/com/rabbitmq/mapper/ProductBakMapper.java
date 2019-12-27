package com.rabbitmq.mapper;

import com.rabbitmq.entity.ProductBak;

public interface ProductBakMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductBak record);

    int insertSelective(ProductBak record);

    ProductBak selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductBak record);

    int updateByPrimaryKey(ProductBak record);
}