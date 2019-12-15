package singleton;


public class Singleton_1 {

    /**
     * 在多线程下也是安全的立即加载的饿汉单例
     * 即：使用类的时候已经将对象创建完毕
     * 缺点：浪费资源。不用的时候也加载了对象
     * 并且，在某些场景是无法使用这种方法的，
     */
    private static final Singleton_1 instance = new Singleton_1();

    private Singleton_1() {

    }

    public static Singleton_1 getInstance() {
        return instance;
    }
}
