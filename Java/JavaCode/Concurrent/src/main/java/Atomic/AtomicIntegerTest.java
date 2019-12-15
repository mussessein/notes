package Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    public static void main(String[] args) {

        AtomicInteger i = new AtomicInteger(0);
        // ���ص���0��get��ֵ�����ǽ��
        int original =i.getAndAdd(3);
        System.out.println("original(ԭֵ)��"+original+"=====i(���):"+i.get());
        original = i.getAndIncrement();
        System.out.println("original(ԭֵ)��"+original+"=====i(���):"+i);
        original = i.getAndSet(1);
        System.out.println("original(ԭֵ)��"+original+"=====i(���):"+i);
    }
}
