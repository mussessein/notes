package Container.Map.HashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Map.Entry<Integer, Integer> entry����
 * ����ֱ����getValue����������Value
 */
public class HashMap_EntySet {
    public static void main(String[] args) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(1, 1);
        hm.put(2, 2);
        hm.put(3, 3);
        hm.put(4, 5);
        // foreach����
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        // ����������
        Iterator<Map.Entry<Integer, Integer>> it = hm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> next = it.next();
            System.out.println(next.getKey() + "=" + next.getValue());
        }
    }
}
