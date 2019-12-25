package Offer.Array;

import java.util.Arrays;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * {1,2,3,2,2,2,5,4,2},返回2
 */
public class CountMost {
    // 快排之后，计算出现次数
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
     * 每次删除两个不同数字
     * 如果存在次数超过一般的数字，那就一定是最后剩下的数字
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
