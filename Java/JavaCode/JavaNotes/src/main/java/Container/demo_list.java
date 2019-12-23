package Container;

import java.util.*;

/**
 * List： 内部不同步，效率高，     当存入数据过大，自动以50%延长，节省空间   **查询最快，增删略慢**
 * Vector：    内部自动实现线程同步，效率低 ，100%延长，速度慢
 * Linkedist: 内部是链表数据结构，不同步。                 **增删元素的速度很快，查询慢，每次查询从第一个开始**
 * 双向链表实现，只能顺序访问，但是可以快速地在链表中间插入和删除元素
 */
public class demo_list {
    public static void main(String[] args) {

        //List方法：
        List<String> L1 = new ArrayList<String>();
        L1.add("abc1");
        L1.add("abc2");
        L1.add("abc4");
        L1.add("abc5");

        //插入元素
        L1.add(2, "abc3");
        System.out.println(L1);
        //删除
        L1.remove(0);
        System.out.println(L1);
        //修改
        L1.set(0, "abc1");
        System.out.println(L1);
        //获取，索引，循环遍历（不需要迭代器，只有list有）
        System.out.println(L1.get(0));
        for (int i = 0; i < L1.size(); i++) {
            System.out.print(L1.get(i) + ",");
        }
        System.out.println("\r");//换行
        //获取子列表
        List<String> L2 = L1.subList(0, 2);//包含0，不包含2
        System.out.println(L2);

        //java.util.Arrays将数组转化为List
        Integer[] arr = {1, 2, 3};
        List list = Arrays.asList(arr);


//Vector:
        System.out.println("\r");
        System.out.println("以下为Vector的方法：");
        //创建一个Vector对象，初始化容量为5,容量增不设限
        //<String>表示Vector参数和返回值都必须为String类型
        Vector<String> v1 = new Vector<String>(5);
        //添加字符
        v1.addElement(new String("One"));
        v1.addElement("Three");
        v1.addElement("Four");
        // 在对应位置插入字符
        v1.insertElementAt("Zero", 0);
        v1.insertElementAt("Two", 2);
        v1.insertElementAt("Five", 5);
        System.out.println("v1:" + v1);
        System.out.println("v1的容量为：" + v1.capacity());

        //创建一个Vector对象，初始化容量为5，容量增量为1
        Vector<String> v2 = new Vector<String>(5, 1);
        v2.addElement("One");
        v2.addElement("Three");
        v2.addElement("Four");
        v2.insertElementAt("Zero", 0);
        v2.insertElementAt("Two", 2);
        v2.insertElementAt("Five", 5);
        System.out.println("v2:" + v2);
        System.out.println("v2的容量为：" + v2.capacity());


//LinkedList
        System.out.println("\r");
        System.out.println("以下为LinkedList的方法：");
        LinkedList<String> L3 = new LinkedList<String>();
        //遍历加入元素
        for (int i = 0; i < 5; i++) {
            L3.add(String.valueOf(i));
        }
        //在链表头添加元素
        L3.addFirst("7");
        System.out.println(L3);
        System.out.println("最后一个元素为：" + L3.getLast());
        //找到返回位置为3的元素
        System.out.println("第四个元素是 ：" + L3.get(3));
        //将位置为3的元素设置为aaa
        L3.set(3, "aaa");
        System.out.println(L3);
        //找到第一个元素
        System.out.println(L3.peek());
        System.out.println(L3);
        //将第一个元素找出，并删除
        System.out.println(L3.poll());
        System.out.println(L3);
        System.out.println("第一个元素是：" + L3.getFirst());
        System.out.println("最后一个元素是：" + L3.getLast());
    }


}