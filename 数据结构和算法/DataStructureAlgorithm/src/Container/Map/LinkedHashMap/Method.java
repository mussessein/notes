package Container.Map.LinkedHashMap;

import java.util.LinkedHashMap;
import java.util.Map;

public class Method {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> lhm =
                new LinkedHashMap<>(3, 0.75f, true);
        // 1. ���LinkedHashMap
        lhm.clear();
        lhm.put(1, "a");
        lhm.put(2, "b");
        lhm.put(3, "c");
        // ��һ�α�����
        for (Map.Entry<Integer, String> entry : lhm.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        System.out.println("=========����֮���ٱ�����========");
        //����֮���ٱ��������ʴ������ٵ�������ǰ��
        lhm.get(1);
        lhm.get(1);
        lhm.get(2);
        lhm.get(1);
        for (Map.Entry<Integer, String> entry : lhm.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        /**
         * 2.removeEldestEntry:��LRUCache
         */


    }
}
