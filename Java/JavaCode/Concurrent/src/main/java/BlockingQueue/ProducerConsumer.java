package BlockingQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/*
BlockingQueue类下有三种添加进入队列方法
put：不会跑出异常，会一直等待
add：队列满了，抛出异常
offer：如果发现队列已满无法添加的话，会直接返回false。
take:阻塞
 */
public class ProducerConsumer {
    private static BlockingQueue<String> queues = null;
    private static Random r = new Random();

    public ProducerConsumer(int n) {
        queues = new LinkedBlockingDeque<>(n);
    }

    public void produce() {
        try {
            while (true) {
                // 产生0~9999随机整数随机生产商品
                String s = "GDX--" + r.nextInt(100);
                queues.put(s);
                System.out.println("生产--" + s);
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        try {
            while (true) {
                String s = queues.take();
                System.out.println("消费--" + s);
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer(2);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                pc.produce();
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                pc.consume();
            }).start();
        }
    }
}
