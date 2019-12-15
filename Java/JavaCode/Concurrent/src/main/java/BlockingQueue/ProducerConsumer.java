package BlockingQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/*
BlockingQueue������������ӽ�����з���
put�������ܳ��쳣����һֱ�ȴ�
add���������ˣ��׳��쳣
offer��������ֶ��������޷���ӵĻ�����ֱ�ӷ���false��
take:����
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
                // ����0~9999����������������Ʒ
                String s = "GDX--" + r.nextInt(100);
                queues.put(s);
                System.out.println("����--" + s);
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
                System.out.println("����--" + s);
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
