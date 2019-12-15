package text;

/**                            ！！！！！重点！！！！！！！
 *                      ！！！！！面向接口编程！！！！！！！
 *     接口的设计：以对象作为形参，传入对象不同，实现的方法不用。
       实现了方法和方法体的分离，传入对象的不同，实现的方法体不同。 *
 */
/*
接口的成员（字段 + 方法）默认都是 public 的，并且不允许定义为 private 或者 protected。
接口的字段默认都是 static 和 final 的。
 */

interface Command {
    //在这样的设计中，可能需要用到这样一种方法
    //但是具体的方法实现，在接口中不给提供，在子类中，具体实现
    void process(int[] target);
}

class ProcessArray {
    //此方法是，需要传入一个Command下的子类对象，来决定实现哪种process方法！！！！
    //传入不同的Command子类，效果不同
    public void process(int[] target, Command cmd) {
        cmd.process(target);
    }
}

class PrintCommand implements Command {
    //重写父类方法

    public void process(int[] target) {
        for (int tmp : target) {
            System.out.println("迭代输出目标数组的元素："+tmp);
        }
    }
}

class AddCommand implements Command {

    public void process(int[] target) {
        int sum = 0;
        for (int tmp : target) {
            sum += tmp;
        }
        System.out.println("元素总和为："+sum);
    }
}

public class demo_interface {

    public static void main(String[] args) {
        ProcessArray pa = new ProcessArray();
        int[] target = { 3, 1, -4, 8, 9 };
        //第一次处理数组，如何处理？取决于创建了哪一个对象，此处为PrintCommand对象
        pa.process(target, new PrintCommand());
        //实现了方法和方法体的分离，传入对象的不同，实现的方法体不同。
        System.out.println("--------------------------");
        //第一次处理数组，如何处理？取决于创建了哪一个对象，此处为AddCommand对象
        pa.process(target, new AddCommand());
    }
}