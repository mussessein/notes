package MyThread;

public class Thread_Data_Shared extends Thread {

    private volatile int count = 50;

    public Thread_Data_Shared() {
    }

    public Thread_Data_Shared(String name) {

        super();
        this.setName(name);
    }

    /*
    这里加入synchronized 关键字,可以实现线程排队执行run方法
    单个线程执行完毕,才会释放锁
    加锁的代码成为 互斥区
     */
    @Override
    public void run() {
        super.run();
        while (count > 0) {
            count--;
            System.out.println(this.currentThread().getName() + " 计算count: " + count);
        }
    }

    public static void main(String[] args) {

        // 各个线程有自己的count值,每个线程运行自己的值

        System.out.println("==========数据不共享==========");
        Thread_Data_Shared a = new Thread_Data_Shared("A");
        Thread_Data_Shared b = new Thread_Data_Shared("B");
        Thread_Data_Shared c = new Thread_Data_Shared("C");
        a.start();
        b.start();
        c.start();

        // 三个线程共享一个count值

/*        System.out.println("==========数据共享==========");
        Thread_Data_Shared threadShared =new Thread_Data_Shared();
        Thread a1=new Thread(threadShared,"A1");
        Thread b1=new Thread(threadShared,"B1");
        Thread c1=new Thread(threadShared,"C1");
        a1.start();
        b1.start();
        c1.start();*/

    }

}

