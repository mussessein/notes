package com.example.lock.mapper;

import com.example.lock.entity.CrmOrder;
import org.apache.ibatis.annotations.Param;

public interface CrmOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrmOrder record);

    int insertSelective(CrmOrder record);

    CrmOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrmOrder record);

    int updateByPrimaryKey(CrmOrder record);
    // 查询此订单是否已经被人抢了，
    int countByMobile(@Param("mobile") String mobile);

}