package com.userauth.sheduler;/**
 * Created by Administrator on 2019/9/9.
 */

import com.userauth.mapper.AuthTokenMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 通用的定时任务调度
 *
 **/
@Component
@EnableAsync
@Slf4j
public class CommonScheduler {
    @Autowired
    private AuthTokenMapper authTokenMapper;
    /**
     * 剔除掉那些已经失效的token  cron=建议一个月一次
     * @Scheduled(cron = "0 0 0 28 * ?")：每个月的 28 日 00:00:00 运行
     * @Async("taskExecutor")：使用此线程池来完成定时任务
     */
    @Scheduled(cron = "0 0 0 28 * ?")
    @Async("taskExecutor")
    public void deleteInvalidateToken(){
        try {
            log.info("=======定时任务调度开启：剔除已经失效的token=======");
            authTokenMapper.deleteUnactiveToken();
            //交给运维-自动抽取出那些失效的token，进行转移 (etl)
        }catch (Exception e){
            log.error("=======剔除失效token发生异常：",e.fillInStackTrace());
        }
    }
}















