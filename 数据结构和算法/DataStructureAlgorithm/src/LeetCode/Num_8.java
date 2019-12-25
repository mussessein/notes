package LeetCode;

/*
±¬Õ¨¶ñÐÄ
 */
public class Num_8 {
    public static int myAtoi(String str) {
        String s = str.trim();
        char[] arr = s.toCharArray();
        int len = s.length();
        int k = s.length();
        if (len==0||arr[0] > '9'||s.indexOf("+-")!=-1||s.indexOf("-+")!=-1) return 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] > '9'||arr[i]==' '||arr[i]=='.') {
                k = i;
                break;
            }
        }
        String res=s.substring(0,k);
        if (k==0||(arr[0]=='-'&&k==1)||res.equals("+")) return 0;
        int x;
        if (res.length()>12){
            x=Integer.MAX_VALUE;
            x=Integer.MIN_VALUE;
        }
        long l=Long.parseLong(res);
        x=l>Integer.MAX_VALUE?Integer.MAX_VALUE:(int)l;
        x=l<Integer.MIN_VALUE?Integer.MIN_VALUE:(int)l;
        return (int)x;
    }


    public static void main(String[] args) {

        String x ="20000000000000000000";
        String x1=" i -888 uu ";
        String x2="+qq";
        System.out.println(myAtoi(x));
        char c ='.';
        System.out.println(c);
    }
}
