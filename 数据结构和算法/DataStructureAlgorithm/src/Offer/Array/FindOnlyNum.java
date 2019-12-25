package Offer.Array;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。
 * 找出这两个数
 */
public class FindOnlyNum {
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (hm.containsKey(array[i])) {
                hm.put(array[i], hm.get(array[i]) + 1);
            } else {
                hm.put(array[i], 1);
            }
        }
        for (Integer i : hm.keySet()) {
            if (hm.get(i) == 1) {
                res.add(i);
            }
        }
        num1[0] = res.get(0);
        num2[0] = res.get(1);
    }
}
