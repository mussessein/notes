package Container.Set;

import java.util.HashSet;

/**
 * �����ظ�(�ײ���HashMap)
 * Ҳ�����ǹ�ϣ˳���������ͨ��HashMap��HashCode���ɵĹ�ϣֵ����Ľ��.
 * ���ֹ�ϣֵΪ��������HashSet�ᰴ˳�����.
 * <p>
 * ������
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
