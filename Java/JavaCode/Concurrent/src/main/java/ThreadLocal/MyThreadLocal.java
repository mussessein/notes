package ThreadLocal;

/**
 * 常用于线程封闭,JDBC的连接池的Connection对象就是线程封闭;
 * threadlocal而是一个线程内部的存储类,
 * 只有指定数据的线程可以得到存储数据
 * B线程设置了ThreadLocal遍历，A线程无法访问
 */
public class MyThreadLocal {
    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }
    }

    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "---" + tl.get());
        }, "A").start();
        new Thread(() -> {
            tl.set(new Person("zhangsan"));
            System.out.println(Thread.currentThread().getName() + "---" + tl.get().name);
        }, "B").start();
    }
}
