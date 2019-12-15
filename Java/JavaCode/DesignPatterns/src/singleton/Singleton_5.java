package singleton;

/**
 * 使用静态内部类实现
 * 也是通过懒加载，实现的！
 */
public class Singleton_5 {
    private Singleton_5() {
    }

    private static class Inner {

        private static Singleton_5 s = new Singleton_5();
    }

    // 在没有调用getInstance的时候，内部类不会加载
    private static Singleton_5 getInstance() {
        return Inner.s;
    }
}
