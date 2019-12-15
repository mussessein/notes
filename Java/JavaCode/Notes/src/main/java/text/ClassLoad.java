package text;

public class ClassLoad {
    public static void main(String[] args) {
        Son son = new Son("son名字");
        son.speak();
    }

}
class father {

    private String name;
    A a = new A();
    static {
        System.out.println("父类的静态代码块");
    }

    {
        System.out.println("父类的非静态代码块");
    }

    father() {
        System.out.println("父类的无参构造函数");
    }

    father(String name) {
        this.name = name;
        System.out.println("父类的有参构造函数" + this.name);
    }


    public void speak() {
        System.out.println("父类的方法");
    }

}

class Son extends father {
    private String name;
    B b = new B();
    static {
        System.out.println("子类的静态代码块");
    }

    {
        System.out.println("子类的非静态代码块");
    }

    Son() {
        System.out.println("子类的无参构造函数");
    }

    Son(String name) {
        this.name = name;
        System.out.println("子类的有参构造函数" + this.name);
    }

    @Override
    public void speak() {
        System.out.println("子类Override了父类的方法");
    }
}
class A{
    A(){
        System.out.println("加载了父类的属性A");
    }
}
class B{
    B(){
        System.out.println("加载了子类的属性B");
    }
}
