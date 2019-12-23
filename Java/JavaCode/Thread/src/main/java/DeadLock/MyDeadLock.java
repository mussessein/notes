package DeadLock;

public class MyDeadLock {
    private static String A = "lock_1";
    private static String B = "lock_2";

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (A) {
                System.out.println(Thread.currentThread().getName() + "�õ���A��");
                try {
                    Thread.sleep(2000); // ȷ��B�����õ�
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    synchronized (B) {
                        System.out.println(Thread.currentThread().getName() + "�õ���B��");
                    }
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (B) {
                System.out.println(Thread.currentThread().getName() + "�õ���B��");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    synchronized (A) {
                        System.out.println(Thread.currentThread().getName() + "�õ���A��");
                    }
                }
            }
        }).start();
    }
}
