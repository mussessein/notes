package com.example.lock.service;

import com.example.lock.components.RedissonDistributeLock;
import com.example.lock.dto.RobbingDto;
import com.example.lock.entity.CrmOrder;
import com.example.lock.listener.event.CrmOrderRobbingEvent;
import com.example.lock.mapper.CrmOrderMapper;
import com.google.common.base.Strings;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.ApplicationEventPublisher;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 抢单 Service
 * 抢单的逻辑处理
 */
@Service
@Slf4j
public class CrmOrderService {

    @Autowired
    private CrmOrderMapper crmOrderMapper;
    @Autowired
    private ApplicationEventPublisher publisher;


    @Transactional(rollbackFor = Exception.class)
    public void robbingV1(RobbingDto dto){
        log.info("============抢单信息:{}",dto);
        if (this.mobileHasRobbed(dto.getMobile())) {
            // 订单被抢
            log.info("============订单已被抢:{}============",dto.getMobile());

        }else {
            // 订单没有被抢
            log.info("============订单未被抢============");
            // 插入抢单记录
            CrmOrder crmOrder = new CrmOrder();
            BeanUtils.copyProperties(dto,crmOrder);
            crmOrderMapper.insertSelective(crmOrder);

            // TODO：异步发送抢单成功APP信息或短信
            CrmOrderRobbingEvent event = new CrmOrderRobbingEvent(this, dto.getMobile(), dto.getUserId());
            publisher.publishEvent(event);
        }
    }
    /**
     * 数据库级别 判断订单是否已存在
     */
    private Boolean mobileHasRobbed(final String mobile){
        int res=  crmOrderMapper.countByMobile(mobile);
        if (res>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 分布式锁——redis
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String onlyRedisKey="redis:only:key";
    private static String crmOrderRedisKey="redis:crm:order:key:%s";

    @Transactional(rollbackFor = Exception.class)
    public void robbingV2(RobbingDto dto){
        //TODO:基于redis原子操作-分布式锁
        String value= UUID.randomUUID().toString()+System.nanoTime();
        Boolean res=stringRedisTemplate.opsForValue().setIfAbsent(onlyRedisKey,value);
        if(res){
            try {
                log.info("============订单未被抢============");
                crmOrderRedisKey = String.format(crmOrderRedisKey, dto.getMobile());
                Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(crmOrderRedisKey, dto.getMobile());

                if (flag){
                    // 缓存中此key不存在，可以抢单
                    //stringRedisTemplate.expire(crmOrderRedisKey,1, TimeUnit.SECONDS);
                    // 插入抢单记录
                    CrmOrder crmOrder = new CrmOrder();
                    BeanUtils.copyProperties(dto,crmOrder);
                    crmOrderMapper.insertSelective(crmOrder);

                    // TODO：异步发送抢单成功APP信息或短信
                    CrmOrderRobbingEvent event = new CrmOrderRobbingEvent(this, dto.getMobile(), dto.getUserId());
                    publisher.publishEvent(event);
                }else{
                    log.info("============抢单失败============");
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //TODO：释放锁
                String redisValue=stringRedisTemplate.opsForValue().get(onlyRedisKey);
                if (!Strings.isNullOrEmpty(redisValue) && redisValue.equals(value)){
                    stringRedisTemplate.delete(onlyRedisKey);
                }
            }
        }else{
            log.info("============抢单失败============");
        }
    }
    /**
     * 分布式锁——redisson
     */
    @Autowired
    private RedissonDistributeLock distributeLock;

    public int robbingV3(RobbingDto dto) throws Exception {
        int res = 0;
        RLock rLock=distributeLock.acquireLock(String.valueOf(dto.getMobile()));
        try {
            if (rLock!=null){
                // 获取锁
                String key="redisson:crm:order:"+dto.getMobile();
                if (distributeLock.existKey(key)){
                    //TODO:已经存在-代表已被抢
                    log.info("============抢单已被抢============");
                }else{
                    //TODO:不存在
                    distributeLock.setKeyValue(key,dto.getMobile());
                    log.info("============抢单成功============");
                    CrmOrder entity=new CrmOrder();
                    BeanUtils.copyProperties(dto,entity);
                    res = crmOrderMapper.insertSelective(entity);
                    //
                    CrmOrderRobbingEvent event=new CrmOrderRobbingEvent(this,dto.getMobile(),dto.getUserId());
                    publisher.publishEvent(event);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            if (rLock!=null){
                distributeLock.realeaseLock(rLock);
            }
        }
        return res;
    }

    // redis判断此订单是否存在
    private Boolean mobileHasRobbedRedis(final String mobile) {
        crmOrderRedisKey = String.format(crmOrderRedisKey, mobile);
        if (stringRedisTemplate.hasKey(crmOrderRedisKey)) {
            return true;
        }else {
            return false;
        }
    }


}
