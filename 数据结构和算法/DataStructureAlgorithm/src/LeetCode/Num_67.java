package LeetCode;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class Num_67 {


    public static String addBinary(String a, String b) {


        int len_1 = a.length()>b.length()?a.length()-1:b.length()-1;
        int len_2 = a.length()>b.length()?b.length()-1:a.length()-1;
        String c=a.length()>b.length()?a:b;
        String d=a.length()>b.length()?b:a;
        String res="";
        char flag = '0';
        for (int i = len_1; i >= 0; i--) {
            if (c.charAt(i)=='0'){
                res=d.charAt(i)+res;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(addBinary("101","11"));

    }
}
