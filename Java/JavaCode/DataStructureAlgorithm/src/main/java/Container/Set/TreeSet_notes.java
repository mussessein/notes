package Container.Set;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * ����������
 * �������ظ���������null,��ָ���쳣��
 * �ײ㣺TreeMap
 * ������contains��add��clear��remove,addAll,first,last
 */
public class TreeSet_notes {
    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();

        String[] ls = {"asd", "asdfggg", "qwzda"};
        Collections.addAll(set, ls);
        System.out.println(set);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(null, "ad");
    }
}
