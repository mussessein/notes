package Container;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 同步队列：
 * 多线程卖票
 */
public class MyConcurrentLinkedQueue {
    private static Queue<String> tickets = new ConcurrentLinkedDeque();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票--" + i);
        }
    }

    public static void main(String[] args) {
        // 10个线程卖票
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();
                    if (s == null) break;
                    else System.out.println("售" + s);
                }
            }).start();
        }
    }
}
