package LeetCode;

import java.util.Arrays;

/*
����: [1,2,3]
���: [1,2,4]
����: ���������ʾ���� 123��
����: [9,9,9]
���: [1,0,0,0]
����: ���������ʾ���� 1000��
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
