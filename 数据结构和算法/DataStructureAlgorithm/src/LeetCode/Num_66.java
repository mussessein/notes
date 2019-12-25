package LeetCode;

import java.util.Arrays;

/*
输入: [1,2,3]
输出: [1,2,4]
解释: 输入数组表示数字 123。
输入: [9,9,9]
输出: [1,0,0,0]
解释: 输入数组表示数字 1000。
 */
public class Num_66 {
    public static  int[] plusOne(int[] digits) {

        return plusOne(digits.length-1,digits);
    }
    private static int[] plusOne(int length,int[] digits){

        if (length==0&digits[length]==9){
            digits=new int[digits.length+1];
            digits[0]=1;
            return digits;
        }
        int end = length;
        if (digits[end]==9){
            digits[end]=0;
            return plusOne(end-1,digits);
        }else
            digits[end]+=1;
        return digits;
    }

    public static void main(String[] args) {
        int[] nums={1,2,3,4};
        int[] b = plusOne(nums);
        System.out.println(Arrays.toString(b));
    }
}
