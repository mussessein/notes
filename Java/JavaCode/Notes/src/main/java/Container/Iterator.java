package Container;

import java.util.*;

/**
 * Iterator接口
 * 迭代器有三个方法：
 * 1. next();     返回迭代的下一个元素
 * 2. hasnext();  如果还有元素，则返回true，没有，返回false
 * ListIterator接口
 * 1.增加了向前迭代的功能，
 * 2.增加了在迭代过程中添加元素（Iterator只能向后迭代，并且只能删除元素）
 */
class Iterator_demo {
    public static void main(String[] args) {

        Collection<String> c1 = new ArrayList<String>();
        c1.add("abc1");
        c1.add("abc2");
        c1.add("abc3");
        c1.add("abc4");
        c1.add("abc5");
        Collection<String> c2 = c1;
        Iterator<String> it1 = c1.iterator();

        //还有元素，则返回true，
        while (it1.hasNext()) {
            System.out.print(it1.next() + ",");
        }
        System.out.println("\r");
        //两种方法，第二种方法稍微省一丢丢内存。
        for (Iterator<String> it2 = c2.iterator(); it2.hasNext();) {
            System.out.print(it2.next() + ",");
        }
        System.out.println("\r");
        

        System.out.println("迭代器子类方法操作:");
        //如果在迭代器的迭代过程中，使用集合操作添加删除元素，会抛出异常！必须使用Iterator的子类ListIterator
        List<String> L1 = new ArrayList<String>();
        L1.add("abc1");
        L1.add("abc2");
        L1.add("abc3");

        //必须使用Iterator的子类ListIterator,使用子类ListIterator下的方法，在迭代过程中，对List进行操作
        for (ListIterator<String> it3 = L1.listIterator(); it3.hasNext();) {
            Object obj = it3.next();
            //如果含有"abc2"，则添加一个"abc9"
            if (obj.equals("abc2")) {
                it3.add("abc9");//it3.set("abc8"); 
            }
        }
        System.out.println(L1);

        //反向迭代
        for (ListIterator<String> it4 = L1.listIterator(); it4.hasPrevious();) {
            System.out.print(it4.previous()+",");
        }
    }
}