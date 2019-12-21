package com.example.lock;

import com.example.lock.entity.ProductLock;
import com.example.lock.mapper.ProductLockMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class LockApplicationTests {

    @Autowired
    private ProductLockMapper productLockMapper;
    @Test
    void contextLoads() {
        ProductLock productLock = productLockMapper.selectByPrimaryKey(1);
        log.info("test:{}",productLock);
    }

}

