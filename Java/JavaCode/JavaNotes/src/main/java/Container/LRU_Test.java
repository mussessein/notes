package Container;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ͨ���̳�LinkedHashMap���ֶ�ʵ��һ��LRU��̭�������
 * ֻ��Ҫ�̳�LinkedHashMap��
 * Ȼ����дremoveEldestEntry����̭�Ĺ���
 *
 */
public class LRU_Test extends LinkedHashMap {

    // ��ʾ���洢����Ԫ�أ����������ٴ�����Ԫ�ؽ���ɾ��
    private static final int MAX_ENTRIES=3;

    // �ж������С�Ƿ���� 3����̭����
    protected boolean removeEldestEntry(Map.Entry eldest){

        return size() > MAX_ENTRIES;
    }
    // ʹ�ø��๹����������LinkedHashMap
    public LRU_Test(){
        super(MAX_ENTRIES,0.75f,true);
    }


    public static void main(String[] args) {
        /**
         * ���Է��֣�
         * �������Ԫ�أ�������������
         * ����ӵ��ĸ�Ԫ�ص�ʱ��û���ʵ����Ǹ�Ԫ�ر��Ƴ���
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
