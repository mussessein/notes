package Container.Map.LinkedHashMap;

import java.util.LinkedHashMap;

/**
 * LinkedHashMap有四中构造函数
 * accessOrder：默认false
 * false：遍历顺序按照插入来遍历，不会被改变
 * true：遍历顺序按照访问顺序，遵循LRU机制，淘汰超过Capacity的元素；
 */
public class Constructor {

    public static void main(String[] args) {
        // 1. 空构造函数，默认大小16，负载因子：0.75
        LinkedHashMap<Integer, String> LHM_1 = new LinkedHashMap<>();
        // 2. 自定义容量
        LinkedHashMap<Integer, String> LHM_2 = new LinkedHashMap<>(5);
        // 3. 自定义容量和负载因子，
        //    accessOrder：false： 基于插入顺序   true：基于访问顺序,
        LinkedHashMap<Integer, String> LHM_3 = new LinkedHashMap<>(5, .75f, false);
    }
}
