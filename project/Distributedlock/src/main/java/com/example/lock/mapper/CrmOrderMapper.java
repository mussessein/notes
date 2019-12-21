package com.example.lock.mapper;

import com.example.lock.entity.CrmOrder;

public interface CrmOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrmOrder record);

    int insertSelective(CrmOrder record);

    CrmOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrmOrder record);

    int updateByPrimaryKey(CrmOrder record);
}