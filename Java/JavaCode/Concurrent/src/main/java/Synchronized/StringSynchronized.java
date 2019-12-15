package Synchronized;

/**
 * 不要以字符串对象，作为锁
 */
public class StringSynchronized {
    // 引用不同，但是这是同一个对象
    String s1 = "Hello";
    String s2 = "Hello";

}
