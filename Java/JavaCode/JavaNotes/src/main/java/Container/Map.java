package Container;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Map �ӿ�
 * ح
 * һһһһһһһһһһһһһһһһһһһһһһһһһһһ
 * ح                       ح                      ح
 * (ʵ����)Hashtable             (ʵ����)Container.Map.HashMap(��)     SortedMap(�ӽӿ�)
 * ح                       ح                      ح
 * (ʵ����)Properties(���������ļ�)     (ʵ����)LinkedMap      (ʵ����)TreeMap(����)
 * <p>
 * Hashtable�̰߳�ȫ�����ǻ������á�
 */
class Map_demo {

    public static void main(String[] args) {
        // Container.Map.HashMap������,����ϣֵ��С˳��
        Map<Integer, String> m1 = new HashMap<>();
        m1.put(111, "abc1");
        m1.put(222, "abc2");
        m1.put(1, "abc3");
        System.out.println(m1);
        System.out.println("m1�Ƿ����002key��" + m1.containsKey(002));
        System.out.println("m1�Ƿ����'abc1'value :" + m1.containsValue("abc1"));
        //����
        for (Object key : m1.keySet()) {
            System.out.println(key + "-->" + m1.get(key));
        }
        //ɾ��
        m1.remove(111);
        System.out.println(m1 + "\n");

        //TreeMap������
        System.out.println("����ΪTreeMapʵ����:");
        TreeMap<Integer, String> tm1 = new TreeMap<>();
        tm1.put(1, "abc1");
        tm1.put(2, "abc2");
        tm1.put(3, "abc3");
        System.out.println(tm1);
        //ȡ��TreeMap����1��ʼ
        System.out.println(tm1.subMap(1, 2));

        // Container.Map.LinkedHashMap������
        Map<Integer, String> m3 = new LinkedHashMap<>();
        m3.put(111, "abc1");
        m3.put(222, "abc2");
        m3.put(1, "abc3");
        //����
        for (Object key : m3.keySet()) {
            System.out.println(key + "-->" + m1.get(key));
        }
    }
}