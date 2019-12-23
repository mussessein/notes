package notes;

/*
�����н��������
t1 wait֮�󣬽������
t2 notify֮��t1 ����״̬���� ����״̬��
t2������ɣ�t1��ʼ����
 */
public class TestDemo {
    public static void main(String[] args) throws Exception {
        final Object obj = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();// �������ͷ���
                    System.out.println("Thread 1 wake up.");
                } catch (InterruptedException e) {
                }
            }
        });
        t1.start();
        Thread.sleep(1000);//We assume thread 1 must start up within 1 sec.
        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                obj.notifyAll();
                System.out.println("Thread 2 sent notify.");
            }
        });
        t2.start();
    }
}
