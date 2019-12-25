package LeetCode;


/*
����һ�� haystack �ַ�����һ�� needle �ַ�����
�� haystack �ַ������ҳ� needle �ַ������ֵĵ�һ��λ�� (��0��ʼ)��
��������ڣ��򷵻�  -1

����: haystack = "hello", needle = "ll"
���: 2
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
