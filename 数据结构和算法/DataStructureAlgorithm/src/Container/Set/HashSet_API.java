package Container.Set;

import java.util.HashSet;

/**
 * 无序不重复(底层是HashMap)
 * 也并不是哈希顺序输出，是通过HashMap的HashCode生成的哈希值排序的结果.
 * 数字哈希值为本身，所以HashSet会按顺序输出.
 * <p>
 * 方法：
 * contains
 * add
 * sizeclear
 * remove
 */
public class HashSet_API {
    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
//        for (int i = 0; i < 10000; i++) {
//            hashSet.add(i);
//        }
        HashSet<String> hashString = new HashSet<>();
        hashSet.add(null);
        hashString.add("123456");
        hashString.add("gegaga");
        hashString.add("ioudas");
        hashString.add("ioqeiu");
        hashString.add("ioeyua");
        hashString.add("ioudiu");
        hashString.add("iouhiu");
        hashString.add("iodaiu");
        hashString.add("ioryiu");
        hashString.add("ioupyu");
        System.out.println(hashString);
        for (String str : hashString) {
            System.out.println(str + "---" + str.hashCode());
        }
    }
}
