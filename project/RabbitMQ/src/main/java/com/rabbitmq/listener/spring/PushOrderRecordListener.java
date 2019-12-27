package com.rabbitmq.listener.spring;

import com.rabbitmq.entity.OrderRecord;
import com.rabbitmq.mapper.OrderRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 这就是监听器-跟RabbitMQ的Listener几乎是一个理念
 * 异步 插入数据库
 */
@Component
@Slf4j
public class PushOrderRecordListener implements ApplicationListener<PushOrderRecordEvent>{

    @Autowired
    private OrderRecordMapper orderRecordMapper;

    @Override
    public void onApplicationEvent(PushOrderRecordEvent event) {
        log.info("监听到的下单记录： {} ",event);

        try {
            if (event!=null){
                OrderRecord entity=new OrderRecord();
                // 选择性复制对象字段
                BeanUtils.copyProperties(event,entity);
                orderRecordMapper.insertSelective(entity);
            }
        }catch (Exception e){
            log.error("监听下单记录发生异常：{} ",event,e.fillInStackTrace());
        }
    }
}
