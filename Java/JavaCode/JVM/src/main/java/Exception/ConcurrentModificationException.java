package Exception;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 1. �쳣��
 * java.util.ConcurrentModificationException�������޸��쳣��
 * 2. ԭ��
 * (1)����߳��ٻ�ȡͬһ���������������ͬʱ���޸ļ����е����ݡ�
 * (2)�����������жԼ��ϵ�ɾ��������
 * 3. ���������
 * (1)ʹ��Vector
 * (2)ʹ��Collections.synchronizedList(new ArrayList<>());
 * (3)ʹ��JUC��CopyOnWriteArrayList,дʱ����
 */
public class ConcurrentModificationException {
    public static void main(String[] args) {
        List list = new ArrayList<>();//new CopyOnWriteArrayList()
        // �������ϵ�add������
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
