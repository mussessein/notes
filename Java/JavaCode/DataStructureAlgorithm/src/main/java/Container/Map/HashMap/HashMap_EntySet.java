package Container.Map.HashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Map.Entry<Integer, Integer> entry对象，
 * 可以直接用getValue方法，访问Value
 */
public class HashMap_EntySet {
    public static void main(String[] args) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(1, 1);
        hm.put(2, 2);
        hm.put(3, 3);
        hm.put(4, 5);
        // foreach遍历
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        // 迭代器遍历
        Iterator<Map.Entry<Integer, Integer>> it = hm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> next = it.next();
            System.out.println(next.getKey() + "=" + next.getValue());
        }
    }
}
