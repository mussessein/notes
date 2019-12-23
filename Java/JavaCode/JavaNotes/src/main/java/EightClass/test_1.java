package EightClass;

import java.util.Arrays;

/** 
 * ��ϰ��
 *      ��һ�� �ַ��� ��ֵ���д�С��������,�����ַ������
 *      �ַ���---->������������
*/
public class test_1 {
    private static final String SPACE_STRING = " ";

    public static void main(String[] args) {

        String numstr = "20 80 40 302 30 -7 128";
        System.out.println("["+numstr+"]");
        String numstr1 = sortNumString(numstr);
        System.out.println("["+numstr1+"]");
    }

    private static String sortNumString(String numstr) {
        //1.���ַ�������ַ�������
        String[] arr_str = numstr.split(SPACE_STRING); //ʹ�ÿո�ָ��ַ���Ϊ�ַ�������
        //2.���ַ���������int����
        int[] arr_num = StrToInt(arr_str);
        //����������
        SortArray(arr_num);
        //��������int���飬��Ϊ�ַ���
        String temp = ArrayToStr(arr_num);
        return temp;
    }

    
    private static String ArrayToStr(int[] arr_num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr_num.length; i++) {
            if (i != arr_num.length - 1) {
                sb.append(arr_num[i] + SPACE_STRING);
            } else {
                sb.append(arr_num[i]);
            }
        }
        return sb.toString();
    }

    
    private static void SortArray(int[] arr_num) {
        Arrays.sort(arr_num);
    }

    
    private static int[] StrToInt(String[] arr_str) {
        int[] arr_num = new int[arr_str.length];
        for (int i = 0; i < arr_num.length; i++) {
            arr_num[i] = Integer.parseInt(arr_str[i]);
        }
        return arr_num;
    }

}