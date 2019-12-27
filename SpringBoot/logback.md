# Log

SpringBoot选用SLF4J和Logback日志框架

## application.yml

当存在logback-spring.xml，application.yml的日志配置无效，默认优先logback-spring.xml；

```yaml
spring:
  profiles:
    # 激活开发环境——在logback配置文件的<springProfile name="dev">中，会选择对应的日志输出格式
    active: dev
logging:
  # 每个应用下的日志级别
  level:
    com.service: info
    com.controller: debug
  # 日志保存在本地目录(文件夹目录)
  file:
    path: ./logs/
  # 日志输出在console，file中的格式
  pattern:
    console: "%d{yyyy-MM} [%thread] %-5level %logger{50} - %msg%n"
    file: "%d{yyyy-MM-dd} === [%thread] === %-5level === %logger{50} ==== %msg%n"
```

# 指定配置

将配置文件放在classpath下，SpringBoot将自动优先使用配置文件，而非application.yml

配置文件名：logback.xml，logback-spring.xml，SpringBoot自动识别

## configuration

整个日志配置文件，是在configuration标签下进行配置

此标签，有几个属性：

```xml
<configuration scan="false" scanPeriod="60 seconds" debug="false"></configuration> 
```

- scan：当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true；

- scanPeriod：每隔多久自动扫描一次此配置文件有没有修改

  scan为true，scanPeriod生效；

- debug：当此属性设置为true时，将打印出logback内部日志信息，基本不需要；

## property

在configuration标签之后，配置一些全局需要的属性：(可以认为是变量)

日志输出目录：一般是总的日志根目录

日志输出文件名：一般不在这里定义，在单独的appender中定义

```xml
<configuration scan="false" scanPeriod="60 seconds" debug="false">
    <!-- 定义日志文件 输入位置 -->
    <property name="log_dir" value="./logs" />
    <!-- 日志最大的历史 30天 -->
    <property name="maxHistory" value="30" />
    <property name="maxFileSize" value="10MB" />
    .....
</configuration>
```

## appender

真正配置日志的格式，输出文件，删除策略，过滤.....的地方就在appender下

```xml
<appender name="ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!-- 过滤器，只记录WARN级别的日志 -->
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
        <level>ERROR</level>
        <onMatch>ACCEPT</onMatch>
        <onMismatch>DENY</onMismatch>
    </filter>
    <!--日志滚动策略-->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!--日志输出文件 可相对和绝对路径 -->
        <fileNamePattern>
            ${log_dir}/app_error.%d{yyyy-MM-dd}.%i.log
        </fileNamePattern>
        <!--超出时间删除旧文件-->
        <maxHistory>${maxHistory}</maxHistory>
        <!--超出时间删除旧文件-->
        <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
            <maxFileSize>${maxFileSize}</maxFileSize>
        </timeBasedFileNamingAndTriggeringPolicy>
    </rollingPolicy>
    <!--每条日志的格式-->
    <encoder>
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n</pattern>
    </encoder>
</appender>
```

- ${log_dir}：这里拿到的就是前面property标签的配置

### SocketAppender

日志发送到指定socket

```xml
<appender name="LOGSTASH" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
    <destination>localhost:4560</destination>
    <encoder charset="UTF-8" class="net.logstash.logback.encoder.LogstashEncoder"/>
</appender>
```



## logger

用来设置某一个包下的日志，或者某一个类的日志，的level

```xml
<!--设置每个目录的日志level-->
<logger name="java.sql.PreparedStatement" value="DEBUG" additivity="false" />
<logger name="java.sql.Connection" value="DEBUG" />
<logger name="java.sql.Statement" value="DEBUG" />
<logger name="com.ibatis" value="DEBUG" />
<logger name="com.ibatis.common.jdbc.SimpleDataSource" value="DEBUG" />
<logger name="com.ibatis.common.jdbc.ScriptRunner" level="DEBUG" />
<logger name="com.apache.ibatis" level="TRACE" />
```

- additivity：是否向上级loger传递打印信息，默认是true；

## 生成指定类日志

将指定路径下（自己的工程）所有类的日志，以appender的配置方式来记录；

比如：我们添加了下面的appender，就会按照之前配置的appender的方式，分类来生成日志文件到指定目录；

```xml
<logger name = "com.xxxxx">
    <appender-ref ref="ERROR" />
    <appender-ref ref="INFO" />
    <appender-ref ref="WARN" />
    <appender-ref ref="DEBUG" />
    <appender-ref ref="TRACE" />
</logger>
```

## root

是logger的根logger

一般将所有配置的appender包含进来，这样，上面配置的logger才能使用这些appender

```xml
<root level="debug">
    <!-- 文件输出 -->
    <appender-ref ref="ERROR" />
    <appender-ref ref="INFO" />
    <appender-ref ref="WARN" />
    <appender-ref ref="DEBUG" />
    <appender-ref ref="TRACE" />
</root>
```

## 自动发现开发环境

在application.yml中配置开发环境，来让logback自动发现开发环境而选择对应的log配置

```yaml
spring:
  profiles:
    # 激活开发环境——在logback配置文件的<springProfile name="dev">中，会选择对应的日志输出格式
    active: dev
```

以logback的一个appender为例：

通过springProfile标签，发现开发环境

```xml
<appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
    <!--encoder和layout没有本质区别，推荐encoder-->
    <layout class="ch.qos.logback.classic.PatternLayout">
        <springProfile name="dev">
            <pattern>%d{yyyy-MM-dd HH}</pattern>
        </springProfile>
        <springProfile name="!dev">
            <pattern>%d{yyyy-MM-dd} ==== [%thread]</pattern>
        </springProfile>
    </layout>
</appender>
```

