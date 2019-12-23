package com.secondkill.service.impl;


import com.secondkill.entity.ItemKill;
import com.secondkill.entity.ItemKillSuccess;
import com.secondkill.enums.SysConstant;
import com.secondkill.mapper.ItemKillMapper;
import com.secondkill.mapper.ItemKillSuccessMapper;
import com.secondkill.service.IKillService;
import com.secondkill.service.RabbitSenderService;
import com.secondkill.utils.RandomUtil;
import com.secondkill.utils.SnowFlake;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.joda.time.DateTime;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.connection.RabbitAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author whr
 * 秒杀业务
 */
@Service
@Slf4j
public class KillService implements IKillService {

    private SnowFlake snowFlake=new SnowFlake(2,3);

    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;
    @Autowired
    private ItemKillMapper itemKillMapper;
    @Autowired
    private RabbitSenderService rabbitSenderService;

     /**
     * 秒杀逻辑判断之后，确定用户成功秒杀了：
     * 1. 记录用户秒杀成功的订单信息
     * 2. 并进行异步邮件通知
     */
    private void commonRecordKillSuccessInfo(ItemKill kill,Integer userId) throws Exception{
        //TODO:记录抢购成功后生成的秒杀订单记录
        ItemKillSuccess entity=new ItemKillSuccess();
        String orderNo=String.valueOf(snowFlake.nextId());

        //entity.setCode(RandomUtil.generateOrderCode());   //传统时间戳+N位随机数
        entity.setCode(orderNo); //雪花算法
        entity.setItemId(kill.getItemId());
        entity.setKillId(kill.getId());
        entity.setUserId(userId.toString());
        entity.setStatus(SysConstant.OrderStatus.SuccessNotPayed.getCode().byteValue());
        entity.setCreateTime(DateTime.now().toDate());
        //TODO:学以致用，举一反三 -> 仿照单例模式的双重检验锁写法
        if (itemKillSuccessMapper.countByKillUserId(kill.getId(),userId) <= 0){
            int res=itemKillSuccessMapper.insertSelective(entity);

            if (res>0){
                //TODO:进行异步邮件消息的通知=rabbitmq+mail
                rabbitSenderService.sendKillSuccessEmailMsg(orderNo);

                //TODO:入死信队列，用于 “失效” 超过指定的TTL时间时仍然未支付的订单
                rabbitSenderService.sendKillSuccessOrderExpireMsg(orderNo);
            }
        }
    }
    /**
     * 秒杀的核心业务逻辑——MySql版本
     * （并发量太低，在集群webServer下性能差）
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean killItemV1(Integer killId, Integer userId) throws Exception {
        Boolean result=false;
        // 判断当前用户是否已经抢购过该商品，同时要判断订单状态status
        if (itemKillSuccessMapper.countByKillUserId(killId, userId) <= 0) {
            /**
             * 没查到记录，表明可以下单
             * 1. 查询秒杀商品，是否还有库存
             * 2. 判断是否可以进行秒杀，有库存，时间合适
             * 3. 可以秒杀，库存减一
             */
            ItemKill itemKill = itemKillMapper.selectById(killId);
            if (itemKill != null && itemKill.getCanKill() == 1 && itemKill.getTotal() > 0) {
                int res = itemKillMapper.updateKillItem(killId);
                if (res > 0) {
                    // 库存扣除成功==>执行秒杀后逻辑（生成订单信息，发送邮件通知）
                    this.commonRecordKillSuccessInfo(itemKill,userId);
                    return true;
                }
            }else {
                throw new RuntimeException("秒杀失败");
            }
        }
        return result;
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * redis的分布式锁：
     * 1. 将同一个用户，在同一时间多次request的线程，交由redis的唯一key进行加锁；
     * 同一时间，只有一个线程能拿到锁，从而进行数据库操作；
     * 2. 这就将多个流量，降为1个有效流量
     * 3. 但是由bug：就是redis在set之后，宕机，会死锁！！！！！！！！！
     * @param killId
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Boolean killItemV2(Integer killId, Integer userId) throws Exception {
        Boolean result=false;
        // <=0说明没有记录，用户从来没有抢购过，就可以抢购
        if (itemKillSuccessMapper.countByKillUserId(killId,userId) <= 0){

            //TODO:借助Redis的原子操作实现分布式锁-对共享操作-资源进行控制
            ValueOperations valueOperations=stringRedisTemplate.opsForValue();
            final String key=new StringBuffer().append(killId).append(userId).append("-RedisLock").toString();
            final String value= RandomUtil.generateOrderCode();
            /**
             * 先行set成功的线程，就可以进入主逻辑，相当于拿到锁；
             * 其他线程，再进行set，都返回false；相当于拿不到锁
             * 这样，同一个用户的多个请求，实现屏蔽
             */
            Boolean cacheRes=valueOperations.setIfAbsent(key,value);
            // 如果在这里Redis节点宕机，key无法清除，重启Redis之后，cacheRes永远是false，永远拿不到锁
            if (cacheRes){
                // 被动释放锁：设置过期时间，防止没有释放掉，占用内存
                stringRedisTemplate.expire(key,100, TimeUnit.SECONDS);
                try {
                    ItemKill itemKill=itemKillMapper.selectByIdV2(killId);
                    if (itemKill!=null && 1==itemKill.getCanKill() && itemKill.getTotal()>0){
                        int res=itemKillMapper.updateKillItemV2(killId);
                        if (res>0){
                            commonRecordKillSuccessInfo(itemKill,userId);

                            result=true;
                        }
                    }
                }catch (Exception e){
                    throw new Exception("还没到抢购日期、已过了抢购时间或已被抢购完毕！");
                }finally {
                    // 手动释放锁
                    if (value.equals(valueOperations.get(key).toString())){
                        stringRedisTemplate.delete(key);
                    }
                }
            }
        }else{
            throw new Exception("Redis-您已经抢购过该商品了!");
        }
        return result;
    }
    @Autowired
    private RedissonClient redissonClient;

    /**
     * redisson的分布式锁
     * 1. 对上一步的redis的死锁进行优化；
     * 2.
     * @param killId
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Boolean killItemV3(Integer killId, Integer userId) throws Exception {
        Boolean result=false;

        final String lockKey=new StringBuffer().append(killId).append(userId).append("-RedissonLock").toString();
        RLock lock=redissonClient.getLock(lockKey);

        try {
            Boolean cacheRes=lock.tryLock(30,10,TimeUnit.SECONDS);
            if (cacheRes){
                //TODO:核心业务逻辑的处理
                if (itemKillSuccessMapper.countByKillUserId(killId,userId) <= 0){
                    ItemKill itemKill=itemKillMapper.selectByIdV2(killId);
                    if (itemKill!=null && 1==itemKill.getCanKill() && itemKill.getTotal()>0){
                        int res=itemKillMapper.updateKillItemV2(killId);
                        if (res>0){
                            commonRecordKillSuccessInfo(itemKill,userId);

                            result=true;
                        }
                    }
                }else{
                    throw new Exception("redisson-您已经抢购过该商品了!");
                }
            }
        }finally {
            lock.unlock();
            //lock.forceUnlock();
        }
        return result;
    }


    @Autowired
    private CuratorFramework curatorFramework;

    private static final String pathPrefix="/zkLock/";

    /**
     * 商品秒杀核心业务逻辑的处理-基于ZooKeeper的分布式锁
     * @param killId
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Boolean killItemV4(Integer killId, Integer userId) throws Exception {
        Boolean result=false;
    /**
     * killID+userID保证节点的唯一性
     * 保证了逻辑上，一个人只能抢购一件商品;
     */
        InterProcessMutex mutex=new InterProcessMutex(curatorFramework,pathPrefix+killId+userId+"-lock");
        try {
            //     获取锁：
            if (mutex.acquire(10L,TimeUnit.SECONDS)){

                //TODO:核心业务逻辑
                if (itemKillSuccessMapper.countByKillUserId(killId,userId) <= 0){
                    ItemKill itemKill=itemKillMapper.selectByIdV2(killId);
                    if (itemKill!=null && 1==itemKill.getCanKill() && itemKill.getTotal()>0){
                        int res=itemKillMapper.updateKillItemV2(killId);
                        if (res>0){
                            commonRecordKillSuccessInfo(itemKill,userId);
                            result=true;
                        }
                    }
                }else{
                    throw new Exception("zookeeper-您已经抢购过该商品了!");
                }
            }
        }catch (Exception e){
            throw new Exception("还没到抢购日期、已过了抢购时间或已被抢购完毕！");
        }finally {
            if (mutex!=null){
             // 释放锁
                mutex.release();
            }
        }
        return result;
    }


}
