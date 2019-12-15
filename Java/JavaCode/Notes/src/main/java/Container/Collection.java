package Container;

import java.util.*;

/**
 * Collection 集合框架的一个根接口
 * Collecrions工具操作List集合
 */
class Set_notes {
    public static void main(String[] args) {
        Collection<String> c1 = new ArrayList<String>();
        Collection<String> c2 = new ArrayList<String>();
        show_1(c1);
        show_2(c2);
        System.out.println("c1 =" + c1);
        System.out.println("c2 =" + c2);
        //删除元素
        c1.remove("abc3");
        //removeAll
        show_1(c1);
        show_2(c2);
        c1.removeAll(c2); //将两个集合相同的元素，从c1中删除。
        System.out.println(c1);
        //retainAll
        show_1(c1);
        show_2(c2);
        c1.retainAll(c2); //保留两个集合中相同的元素。
        System.out.println(c1);


        //使用Collections工具类来操作List集合
        ArrayList<Integer> AL1 = new ArrayList<>();
        AL1.add(2);
        AL1.add(90);
        AL1.add(70);
        AL1.add(15);
        System.out.println(AL1);
        //逆序
        Collections.reverse(AL1);
        //自然排序
        Collections.sort(AL1);
        //输出最大元素
        System.out.println(Collections.max(AL1));//min
        //替换
        Collections.replaceAll(AL1, 70, 80);
        //统计某元素出现次数
        System.out.println(Collections.frequency(AL1, 15));
        //索引某元素位置
        System.out.println(AL1);
        System.out.println(AL1.indexOf(15));
    }


    private static void show_2(Collection<String> c2) {
        c2.clear();
        c2.add("abc2");
        c2.add("abc5");
        c2.add("abc6");
    }

    private static void show_1(Collection<String> c1) {
        c1.clear();
        c1.add("abc1");
        c1.add("abc2");
        c1.add("abc3");
        c1.add("abc4");
    }


}