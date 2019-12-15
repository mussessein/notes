package singleton;

/**
 * double check方式(局部添加同步方法)
 * 提高了性能的同步单例模式
 * 但是 有一个隐患:
 * 可能引发空指针异常---->使用volatile可以解决
 * 为什么会空指针？
 * 因为不使用volatile，有可能发生指令重排序
 * 正常的顺序：
 * 1.为instance分配内存空间
 * 2.初始化instance
 * 3.将instance指向分配的内存地址
 * 重排序后：可能变成1->3->2
 * 还没初始化，就执行了第3步，就会发生空指针异常
 * 所以使用了volatile关键字之后，就可以禁止指令重排序
 * 但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。
 * 指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。
 * 例如，线程 T1 执行了 1 和 3，此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，
 * 因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化。
 */
public class Singleton_4 {
    private volatile static Singleton_4 instance;

    private Singleton_4() {

    }

    // double check方式
    public static Singleton_4 getInstance() {
        /**
         *  这一步判断，主要考虑的是性能问题，
         *  创建过对象之后，以后的每次再调用此方法，
         *  不需要经过synchronized判断，直接返回instance
         */
        if (null == instance)
        /**
         * synchronized只会进行一次加锁
         * 就是在instance对象还没有创建的时候,null==instance
         * 然后,加锁创建instance对象,然后释放锁
         * 下一个线程进行判断,instance!=null
         * 不会再进行synchronized加锁,直接return Singleton_4.instance;
         */
            synchronized (Singleton_4.class) {
                if (null == instance)     // 第二次检查使创建两个不同的 Singleton 对象成为不可能。
                    instance = new Singleton_4();
            }

        return Singleton_4.instance;
    }
}
