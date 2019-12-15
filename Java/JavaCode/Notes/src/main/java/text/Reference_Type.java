package text;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示java的弱引用对象
 * 具体查看 JVM.md
 * 
 */
public class Reference_Type {

    private static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {

        /*
        创建十个 1M的数组,理论上超出内存限制,
        通过软引用,在超出内存的时候,并不会报错,而是会被回收
         */
        for (int i = 0; i < 10; i++) {
            byte[] buff = new byte[1024 * 1024];
            SoftReference<byte[]> sr = new SoftReference<>(buff);
            list.add(sr);
        }
        // 通知资源回收
        System.gc();
        /*
        打印出刚刚创建的对象
         */
        for(int i=0; i < list.size(); i++){
            Object obj = ((SoftReference) list.get(i)).get();
            System.out.println(obj);
        }
    }
}
