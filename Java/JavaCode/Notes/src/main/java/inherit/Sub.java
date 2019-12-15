package inherit;

/**
 * 父类构造器尽量不要加入即将被子类重写的方法~~~！！！！！！！
 *                                                                 有什么意义呢？？？？？？
 */
class Base {
    public void test() {
        System.out.println("将被子类重写的方法");
    }

    public Base() {
       test();
    }

}

public class Sub extends Base {
    private String name;

    public void test() {
        System.out.println("已经重写的方法" + "name长度为：" + name);
    }

    public Sub() {
        System.out.println("Sub构造");
        
    }

    public static void main(String[] args) {
        // 下行代码，空指针异常，无法输出
        Sub s1 = new Sub();
        // 下行代码可以输出
        s1.test();
    }
}
