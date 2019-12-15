package Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    public static void main(String[] args) {

        AtomicInteger i = new AtomicInteger(0);
        // 返回的是0，get的值，而非结果
        int original =i.getAndAdd(3);
        System.out.println("original(原值)："+original+"=====i(结果):"+i.get());
        original = i.getAndIncrement();
        System.out.println("original(原值)："+original+"=====i(结果):"+i);
        original = i.getAndSet(1);
        System.out.println("original(原值)："+original+"=====i(结果):"+i);
    }
}
