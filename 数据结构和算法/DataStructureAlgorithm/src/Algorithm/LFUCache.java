package Algorithm;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class LFUCache<K, V> {
    private int maxSize;
    private HashMap<K, V> hm;
    private HashMap<K, Integer> count;

    public LFUCache(int maxSize) {
        this.maxSize = maxSize;
        hm = new HashMap<>(maxSize);
        count = new HashMap<>(maxSize);
    }

    public V get(K key) {
        if (hm.containsKey(key)) {
            V v = hm.get(key);
            System.out.println(v);
            if (v != null) {
                count.put(key, count.get(key) + 1);
            }
            return v;
        } else {
            System.out.println(-1);
            return null;
        }
    }

    public V set(K key, V value) {
        if (hm.size() == maxSize) {
            K min = getMin();
            hm.remove(min);
        }
        hm.put(key, value);
        count.put(key, new Integer(1));
        return value;
    }

    private K getMin() {
        Collection<Integer> c = count.values();
        V[] obj = (V[]) c.toArray();
        Arrays.sort(obj);
        V v = obj[0];
        for (K key : count.keySet()) {
            if (count.get(key) == v) {
                return key;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LFUCache<Integer, Integer> cache = new LFUCache<Integer, Integer>(3);
        cache.set(2, 2);
        cache.set(1, 1);
        cache.get(2);
        cache.get(1);
        cache.get(2);
        cache.set(3, 3);
        cache.set(4, 4);
        cache.get(3);
        cache.get(2);
        cache.get(1);
        cache.get(4);
    }
}
