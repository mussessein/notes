package text;

/**
 * 多态：同一个类型的多个实例，在执行同一个方法的时候，
 * 呈现出多种的行为特征，就叫多态。
 * 
 * 为什么会有多态？
 * java在执行方法时，
 * 方法的执行是动态绑定的，方法总是执行该变量实际所指向对象的方法
 * 
 * 变量的类型：
 * 1.编译时类型：声明该变量时指定的类型。
 *              在java程序的编译阶段，java编译器只认编译时类型。
 * 2.运行时类型：（实际类型），该变量实际所引用的对象的类型
 * 
 */

 //多态例子 1
class Bird {
    public void fly() {
        System.out.println("鸟在天上飞");
    }
}

class Sparrow extends Bird {

}

class Ostrich extends Bird {
    public void fly() {
        System.out.println("鸵鸟在地上跑");
    }
}

//多态例子 2
class Shape {
    public void draw() {
        System.out.println("绘制一个图形");
    }
}

class Rectangle extends Shape {
    public void info() {
        System.out.println("我是一个矩形");
    }

    public void draw() {
        System.out.println("绘制一个矩形");
    }
}

class Circle extends Shape {
    public void draw() {
        System.out.println("画圆");
    }
}


public class demo_Duotai {

    public static void main(String[] args) {

        //向上转型:子类对象可以直接赋值给父类变量
        Bird b1 = new Sparrow();
        Bird b2 = new Ostrich();
        b1.fly(); //动态的绑定Sparrow的fly方法，但是Sparrow没有fly，自动回到父类方法
        b2.fly(); //动态绑定Ostrich的fly方法

        // s1的实际类型是Rectangle，但是编译没法通过
        Shape s1 = new Rectangle();
        Shape s2 = new Circle();

        //s1的编译类型是Shape，Shape没有info方法，所以编译不能通过
        //s1.info();

        s1.draw();//运行的时候，方法为Rectangle方法，所以运行的时候看的是实际类型（Rectangle）
    
        //类型强制转换：
        Rectangle r1 = (Rectangle) s1;
        //instanceof只能在变异类型具有继承关系之间进行判断，否则编译报错
        System.out.println(r1 instanceof Rectangle);
        r1.info();
        //非父子关系的强转，编译可以通过，运行时报错ClassCastException(类型转换异常)
        Rectangle r2 = (Rectangle) s2;
        r2.draw();
    }
}



