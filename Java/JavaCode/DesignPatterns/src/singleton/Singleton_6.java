package singleton;

/**
 * 枚举模式
 * 依然是懒汉模式,在没有调用getInstance之前,内部枚举类不会加载
 */
public class Singleton_6 {
    private Singleton_6() {
    }

    public static Singleton_6 getInstance() {
        System.out.println("开始拿实例");
        return Singleton.INSTANCE.getInstance();
    }

    // 私有枚举类
    private enum Singleton {
        INSTANCE;

        // 创建单例引用
        private Singleton_6 singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new Singleton_6();
            System.out.println("枚举构造函数");
        }

        public Singleton_6 getInstance() {
            System.out.println("枚举返回单例");
            return singleton;
        }
    }

    public static void main(String[] args) {
        Singleton_6 demo = new Singleton_6();
        System.out.println("==============");
        Singleton_6 instance = getInstance();

    }
}
