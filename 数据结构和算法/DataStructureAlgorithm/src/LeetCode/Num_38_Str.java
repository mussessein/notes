package LeetCode;

/**
 * 1.     1
 * 2.     11    (上一步是1个1）
 * 3.     21    （上一步是2个1）
 * 4.     1211
 * 5.     111221
 */

public class Num_38_Str {
    public static String countAndSay(int n) {
        int i=1,j=0,k=0;
        String str = "1";
        while (i<n){
            StringBuilder res = new StringBuilder();
            for (int l = 0; l < str.length(); l++) {
                if (str.charAt(j)!=str.charAt(l)) {
                    k=l-j;
                    res.append(k).append(str.charAt(j));
                    j = l;
                }
                if (l==str.length()-1){
                    k=l-j;
                    res.append(k+1).append(str.charAt(l));
                }

            }
            str = res.toString();
            i++;
            j=0;
        }
        return str;
    }


    public static void main(String[] args) {
        int n=4;
        String res =countAndSay(4);
        System.out.println(res);
    }
}

