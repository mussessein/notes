package Container.List;

import java.util.*;


public class Notes {
    public static void main(String[] args) {
        Set<Integer> hashset = new HashSet<>();
        hashset.add(3);
        hashset.add(2);
        hashset.add(1);
        /**
         * List:有序可重复列表，默认容量10，扩容自增50%，可包含多个null
         * 主要方法：set，get，add，last
         */
        List<Integer> list_1 = new ArrayList<>(hashset);//默认容量10
        list_1.set(1, 10);  // 设置值
        list_1.get(1);
        list_1.add(2);
        System.out.println(list_1);
        /**
         * LinkedList:可添加null
         */
        List<Object> linked = new LinkedList<>();
        linked.add(null);
        linked.add(null);
        linked.add(null);
        System.out.println(linked);
    }
}
