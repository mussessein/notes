package Container;

import java.util.*;

/**
 * **********      1.ArrayList内添加对象元素
 * 2.定义一个函数，去除ArrayList内重复元素getSingleElement（）；
 * 3.ArrayList内判断对象是否相同，只通过equals，不判断哈希表。
 * 不同对象，相
 * <p>
 * 三种遍历集合方式：forEach///for///Iterator
 */
class ArrayList_test_1 {

    public static void main(String[] args) {

        ArrayList<Person_1> a1 = new ArrayList<Person_1>();
        a1.add(new Person_1("Num_1", 21));
        a1.add(new Person_1("Num_1", 21));
        a1.add(new Person_1("Num_1", 21));
        a1.add(new Person_1("Num_1", 21));
        a1.add(new Person_1("Num_2", 22));
        a1.add(new Person_1("Num_3", 23));
        a1.add(new Person_1("Num_4", 24));
        
        /*java8新增的Predicate操作集合，批量删除符合条件的元素
        a1.removeIf(ele -> ((String) ele).length() < 20); 删除长度小于20的元素
        a1.remove(new Person_1()); 直接删除第一个对象，
        前提，对象内重写equals方法,此方法，会把第一个元素进行equals比较，返回true时，删除第一个元素，并没有卵用
        */
        a1.removeIf(ele -> ele.getAge() < 22);//()内的代码表示已经遍历了a1的全部元素，并判断条件

        //增强for循环
        for (Person_1 p : a1) {
            System.out.println(p);
        }
        //forEach遍历集合,Lambda
        a1.forEach(p -> System.out.println(p.getName() + "--" + p.getAge()));
        //a1.forEach(System.out::println); 可用于遍历数组

        //Iterato遍历删除元素，当前遍历结果仍包含所删除的元素，下次遍历，元素被删除
        for (Iterator<Person_1> it = a1.iterator(); it.hasNext(); ) {
            Person_1 p = (Person_1) it.next();
            if (p.getAge() == 22)
                it.remove();
            System.out.println(p.getName() + "--" + p.getAge());
        }
        System.out.println("\r");
        for (Iterator<Person_1> it = a1.iterator(); it.hasNext(); ) {
            Person_1 p = (Person_1) it.next();
            System.out.println(p.getName() + "--" + p.getAge());
        }
        /* for遍历集合，不允许删除元素
        for (Person_1 p : a1) {
            System.out.println(p);
        }
        */

        //定义一个函数，去除ArrayList内重复元素：
        ArrayList<String> a2 = new ArrayList<>();
        a2.add("abc1");
        a2.add("abc1");
        a2.add("abc2");
        a2.add("abc1");
        a2.add("abc3");
        System.out.println("去除重复之前： " + a2);
        a2 = getSingleElement(a2);
        System.out.println("去除重复之后： " + a2);
        //单独拿出索引元素
        System.out.println("第三个元素为 ： " + a2.get(2));

        //两种for循环遍历集合元素
        /*   尝试在for遍历下删除元素 抛出java.util.ConcurrentModificationException
        System.out.println("for遍历集合 ： ");
        Iterator<String> it = a2.iterator();
        for (String s : a2) {
            if (s.equals("abc3"))  
                a2.remove(s);
            System.out.println(it.next());
        }*/
        for (Object o : a2) {
            //String s = (String) o;
            System.out.println(o);
        }
    }


    public static ArrayList<String> getSingleElement(ArrayList<String> a2) {
        //先定义一个样本集合
        ArrayList<String> temp = new ArrayList<String>();
        for (Iterator<String> it = a2.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (!temp.contains(obj)) {
                temp.add((String) obj);
            }
        }
        return temp;
    }

}

