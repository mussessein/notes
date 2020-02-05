package Synchronizer.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author whr
 * @date 2020/1/15 11:34
 * @Description
 * cyclicBarrier����դ��
 * ʹ��await���̵߳ȴ��������߳�await��ϣ�ͬʱ��ʼ���¼���ִ��
 */
public class CyclicBarrierExample {
    private static class Worker extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Worker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            super.run();
            try {
                System.out.println(Thread.currentThread().getName() + "��ʼ�ȴ������߳�");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "��ʼִ��");
                // �����߳̿�ʼ����������Thread.sleep()��ģ��ҵ����
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "ִ�����");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadCount = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);

        for (int i = 0; i < threadCount; i++) {
            System.out.println("���������߳�" + i);
            Worker worker = new Worker(cyclicBarrier);
            worker.start();
        }
    }
}
