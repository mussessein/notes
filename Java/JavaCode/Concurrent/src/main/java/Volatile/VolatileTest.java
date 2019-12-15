package Volatile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * volatile不能保证原子性测试
 */
public class VolatileTest {
    int count = 0;

    public void add() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
        List<Thread> list = new ArrayList<>();
        // 添加10个线程
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(test::add, "Thread" + i));
        }
        // 执行
        list.forEach((t) -> t.start());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test.count);
    }

}
