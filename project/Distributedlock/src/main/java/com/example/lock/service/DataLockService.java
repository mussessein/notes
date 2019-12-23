package com.example.lock.service;

import com.example.lock.components.RedissonDistributeLock;
import com.example.lock.dto.ProductLockDto;
import com.example.lock.entity.ProductLock;
import com.example.lock.mapper.ProductLockMapper;
import com.mysql.jdbc.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    /**
     * Redis——原子操作实现分布式锁
     *
     */
    final static String redis_prefix = "REDIS_PRODUCT_KEY:";
    final static String countKey = "REDIS_COUNT";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Transactional(rollbackFor = Exception.class)
    public int updateStock_4(ProductLockDto dto) throws InterruptedException {
        int res =0;
        // 拼装key,value
        final String key = String.format(redis_prefix +dto.getId());
        Boolean flag = true;
        while (flag) {
            String value = UUID.randomUUID().toString() + System.nanoTime();
            // 设置锁：SETNX
            flag = stringRedisTemplate.opsForValue().setIfAbsent(key, value);
            if (flag){
                // 拿到锁
                stringRedisTemplate.expire(key,5, TimeUnit.SECONDS);//被动释放锁，防止宕机无法释放锁
                stringRedisTemplate.opsForValue().increment(countKey,1L);//记录请求数
                try{
                    flag = false;
                    ProductLock entity = productLockMapper.selectByIdNegative(dto.getId());
                    // 判断version字段，库存是否充足,库存不足，直接跳finally
                    if (entity!=null && entity.getStock().compareTo(dto.getStock())>=0){
                        entity.setStock(dto.getStock());
                        // 使用version
                        res = productLockMapper.updateStock_3(entity);
                        if (res >= 0) {
                            log.info("分布式锁操作成功减少库存=>{}",dto.getStock());
                        }
                    }
                }catch (Exception e){
                    log.error("分布式锁失败:{}",e.getMessage());
                }finally {
                    // finally释放锁
                    String redisValue = stringRedisTemplate.opsForValue().get(key);
                    if (!StringUtils.isNullOrEmpty(redisValue) && redisValue.equals(value)) {
                        stringRedisTemplate.delete(key);
                    }
                }
            }else {
                // 没有拿到锁
                flag=true;
                Thread.sleep(1000);
            }
        }
        return res;
    }

    /**
     * zookeeper分布式锁测试
     */
//    private final static String zk_prefix = "/stock_lock_";
//    @Autowired
//    private CuratorFramework client;
//    @Transactional(rollbackFor = Exception.class)
//    public int updateStock_5(ProductLockDto dto) throws Exception {
//        int res =0;
//        InterProcessMutex mutex=new InterProcessMutex(client,zk_prefix+dto.getId());
//        try {
//            if (mutex.acquire(10L,TimeUnit.SECONDS)){
//                // TODO: 业务逻辑
//
//                ProductLock entity = productLockMapper.selectByIdNegative(dto.getId());
//                // 判断version字段，库存是否充足
//                if (entity!=null && entity.getStock().compareTo(dto.getStock())>=0){
//                    entity.setStock(dto.getStock());
//                    res = productLockMapper.updateStock_3(entity);
//                    if (res > 0) {
//                        log.info("减少库存=>{}",dto.getStock());
//                    }
//                }
//            }else {
//                throw new RuntimeException("zookeeper分布式锁获取失败");
//            }
//        } catch (Exception e) {
//            log.error("zookeeper分布式锁失败:{}",e.getMessage());
//        }finally {
//            // TODO：释放锁
//            mutex.release();
//        }
//        return res;
//    }

    /**
     * 分布式锁：Redisson
     * 无法处理所有请求
     */
    @Autowired
    private RedissonDistributeLock redissonLock;
    @Transactional(rollbackFor = Exception.class)
    public int updateStock_6(ProductLockDto dto) throws Exception {
        int res =0;
        // 获取锁
        RLock rLock=redissonLock.acquireLock(String.valueOf(dto.getId()));
        try {
            if (rLock!=null){
                // TODO: 业务逻辑
                ProductLock entity = productLockMapper.selectByIdNegative(dto.getId());
                // 判断version字段，库存是否充足
                if (entity!=null && entity.getStock().compareTo(dto.getStock())>=0){
                    entity.setStock(dto.getStock());
                    res = productLockMapper.updateStock_3(entity);
                    if (res > 0) {
                        log.info("减少库存=>{}",dto.getStock());
                    }
                }
            }else {
                throw new RuntimeException("redisson分布式锁获取失败");
            }
        } catch (Exception e) {
            log.error("redisson分布式锁失败:{}",e.getMessage());
        }finally {
            // TODO：释放锁
            redissonLock.realeaseLock(rLock);
        }
        return res;
    }





}
