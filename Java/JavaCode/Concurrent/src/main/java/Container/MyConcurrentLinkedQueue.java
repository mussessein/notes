package Container;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * ͬ�����У�
 * ���߳���Ʊ
 */
public class MyConcurrentLinkedQueue {
    private static Queue<String> tickets = new ConcurrentLinkedDeque();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("Ʊ--" + i);
        }
    }

    public static void main(String[] args) {
        // 10���߳���Ʊ
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();
                    if (s == null) break;
                    else System.out.println("��" + s);
                }
            }).start();
        }
    }
}
