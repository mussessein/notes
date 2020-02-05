package com.secondkill.service;

import com.secondkill.dto.KillSuccessUserInfo;
import com.secondkill.dto.MailDto;
import com.secondkill.entity.ItemKillSuccess;
import com.secondkill.mapper.ItemKillSuccessMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * 消息接收者模型：
 * 从RabbitMQ中拿出消息：
 * 1. 对接 邮件队列，从中拿出要发送邮件的用户信息
 * 2. 将秒杀信息，发送到死信队列，等待支付，超时未支付，自动失效掉消息
 */
@Service
@Slf4j
public class RabbitReceiverService {

    @Autowired
    private Environment env;
    @Autowired
    private MailService mailService;
    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;
    /**
     * 秒杀成功
     * 异步发送邮件
     * @param info
     */
    @RabbitListener(queues = "${mq.kill.item.success.email.queue}",containerFactory = "singleListenerContainer")
    public void consumerEmailMsg(KillSuccessUserInfo info) {
        try {
            log.info("秒杀成功==>开始发送邮件{}", info);
            final String content = String.format(env.getProperty("mail.kill.item.success.content"), info.getItemName(), info.getCode());
            MailDto mailDt = new MailDto(env.getProperty("mail.kill.item.success.subject"), content, new String[]{info.getEmail()});
            // 发送邮件
            mailService.sendHTMLMail(mailDt);
        } catch (Exception e) {
            log.error("秒杀成功==>发送邮件失败",e.fillInStackTrace());
        }
    }
    /**
     * 用户秒杀成功后超时未支付-监听者
     * @param info
     */
    @RabbitListener(queues = {"${mq.kill.item.success.kill.dead.real.queue}"},containerFactory = "singleListenerContainer")
    public void consumeExpireOrder(KillSuccessUserInfo info){
        try {
            log.info("用户秒杀成功后超时未支付-监听者-接收消息:{}",info);

            if (info!=null){
                ItemKillSuccess entity=itemKillSuccessMapper.selectByPrimaryKey(info.getCode());
                if (entity!=null && entity.getStatus().intValue()==0){
                    // 更新此订单状态为-1
                    itemKillSuccessMapper.expireOrder(info.getCode());
                }
            }
        }catch (Exception e){
            log.error("用户秒杀成功后超时未支付-监听者-发生异常：",e.fillInStackTrace());
        }
    }
}
