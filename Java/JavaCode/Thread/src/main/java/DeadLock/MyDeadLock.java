package DeadLock;

public class MyDeadLock {
    private static String A = "lock_1";
    private static String B = "lock_2";

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (A) {
                System.out.println(Thread.currentThread().getName() + "拿到了A锁");
                try {
                    Thread.sleep(2000); // 确保B锁被拿到
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    synchronized (B) {
                        System.out.println(Thread.currentThread().getName() + "拿到了B锁");
                    }
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (B) {
                System.out.println(Thread.currentThread().getName() + "拿到了B锁");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    synchronized (A) {
                        System.out.println(Thread.currentThread().getName() + "拿到了A锁");
                    }
                }
            }
        }).start();
    }
}
