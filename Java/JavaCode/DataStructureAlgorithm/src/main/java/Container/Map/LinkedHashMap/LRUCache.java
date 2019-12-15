package Container.Map.LinkedHashMap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU:�������ʹ���㷨
 * ʹ�ù��ĺ������ģ������LinkedHashMap��β
 * ���������С���Ƴ�����Ԫ�ء�
 * �̳�LinkedHashMap����дremoveEldestEntry�������Ƴ����ԣ�
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
         * 3û�б����ʹ������Լ���4��ʱ��ɾ����3
         * ���ң��¼����Ԫ�أ���β��
         */
        for (Map.Entry<Integer, String> entry : lruCache.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        System.out.println("------------------------");
        // �ٴβ��룬ɾ����2
        lruCache.put(5, "e");
        for (Map.Entry<Integer, String> entry : lruCache.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}