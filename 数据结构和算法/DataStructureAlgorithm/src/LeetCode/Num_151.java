package LeetCode;
/*
输入: "the sky is blue"
输出: "blue is sky the"

输入: "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

 */
public class Num_151 {

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int high = s.length() - 1, low;
        while (high >= 0) {
            // 找到单词的最后一个字母
            while (high >= 0 && s.charAt(high) == ' ')
                high--;
            low = high;
            // 找到单词的第一个字母（low为第一个字母前的空格下标或-1）
            while (low >= 0 && s.charAt(low) != ' ')
                low--;
            // 截取单词
            sb.append(s.substring(low + 1, high + 1)).append(" ");
            high = low;
        }
        // 判断空串("")或空格字符串(如" "等)的情况
        return s.trim().length() != 0 ?
                sb.toString().trim() : s.trim();
    }

    public static void main(String[] args) {

        String s ="hh kk ii!  ";
        System.out.println(new Num_151().reverseWords(s));
    }
}
