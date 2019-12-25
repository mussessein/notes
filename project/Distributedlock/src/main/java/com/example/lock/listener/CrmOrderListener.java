package com.example.lock.listener;

import com.example.lock.entity.User;
import com.example.lock.listener.event.CrmOrderRobbingEvent;
import com.example.lock.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CrmOrderListener implements ApplicationListener<CrmOrderRobbingEvent> {

    @Autowired
    private UserMapper userMapper;
    @Async
    public void onApplicationEvent(CrmOrderRobbingEvent event) {
        log.info("===========Crm系统抢单成功监听===========");

        if (event!=null && event.getUserId()!=null){
            String msg = "%s,您已抢购到手机号：%s的顾客订单！";
            User user = userMapper.selectByPrimaryKey(event.getUserId());
            if (user != null) {
                msg = String.format(msg,user.getUserName(),event.getMobile());

                // TODO：这里可以实现发送短信，或者推送APP消息的逻辑
                log.info("抢单成功：{}",msg);
            }
        }
    }
}
