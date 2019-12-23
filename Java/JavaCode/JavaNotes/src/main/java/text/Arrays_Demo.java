package text;

import java.util.Arrays;

public class Arrays_Demo {
    public static void main(String[] args) {

        int[] a ={1,2,8,4,6,0,3};
        // 默认生序排列
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));

        // 二分查找，返回索引位置
        int index = Arrays.binarySearch(a, 6);
        System.out.println(index);

        // 复制长度为3
        int[] c =Arrays.copyOf(a,3);
        System.out.println(Arrays.toString(c));
    }
}
