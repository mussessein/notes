package Offer;

public class Solution_2 {
    public static String replaceSpace(StringBuffer str) {

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                str.replace(i, i + 1, "%20");
                i += 2;
            }

        }
        return str.toString();
    }

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("he wo");
        String res = replaceSpace(str);
        System.out.println(res);
    }
}
