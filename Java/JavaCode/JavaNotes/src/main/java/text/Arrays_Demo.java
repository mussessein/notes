package text;

import java.util.Arrays;

public class Arrays_Demo {
    public static void main(String[] args) {

        int[] a ={1,2,8,4,6,0,3};
        // Ĭ����������
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));

        // ���ֲ��ң���������λ��
        int index = Arrays.binarySearch(a, 6);
        System.out.println(index);

        // ���Ƴ���Ϊ3
        int[] c =Arrays.copyOf(a,3);
        System.out.println(Arrays.toString(c));
    }
}
