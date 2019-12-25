package Offer.Array;

import java.util.Arrays;

/**
 * 将数组拼接成一个最小数
 */
public class MinNumber {
    // 冒泡排序，太复杂，直接用sort，重写CompareTo
    public static String PrintMinNumber(int[] numbers) {
        int len = numbers.length;
        String[] strs = new String[len];
        for (int i = 0; i < len; i++) {
            strs[i] = String.valueOf(numbers[i]);
        }

        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (strs[min].charAt(0) > strs[j].charAt(0)) {
                    min = j;
                } else if (strs[min].charAt(0) == strs[j].charAt(0)) {
                    String a = strs[j] + strs[min];
                    String b = strs[min] + strs[j];
                    min = a.compareTo(b) >= 0 ? i : j;
                }
            }
            String t = strs[i];
            strs[i] = strs[min];
            strs[min] = t;
        }
        String res = "";
        for (int i = 0; i < len; i++) {
            res += strs[i];
        }
        return res;
    }

    // chong
    public static String MinNumber(int[] numbers) {
        int len = numbers.length;
        String[] strs = new String[len];
        for (int i = 0; i < len; i++) {
            strs[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(strs, (String o1, String o2) ->
                ((o1 + "" + o2).compareTo(o2 + "" + o1))
        );
        String res = "";
        for (int i = 0; i < len; i++) {
            res += strs[i];
        }
        return res;
    }


    public static void main(String[] args) {
        int[] numbers = {5, 4, 1, 2};
        String res = MinNumber(numbers);
        System.out.println(res);
    }
}
