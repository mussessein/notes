package text;


/**内部类：非静态内部类，静态内部类，局部内部类，匿名内部类
 * 静态内部类:1，在静态内部类中，可以访问外部类的静态成员变量，不可以访问实例变量（非静态）
 *           2，在外部类中，可以访问静态内部类的所有成员变量，但是访问方式不同！！！！
 *           3，不能直接创建内部类对象，内部类必须寄生于外部类
 */
class OuterClass {
    private String oc1 = "外部类实例变量";
    private static String oc2 = "外部类静态成员变量";

    //非静态内部类：依赖外部类的实例
    public class InnerClass_1{

    }
    // 静态内部类，访问外部类成员：不依赖外部类实例，依赖外部类
    static class InnerClass_2 {
        public String ic1 = "静态内部类实例变量";
        private static String ic2 = "静态内部类静态成员变量";

        // 在静态内部类，通过方法来访问外部类的成员
        public void accessOuterClass() {
            // System.out.println(oc1);
            // 上行报错，下行可以通过，
            System.out.println(oc2);
        }
        public InnerClass_2(){}
    }

    // 外部类访问静态内部类成员
    public void accessInnerClass() {
        // 访问内部类的静态成员变量，以类来访问
        System.out.println(InnerClass_2.ic2);
        // 访问内部类的非静态成员变量，以实例来访问
        System.out.println(new InnerClass_2().ic1);

        new InnerClass_2().accessOuterClass();
        System.out.println(oc1);
    }
}

public class demo_Inner {

    public static void main(String[] args) {
        OuterClass o = new OuterClass();
        o.accessInnerClass();
        // 不能直接创建内部类对象，静态内部类必须寄生于外部类
        // new InnerClass_2().accessOuterClass();错误
        OuterClass.InnerClass_2 in = new OuterClass.InnerClass_2();
        in.accessOuterClass();
    }
}