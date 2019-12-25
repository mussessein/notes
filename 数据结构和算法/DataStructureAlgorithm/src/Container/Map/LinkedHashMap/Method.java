package Container.Map.LinkedHashMap;

import java.util.LinkedHashMap;
import java.util.Map;

public class Method {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> lhm =
                new LinkedHashMap<>(3, 0.75f, true);
        // 1. 清空LinkedHashMap
        lhm.clear();
        lhm.put(1, "a");
        lhm.put(2, "b");
        lhm.put(3, "c");
        // 第一次遍历：
        for (Map.Entry<Integer, String> entry : lhm.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        System.out.println("=========访问之后再遍历：========");
        //访问之后再遍历：访问次数最少的排在最前面
        lhm.get(1);
        lhm.get(1);
        lhm.get(2);
        lhm.get(1);
        for (Map.Entry<Integer, String> entry : lhm.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        /**
         * 2.removeEldestEntry:见LRUCache
         */


    }
}
