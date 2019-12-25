package LeetCode;

/*
给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
如果不存在最后一个单词，请返回 0 。
输入: "Hello World"
输出: 5
 */
public class Num_58 {
    public static int lengthOfLastWord(String s) {
        s = s.trim();
        if (s == "")
            return 0;
        String[] arr = s.split(" ");
        return arr[arr.length - 1].length();
    }

    public static int lengthOfLastWord1(String s) {
        if (s == "")
            return 0;
        int res=0,len = s.length()-1;
        int i=len,j=len;
        for (int k = len; k >0; k--) {
            if (s.charAt(k) != ' '&&s.charAt(k-1)==' ') {
                i=j;
                j--;
            }
            else res=i-j;
        }
        return res;
    }


    public static void main(String[] args) {

        String s = "a    ";
        System.out.println(s.length());
        System.out.println(lengthOfLastWord1(s));
    }
}
