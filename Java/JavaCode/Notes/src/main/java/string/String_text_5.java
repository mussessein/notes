package string;

/**
 * �Զ��巽����ʵ�� trim() ȥ���ַ������ߵĿո�
 */
public class String_text_5 {

    public static void main(String[] args) {
        String s = "aaaaaa";
        String t =s.substring(0,2);
        System.out.println(t);
    }

    public static String MyTrim(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start <= end && s.charAt(start) == ' ') {
            start++;
        }
        while (start <= end && s.charAt(end) == ' ') {
            end--;
        }
        return s.substring(start,end+1);
    }
}