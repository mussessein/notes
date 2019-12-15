package notes;

/*
求运行结果，例子
t1 wait之后，进入池子
t2 notify之后，t1 就绪状态（非 运行状态）
t2运行完成，t1开始运行
 */
public class TestDemo {
    public static void main(String[] args) throws Exception {
        final Object obj = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();// 阻塞，释放锁
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
