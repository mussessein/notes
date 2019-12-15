package LeetCode;
/*
����: "the sky is blue"
���: "blue is sky the"

����: "a good   example"
���: "example good a"
����: ����������ʼ��ж���Ŀո񣬽���ת�󵥴ʼ�Ŀո���ٵ�ֻ��һ����

 */
public class Num_151 {

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int high = s.length() - 1, low;
        while (high >= 0) {
            // �ҵ����ʵ����һ����ĸ
            while (high >= 0 && s.charAt(high) == ' ')
                high--;
            low = high;
            // �ҵ����ʵĵ�һ����ĸ��lowΪ��һ����ĸǰ�Ŀո��±��-1��
            while (low >= 0 && s.charAt(low) != ' ')
                low--;
            // ��ȡ����
            sb.append(s.substring(low + 1, high + 1)).append(" ");
            high = low;
        }
        // �жϿմ�("")��ո��ַ���(��" "��)�����
        return s.trim().length() != 0 ?
                sb.toString().trim() : s.trim();
    }

    public static void main(String[] args) {

        String s ="hh kk ii!  ";
        System.out.println(new Num_151().reverseWords(s));
    }
}
