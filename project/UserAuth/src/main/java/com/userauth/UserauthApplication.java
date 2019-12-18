package com.userauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.userauth.mapper")
@EnableScheduling
//@EnableTransactionManagement
public class UserauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserauthApplication.class, args);
    }

}
