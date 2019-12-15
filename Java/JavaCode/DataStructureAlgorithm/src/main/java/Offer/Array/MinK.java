package Offer.Array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ����n���������ҳ�������С��K����
 * 4,5,1,6,2,7,3,8,k=4
 * ���أ�1,2,3,4
 */
public class MinK {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (k > input.length) return res;
        Arrays.sort(input);
        for (int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }
}
