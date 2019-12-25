package LeetCode;
/*
 ‰»Î: s = "LEETCODEISHIRING", numRows =?4
 ‰≥ˆ:?"LDREOEIIECIHNTSG"
Ω‚ Õ:

L     D     R
E   O E   I I
E C   I H   N
T     S     G
 */
public class Num_6_String {
    public static String convert(String s, int numRows) {
        int len = s.length();
        if (len == 0)
            return "";
        if (numRows==1||len<numRows)
            return s;
        int n = (numRows - 2) * 2 + 1;
        int k;
        StringBuilder res = new StringBuilder();
        char[] str = s.toCharArray();
        for (int i = 0; i < numRows; i++) {
            k = i;
            int h1 = n - (i * 2) + 1;
            int h2 = n - h1 + 1;
            res.append(str[i]);
            while (k < len) {
                if (h1 != 0 && k + h1 < len) {
                    res.append(str[k + h1]);
                }
                if (h2 != 0 && k + h1 + h2 < len) {
                    res.append(str[k + h1 + h2]);
                }
                k += h1 + h2;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "P";
        String str = convert(s, 2);
        System.out.println(str);
    }
}
