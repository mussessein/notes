package Exception;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 1. 异常：
 * java.util.ConcurrentModificationException（并发修改异常）
 * 2. 原因：
 * (1)多个线程再获取同一个集合里面的数据同时，修改集合中的数据。
 * (2)迭代器过程中对集合的删除操作；
 * 3. 解决方法：
 * (1)使用Vector
 * (2)使用Collections.synchronizedList(new ArrayList<>());
 * (3)使用JUC下CopyOnWriteArrayList,写时复制
 */
public class ConcurrentModificationException {
    public static void main(String[] args) {
        List list = new ArrayList<>();//new CopyOnWriteArrayList()
        // 并发集合的add，引发
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
