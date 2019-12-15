package MyThread;

/*
Daemon Thread 守护线程（后台线程）
    1. 在后台运行，是为其他的线程提供服务
    2. 特征：如果所有的线程都死亡了，后台线程自动死亡


 */
public class DaemonThread extends Thread {

    DaemonThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(getName() + i);
        }
    }

    public static void main(String[] args) {

        DaemonThread dt = new DaemonThread("精灵线程");
        //模拟后台线程，将dt设置为守护线程
        dt.setDaemon(true);
        dt.start();
        //在主线程结束后，守护线程自动死亡
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }

    }
}
