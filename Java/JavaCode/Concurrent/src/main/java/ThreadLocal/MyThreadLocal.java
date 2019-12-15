package ThreadLocal;

/**
 * �������̷߳��,JDBC�����ӳص�Connection��������̷߳��;
 * threadlocal����һ���߳��ڲ��Ĵ洢��,
 * ֻ��ָ�����ݵ��߳̿��Եõ��洢����
 * B�߳�������ThreadLocal������A�߳��޷�����
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
