package text;

/**                                       匿名内部类
 * 1.实现接口的匿名内部类。
 * 2.实现抽象类的匿名内部类。
 * 
 * 需要完成某个接口方法，只是用一次，不需要创建此接口的实现类
 * 可以定义匿名内部类，来完成方法体。
 */

//1.实现接口的匿名内部类。
interface Product {
    public double getPrice();

    public String getName();
}

//2.实现抽象类的匿名内部类。
abstract class Device {

    public abstract double getPrice();

    public abstract String getName();

    public Device(){}
    public Device(String name) {
    }
}

public class demo_Anonymous {

    //定义一个方法，需要一个Product对象作为参数
    public void test(Product p) {
        System.out.println("买了一个" + p.getName() + "，花了" + p.getPrice());
    }
    public void test(Device d) {
        System.out.println("买了一个" + d.getName() + "，花了" + d.getPrice());
    }

    public static void main(String[] args) {
    // 1.接口
        demo_Anonymous da1 = new demo_Anonymous();
        //此时，如果要用test()方法，需要一个Product对象，
        //如果此接口实现类仅使用一次
        //在不需要创建Product子类的情况下，可以使用匿名内部类
        //在内部类中实现方法体！！！
        da1.test(new Product() {
            public double getPrice() {
                return 6666;
            }
            public String getName() {
                return "GDX";
            }
        });
    //2.抽象类
        demo_Anonymous da2 = new demo_Anonymous();
        

        da2.test(new Device("示波器") {
            public double getPrice() {
                return 666;
            }
            public String getName() {
                return "示波器";
            }
        });

        Device d = new Device() 
        {
            //初始化块
            {
                System.out.println("------初始化匿名内部类-------");
            }
            //实现抽象方法
            public double getPrice() {
                return 777;
            }
            public String getName() {
                return "微波炉";
            }
        };
        da2.test(d);
        
    }
}