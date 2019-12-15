package LeetCode;


public class Num_5 {
    /**
     * 最长回文子串暴力解：
     */
    public static String longestPalindrome(String s) {
        int k = 0, m = 0;
        String tmp = "";
        if (s.length() <= 1)
            return s;
        for (int i = 0; i < s.length(); i++) {
            k = i;
            for (int j = s.length() - 1; j > i; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i == k)
                        m = j;
                    if (i + 2 == j || i + 1 == j)
                        if (m - k >= tmp.length())
                            tmp = s.substring(k, m + 1);
                    i++;
                } else if (i != k) {
                    i = k;
                    j = m;
                }
            }
            i = k;
        }
        if (tmp == "")
            return s.substring(0, 1);
        return tmp;
    }

    public static void main(String[] args) {
        String s = "cdda";
        System.out.println(longestPalindrome(s));
    }
}
