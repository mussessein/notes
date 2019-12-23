package com.example.lock.mapper;

import com.example.lock.entity.ProductLock;
import org.apache.ibatis.annotations.Param;

public interface ProductLockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductLock record);

    int insertSelective(ProductLock record);

    ProductLock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductLock record);

    int updateByPrimaryKey(ProductLock record);
    // 最初版本：更新库存
    int updateStock_1(ProductLock lock);
    // 乐观锁——version版本号
    int updateStock_2(ProductLock lock);
    // 悲观锁
    ProductLock selectByIdNegative(@Param(value = "id") Integer id);
    int updateStock_3(ProductLock lock);


}