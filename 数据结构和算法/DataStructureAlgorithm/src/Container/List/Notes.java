package Container.List;

import java.util.*;


public class Notes {
    public static void main(String[] args) {
        Set<Integer> hashset = new HashSet<>();
        hashset.add(3);
        hashset.add(2);
        hashset.add(1);
        /**
         * List:������ظ��б�Ĭ������10����������50%���ɰ������null
         * ��Ҫ������set��get��add��last
         */
        List<Integer> list_1 = new ArrayList<>(hashset);//Ĭ������10
        list_1.set(1, 10);  // ����ֵ
        list_1.get(1);
        list_1.add(2);
        System.out.println(list_1);
        /**
         * LinkedList:�����null
         */
        List<Object> linked = new LinkedList<>();
        linked.add(null);
        linked.add(null);
        linked.add(null);
        System.out.println(linked);
    }
}
