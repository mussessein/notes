package Container;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Container:     元素不可重复，无序！
 * 接口方法和Collection 一致。
 * 1. Set:
 * HashSet集合数据结构是哈希表，存储元素的时候是HashCode方法来确定位置
 * 不保证迭代顺序，无序
 * 2. LinkedHashSet:保持有序
 */
class Set_demo {

    public static void main(String[] args) {

        //Set:
        HashSet<String> hs1 = new HashSet<String>();
        hs1.add("abc1");
        hs1.add("abc2");
        hs1.add("abc3");
        hs1.add("abc4");

        Iterator<String> it1 = hs1.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }

        //发现，一样的值，也被存入HashSet，这是因为，虽然数据一样，但是在哈希表中的值，并不一样
        HashSet<Person_1> hs2 = new HashSet<Person_1>();
        hs2.add(new Person_1("p_1", 21));
        hs2.add(new Person_1("p_1", 21));
        hs2.add(new Person_1("p_2", 22));
        hs2.add(new Person_1("p_3", 23));
        hs2.add(new Person_1("p_4", 24));
        for (Iterator<Person_1> it2 = hs2.iterator(); it2.hasNext(); ) {
            Person_1 p = (Person_1) it2.next();
            System.out.println(p.getName() + "--" + p.getAge());

        }
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(3);
        linkedHashSet.add(3);
        linkedHashSet.add(1);
        linkedHashSet.add(9);
        linkedHashSet.add(1);
        for (int val : linkedHashSet) {
            System.out.println(val);
        }
    }
}

