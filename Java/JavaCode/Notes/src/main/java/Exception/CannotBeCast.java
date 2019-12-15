package Exception;

import java.util.ArrayList;
import java.util.List;
/*
* 编译可以通过，运行抛出异常
* 12行，b不能强转为a
* 子类强转父类，可以
* 父类强转子类，有风险
*
* */
public class CannotBeCast {
    public static void main(String[] args){
        List<cat> cats=new ArrayList<cat>();
        cats.add(new cat());
        Animal b=new Animal();
        cat a =null;
        // 只有父类对象本身就是用子类new出来的时候, 才可以在将来被强制转换为子类对象
        if (b instanceof Animal)a=(cat)b;  //(运行时异常)Animal cannot be cast to class Exception.cat
        if (a!=null)cats.add(a);
        System.out.println(cats.size()+"cats");
    }
}
class Animal{}
class cat extends Animal{}