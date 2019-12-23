package inherit;

/**
 * 静态初始化块的执行顺序
 */
class Root {
    static {
        System.out.println("Root静态初始化块");
    }
    {
        System.out.println("Root普通初始化块");
    }

    public Root() {
        System.out.println("Root无参构造器");
    }
}

class Mid extends Root {
    static {
        System.out.println("Mid静态初始化块");
    }
    {
        System.out.println("Mid普通初始化块");
    }

    public Mid() {
        System.out.println("Mid无参构造器");
    }
}

class Leaf extends Mid {
    static {
        System.out.println("Leaf静态初始化块");
    }
    {
        System.out.println("Leaf普通初始化块");
    }

    public Leaf() {
        System.out.println("Leaf无参构造器");
    }
}
public class StaticTest {
    public static void main(String[] args) {
        //第一次创建此类，初始化会先执行顶层父类的静态初始化块，继而普通初始化块，再是构造器！！！！
        new Leaf();
        System.out.println("---------------------");
        //此类初始化成功之后，再虚拟机中就一直存在，所以第二次创建此类不会再进行静态初始化
        new Leaf();
    }
    
}