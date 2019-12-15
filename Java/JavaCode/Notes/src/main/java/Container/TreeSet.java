package Container;

import java.util.*;


/**
 *           TreeSet：(二分搜索树)
 *  TreeSet可以确保元素处于排序状态，线程不同步。
 * 
 * TreeSet仅通过ComparaTo方法判别对象大小，无关于equals
 * 自动调用ComparaTo(Object obj)方法进行自然排序，升序排列，此方法定义在Comparable接口中，
 * 例如：obj1.comparaTo(obj2), 返回结果： 0-->obj1==obj2;   正整数-->obj1>obj2;  负整数-->obj1<obj2;
 * 
 * ******如果试图将对象添加进TreeSet，此类必须实现Comparable接口，并提供比较大小的标准，否则报错。
 * ******TreeSet只能添加同一种类型的对象~！否则抛出ClassCastException
 * ******如果要自定义排序方式，在要排序的类中重写equals方法和ComparaTo方法，并且保证在equals返回True的同时，ComparaTo返回0；
 * ******也就是说，TreeSet在排序的时候，判别的是ComparaTo方法，在添加，删除，修改的时候判别的是equals方法
 * ******建议不要修改TreeSet和HashSet中的元素！！！！
 *                                       
 */
class TreeSet_demo {

    public static void main(String[] args) {

        TreeSet<Integer> ts1 = new TreeSet<>();
        ts1.add(10);
        ts1.add(8);
        ts1.add(-9);
        ts1.add(3);
        //自动进行排序
        System.out.println(ts1);
        //输出第一个元素
        System.out.println(ts1.first());
        //返回小于4的子集
        System.out.println(ts1.headSet(4));
        //返回大于5的子集
        System.out.println(ts1.tailSet(5));
        //返回大于4，小于9的子集
        System.out.println(ts1.subSet(4, 9));

        System.out.println("自定义排序如下：");

        //自定义排序（使用目标类型为ComparaTo的Lambda表达式：自定义排序方式：按年龄从大到小
        TreeSet<Person_1> ts2 = new TreeSet<>( (p1,p2)->{
            return p1.getAge()>p2.getAge()? -1      //如果在判别式下有三种结果，可以用这种方式！！！！！！
                : p1.getAge()<p2.getAge()? 1:0;
        });
        ts2.add(new Person_1("p_1", 12));
        ts2.add(new Person_1("p_3", 11));
        ts2.add(new Person_1("p_2", 15));
        ts2.add(new Person_1("p_4", 18));
        //在重写toString方法之后，可以打印，否则打印哈希值
        System.out.println(ts2);
        //或者迭代器遍历
        for (Iterator<Person_1> it2 = ts2.iterator(); it2.hasNext();) {
            Person_1 p = (Person_1) it2.next();
            System.out.println(p.getName() + "--" + p.getAge());
        }
    }       
}
