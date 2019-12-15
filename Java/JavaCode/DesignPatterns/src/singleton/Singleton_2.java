package singleton;

/**
 * 懒加载：即 用的时候才加载，不会浪费内存
 * 实现了懒加载,即：懒汉模式
 * 在使用类的时候，不进行对象的创建，
 * 只有调用了getInstance方法之后，对象才被创建
 * 此方法在多线程下,无法做到单例模式
 *
 * 添加volatile和synchronized可以做到线程安全
 *
 */
public class Singleton_2 {

    private static volatile Singleton_2 instance; // 第一次类加载，instance指向null

    private Singleton_2(){

    }

    public static synchronized Singleton_2 getInstance(){

        if (null == instance)
            instance=new Singleton_2();

        return Singleton_2.instance;
    }

}
