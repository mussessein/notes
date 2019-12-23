package text;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ʾjava�������ö���
 * ����鿴 JVM.md
 * 
 */
public class Reference_Type {

    private static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {

        /*
        ����ʮ�� 1M������,�����ϳ����ڴ�����,
        ͨ��������,�ڳ����ڴ��ʱ��,�����ᱨ��,���ǻᱻ����
         */
        for (int i = 0; i < 10; i++) {
            byte[] buff = new byte[1024 * 1024];
            SoftReference<byte[]> sr = new SoftReference<>(buff);
            list.add(sr);
        }
        // ֪ͨ��Դ����
        System.gc();
        /*
        ��ӡ���ոմ����Ķ���
         */
        for(int i=0; i < list.size(); i++){
            Object obj = ((SoftReference) list.get(i)).get();
            System.out.println(obj);
        }
    }
}
