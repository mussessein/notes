package Container;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 通过继承LinkedHashMap，手动实现一个LRU淘汰缓存机制
 * 只需要继承LinkedHashMap，
 * 然后重写removeEldestEntry，淘汰的规则
 *
 */
public class LRU_Test extends LinkedHashMap {

    // 表示最多存储三个元素，被访问最少次数的元素将被删除
    private static final int MAX_ENTRIES=3;

    // 判断链表大小是否大于 3（淘汰规则）
    protected boolean removeEldestEntry(Map.Entry eldest){

        return size() > MAX_ENTRIES;
    }
    // 使用父类构造器，构造LinkedHashMap
    public LRU_Test(){
        super(MAX_ENTRIES,0.75f,true);
    }


    public static void main(String[] args) {
        /**
         * 测试发现：
         * 添加三个元素，访问了两个。
         * 当添加第四个元素的时候，没访问到的那个元素被移除了
         */
        LRU_Test lru = new LRU_Test();
        lru.put(1,"a");
        lru.put(2,"b");
        lru.put(3,"c");
        lru.get(3);
        lru.get(2);
        lru.put(4,"d");
        System.out.println(lru.keySet());
    }
}
