package Container;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Map 接口
 * 丨
 * 一一一一一一一一一一一一一一一一一一一一一一一一一一一
 * 丨                       丨                      丨
 * (实现类)Hashtable             (实现类)Container.Map.HashMap(快)     SortedMap(子接口)
 * 丨                       丨                      丨
 * (实现类)Properties(处理属性文件)     (实现类)LinkedMap      (实现类)TreeMap(有序)
 * <p>
 * Hashtable线程安全，但是基本不用。
 */
class Map_demo {

    public static void main(String[] args) {
        // Container.Map.HashMap（无序,按哈希值大小顺序）
        Map<Integer, String> m1 = new HashMap<>();
        m1.put(111, "abc1");
        m1.put(222, "abc2");
        m1.put(1, "abc3");
        System.out.println(m1);
        System.out.println("m1是否包含002key：" + m1.containsKey(002));
        System.out.println("m1是否包含'abc1'value :" + m1.containsValue("abc1"));
        //遍历
        for (Object key : m1.keySet()) {
            System.out.println(key + "-->" + m1.get(key));
        }
        //删除
        m1.remove(111);
        System.out.println(m1 + "\n");

        //TreeMap（有序）
        System.out.println("以下为TreeMap实现类:");
        TreeMap<Integer, String> tm1 = new TreeMap<>();
        tm1.put(1, "abc1");
        tm1.put(2, "abc2");
        tm1.put(3, "abc3");
        System.out.println(tm1);
        //取子TreeMap，从1开始
        System.out.println(tm1.subMap(1, 2));

        // Container.Map.LinkedHashMap（有序）
        Map<Integer, String> m3 = new LinkedHashMap<>();
        m3.put(111, "abc1");
        m3.put(222, "abc2");
        m3.put(1, "abc3");
        //遍历
        for (Object key : m3.keySet()) {
            System.out.println(key + "-->" + m1.get(key));
        }
    }
}