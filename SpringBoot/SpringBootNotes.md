# 监听器

ApplicationListener

ApplicationEvent



# BeanUtils.copyProperties

```
BeanUtils.copyProperties("要转换的类", "转换后的类"); 
```

将两个对象的相同字段，进行复制，不同字段，不做操作，取初始值；

1. Spring的BeanUtils的CopyProperties方法需要对应的属性有getter和setter方法；
2. spring和apache的copy属性的方法源和目的参数的位置正好相反，所以导包和调用的时候都要注意一下；

# 注解

## 异步调用注解

异步调用标有@Aysn的方法，当前线程继续往下走，再起一个线程，完成@Aysn的方法；

即：不阻塞当前线程；

比如：邮件服务；另起线程完成邮件的发送，主业务逻辑，不需要等待邮件发送完毕；

比如：定时删除一些失效的Token

### @EnableAysn

### @Async

可以传入一个Bean，让此任务加入到线程池中进行；

### @Scheduled

定时任务

```java
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
        }catch (Exception e){
            log.error("=======剔除失效token发生异常：",e.fillInStackTrace());
        }
    }
}
```

```java
/**
 * 线程池的方式，调用定时任务
 **/
@Configuration
public class SchedulerConfig {
    @Bean("taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(4);
        //最大核心线程数
        executor.setMaxPoolSize(10);
        //设置队列中等待被调度处理的任务的数量
        executor.setQueueCapacity(8);
        executor.initialize();
        return executor;
    }
}
```

## @RequestHeader

 可以把Request请求header部分的值绑定到方法的参数上 ;

可以拿到Header中的Token信息；



## @NotNull、@NotEmpty、@NotBlank

 @NotNull：不能为null，但可以为empty 

@NotEmpty ：不能为null，而且长度必须大于0 

@NotBlank： 只能作用在String上，不能为null，而且调用trim()后，长度必须大于0 



## @RequestBody @Validated 

以上两个注解一般配合使用，并且搭配@NotBlank的JavaBean

比如：修改密码的业务逻辑

```java
@Data
public class UpdatePsdDto implements Serializable{
    @NotBlank(message = "旧密码不能为空！")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空！")
    private String newPassword;
}
```

UpdatePsdDto此对象，一般是与前端约定好，用来单独存储密码的对象；

@NotBlank限制其不能为空

**在Controller中 @RequestBody @Validated限制此对象，就必须在后面跟上BindingResult参数**

在进行实际的修改密码业务之前，校验BindingResult参数，校验通过，才能继续进行业务处理

```java
@RequestMapping(value = "token/password/update",method = RequestMethod.POST)
public BaseResponse updatePassword(@RequestHeader String accessToken, 
                                   @RequestBody @Validated UpdatePsdDto dto, 
                                   BindingResult bindingResult){
    log.info("=====修改密码======");
    if (StringUtils.isBlank(accessToken)){
        return new BaseResponse(StatusCode.InvalidParams);
    }
    // 校验BindingResult
    String res= ValidatorUtil.checkResult(bindingResult);
    if (StringUtils.isBlank(res)){
        return new BaseResponse(StatusCode.InvalidParams.getCode(),res);
    }
    // 校验通过，才能继续业务
    BaseResponse response=new BaseResponse(StatusCode.Success);
    try {
        dataBaseService.updatePassword(accessToken,dto);
    }catch (Exception e){
        response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
    }
    return response;
}
```

```

```

## @Transactional

 声明式事务（@Transactional) 

- @Transactional加在方法上 ，表示对该方法应用事务 ； 当加在类上，表示对该类里面所有的方法都应用相同配置的事务 

- 不宜使用在处理时间过长的事务，此注解的方法会持续持有数据库线程池的连接，不能及时返回连接；

- **方法必须是public，才有效；**

  这是因为@Transactional底层使用SpringAOP进行Cglib代理，必须是public的方法，才能进行拦截，进事务管理；

- **自调用**：此注解只能由外部的方法调用，同一类下，没有@Transactional注解的方法调用了同类下的@Transactional注解方法，事务就会失效，不会回滚

- **解决自调用问题**：使用AspectJ代替SpringAOP代理；

- **请使用：`@Transactional(rollbackFor = Exception.class)`**

  默认`@Transactional`只会在不受控的异常的情况下进行回滚（Error，RuntimeException）

  在受控异常（能抛出的异常）下，不会进行回滚；

  但是加上`(rollbackFor = Exception.class)`之后，在受控异常下也会回滚；

```
(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
```

## @JsonFormat

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

在entity层的时间字段添加此注解，返回固定的格式和timezone

```java
@Data
public class ItemKill {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
}
```

