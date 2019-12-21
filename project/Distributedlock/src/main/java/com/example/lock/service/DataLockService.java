package com.example.lock.service;

import com.example.lock.dto.ProductLockDto;
import com.example.lock.entity.ProductLock;
import com.example.lock.mapper.ProductLockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service层
 * 模拟高并发下数据库 库存更新操作：
 * 1. 数据库级——不加锁
 * 2. 数据库级别——悲观锁
 * 3. 数据库级别——乐观锁
 */
@Service
@Slf4j
public class DataLockService {

    @Autowired
    private ProductLockMapper productLockMapper;

    /**
     * 高并发：数据库更新——不加锁
     * 在高并发下 出现问题：
     * 1. 出现负库存
     * 2. 库存数量大于 实际数量
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateStock_1(ProductLockDto dto) throws Exception{
        int res=0;
        ProductLock entity=productLockMapper.selectByPrimaryKey(dto.getId());
        // 判断：要更新的库存 > 当前库存
        if (entity!=null && entity.getStock().compareTo(dto.getStock())>=0){
            entity.setStock(dto.getStock());
            return productLockMapper.updateStock_1(entity);
        }
        return res;
    }

    /**
     * 数据库更新——乐观锁实现
     * 1. 在对应表中添加字段：version
     * 2. 查询出来的entity，带有version字段
     * 3. 在执行更新库存时：带上version字段进行判断
     *      （1）判断通过则更新，并递增version版本号；
     *      （2）判断不通过，不进行更新库存操作，不修改版本号；
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateStock_2(ProductLockDto dto){
        int res =0;
        // 查询出version字段
        ProductLock entity = productLockMapper.selectByPrimaryKey(dto.getId());
        // 判断version字段，库存是否充足
        if (entity!=null && entity.getStock().compareTo(dto.getStock())>=0){
            entity.setStock(dto.getStock());
            res = productLockMapper.updateStock_2(entity);
        }
        if (res > 0) {
            log.info("减少库存=>{}",dto.getStock());
        }
        return res;
    }

    /**
     * 悲观锁实现——for update
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateStock_3(ProductLockDto dto){
        int res =0;
        // select-for update 查询
        ProductLock entity = productLockMapper.selectByIdNegative(dto.getId());
        // 判断version字段，库存是否充足
        if (entity!=null && entity.getStock().compareTo(dto.getStock())>=0){
            entity.setStock(dto.getStock());
            res = productLockMapper.updateStock_3(entity);
        }
        if (res > 0) {
            log.info("减少库存=>{}",dto.getStock());
        }
        return res;
    }









}
