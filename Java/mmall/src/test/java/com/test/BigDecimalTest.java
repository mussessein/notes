package com.test;

import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalTest {
    /**
     * 丢失精度测试:
     */
    @Test
    public void test1(){
        System.out.println(0.05+0.1); //0.15000000000000002
        System.out.println(1.0-0.42); //0.5800000000000001
        System.out.println(4.015*100);//401.49999999999994
        System.out.println(123.3/100);//1.2329999999999999
    }

    @Test
    public void test2(){
        // 禁止使用
        // BigDecimal b1 = new BigDecimal(0.05);
        BigDecimal b1 =BigDecimal.valueOf(0.05);
        BigDecimal b2 =BigDecimal.valueOf(0.01);
        BigDecimal b3 = new BigDecimal("0.05");
        BigDecimal b4 = new BigDecimal("0.01");
        System.out.println(b3.add(b4)); //
    }
}
