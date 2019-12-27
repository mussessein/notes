package com.rabbitmq.service.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * 为所有抢单线程设定统一起跑线；
 */
@Service
@Slf4j
public class InitService {

    public static final int ThreadNum =1;

    private static int mobile=0;

    @Autowired
    private ConcurrencyService concurrencyService;

    @Autowired
    private CommonMqService commonMqService;

    public void generateMultiThread(){
        log.info("开始初始化线程数----> ");
        try {
            CountDownLatch countDownLatch=new CountDownLatch(1);
            for (int i=0;i<ThreadNum;i++){
                new Thread(new RunThread(countDownLatch)).start();
            }
            //TODO：同时启动多个线程，线程起跑线
            countDownLatch.countDown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 线程类
     */
    private class RunThread implements Runnable{
        private final CountDownLatch startLatch;

        public RunThread(CountDownLatch startLatch) {
            this.startLatch = startLatch;
        }
        @Override
        public void run() {
            try {
                //TODO：线程等待
                startLatch.await();
                mobile += 1;
                //concurrencyService.manageRobbing(String.valueOf(mobile));//--v1.0
                commonMqService.sendRobbingMsg(String.valueOf(mobile));//+v2.0
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
