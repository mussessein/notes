package Offer.Array;

import java.util.Arrays;

/**
 * ��������һ�����ֳ��ֵĴ����������鳤�ȵ�һ�룬���ҳ�������֡�
 * {1,2,3,2,2,2,5,4,2},����2
 */
public class CountMost {
    // ����֮�󣬼�����ִ���
    public static int MoreThanHalfNum_Solution(int[] array) {
        int len = array.length;
        if (len == 1) return array[0];
        Arrays.sort(array);
        for (int i = 0; i < len - 1; i++) {
            int tmp = array[i];
            int k = 1;
            for (int j = i + 1; j < len; j++) {
                if (array[j] == tmp) {
                    k++;
                    if (k > len / 2) {
                        return tmp;
                    }
                } else {
                    break;
                }
            }
        }
        return 0;
    }

    /**
     * ÿ��ɾ��������ͬ����
     * ������ڴ�������һ������֣��Ǿ�һ�������ʣ�µ�����
     */
    public static int count(int[] array) {
        return 0;
    }

    public static void main(String[] args) {
        int[] array = {1, 2};
        int res = MoreThanHalfNum_Solution(array);
        System.out.println(res);
    }
}
