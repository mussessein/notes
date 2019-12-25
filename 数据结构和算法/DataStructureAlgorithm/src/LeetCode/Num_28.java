package LeetCode;


/*
给定一个 haystack 字符串和一个 needle 字符串，
在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
如果不存在，则返回  -1

输入: haystack = "hello", needle = "ll"
输出: 2
 */
public class Num_28 {
    public int strStr(String haystack, String needle) {

        int a = haystack.length();
        int b = needle.length();
        if (a<b) return -1;
        else if (b==0) return 0;
        for (int i = 0; i < a - b + 1; i++ ){
            if (haystack.substring(i, i+b).equals(needle)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {

        String haystack = "hello";
        String needle = "ll";
        System.out.println(new Num_28().strStr("", ""));
    }
}
