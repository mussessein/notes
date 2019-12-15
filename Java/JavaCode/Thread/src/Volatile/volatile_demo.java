package Volatile;

/*
��һ����������Ϊvolatile֮��
�˱��������е��߳������ɼ���
���ǣ�
    volatile�����ܱ�֤�̵߳İ�ȫ��
 */
public class volatile_demo {
    public static volatile int race = 0;
    private static final int THREAD_COUNT = 20;

    public static void increase() {

        race++;
    }

    public static void main(String[] args) {

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        // ͣ�������߳�
        while (Thread.activeCount() > 1)
            Thread.yield();
        System.out.println(race);
    }
}
