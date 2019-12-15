package singleton;

/**
 * 此方法不可用！
 */
public class Singleton_3 {
    private static Singleton_3 instance;

    private Singleton_3() {

    }

    public static Singleton_3 getInstance() {

        if (null == instance)
            /*
            虽然加了锁，但是等到第一个线程执行完instance=new Singleton()跳出这个锁时，
            另一个线程已经进入if语句，同样会实例化另外一个Singleton对象，线程不安全的原理跟3类似。
             */
            synchronized (Singleton_3.class) {
                instance = new Singleton_3();
            }
        return Singleton_3.instance;
    }
}
