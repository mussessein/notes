package Container.Map.LinkedHashMap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU:最近最少使用算法
 * 使用过的和新来的，会放在LinkedHashMap队尾
 * 超出缓存大小，移除队首元素。
 * 继承LinkedHashMap，重写removeEldestEntry方法（移除策略）
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int maxEntries;

    public LRUCache(int maxEntries) {
        super(16, 0.75f, true);
        this.maxEntries = maxEntries;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > maxEntries;
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<Integer, String>(3);
        lruCache.put(1, "a");
        lruCache.put(2, "b");
        lruCache.put(3, "c");
        lruCache.get(1);
        lruCache.get(2);
        lruCache.get(1);
        lruCache.put(4, "d");
        /**
         * 3没有被访问过，所以加入4的时候，删除了3
         * 并且，新加入的元素，在尾部
         */
        for (Map.Entry<Integer, String> entry : lruCache.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        System.out.println("------------------------");
        // 再次插入，删除了2
        lruCache.put(5, "e");
        for (Map.Entry<Integer, String> entry : lruCache.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}