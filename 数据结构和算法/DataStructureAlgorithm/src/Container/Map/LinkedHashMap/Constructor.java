package Container.Map.LinkedHashMap;

import java.util.LinkedHashMap;

/**
 * LinkedHashMap�����й��캯��
 * accessOrder��Ĭ��false
 * false������˳���ղ��������������ᱻ�ı�
 * true������˳���շ���˳����ѭLRU���ƣ���̭����Capacity��Ԫ�أ�
 */
public class Constructor {

    public static void main(String[] args) {
        // 1. �չ��캯����Ĭ�ϴ�С16���������ӣ�0.75
        LinkedHashMap<Integer, String> LHM_1 = new LinkedHashMap<>();
        // 2. �Զ�������
        LinkedHashMap<Integer, String> LHM_2 = new LinkedHashMap<>(5);
        // 3. �Զ��������͸������ӣ�
        //    accessOrder��false�� ���ڲ���˳��   true�����ڷ���˳��,
        LinkedHashMap<Integer, String> LHM_3 = new LinkedHashMap<>(5, .75f, false);
    }
}
