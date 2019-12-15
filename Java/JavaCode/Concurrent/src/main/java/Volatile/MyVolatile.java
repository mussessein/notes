package Volatile;

import java.util.concurrent.TimeUnit;

/**
 * 测试volatile关键字的内存可见
 * 线程A内复制了flag，进入while死循环；
 * 不适用volatile，即使修改heap中的flag，线程A也无法更新flag的值
 * <p>
 * volatile 使得修饰的变量在发生变化的时候，通知其他线程进行更新，即可见性；
 */
public class MyVolatile {

    /*volatile*/ boolean flag = true;

    void test() {
        System.out.println("start");
        while (flag) {

        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        MyVolatile T = new MyVolatile();
        // 线程A执行
        new Thread(T::test, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 试图打断上一个线程
        T.flag = false;
    }
}
