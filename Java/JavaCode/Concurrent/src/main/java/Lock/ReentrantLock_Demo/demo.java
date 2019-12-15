package Lock.ReentrantLock_Demo;

/*
此代码发生阻塞现象！！！！！！！！！
阻塞而非死锁
阻塞：一个线程执行结束，等待其他线程执行，
死锁：一个线程执行结束，等待其他线程释放同步监视器，

取完钱，释放锁，然后又是取钱线程拿到锁，进入了等待被唤醒，存钱线程又拿不到锁，无法唤醒
 */
public class demo {

    public static void main(String[] args){
        //创建一个账户
        Account account=new Account("8888888",0);
        //创建存取线程
        new DepositThread("张三",account,1000).start();
        new DrawThread("张四",account,1000).start();
    }
}
