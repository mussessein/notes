package string;

import java.util.*;

/**                                   δ�����(������)
 * ����ͼ�⣩
 * �ҵ������ַ�������ͬ�Ӵ��������Ӵ�   (���ܴ��ڶ����ͬ���ȵ��Ӵ�)  
 * 1.���жϽϴ��ַ������Ƿ��н�С�ַ���
 * 2.���ǣ��򽫱Ƚ϶̵��ַ������ȼ�һ��������鳤�ַ������Ƿ�����˴��Ӵ�
 * 3.�Դ�����
 */
class String_test_3 {
    public static void main(String[] args) {
        String str1 = "abcdefghijklmnoooopikjjjjkjjjj";
        String str2 = "abcdojklmoloooojjjjljjjj";
        List<String> s1 = new ArrayList<String>();
        s1 = getMaxSubstring(str1, str2);
        System.out.print("�ҵ��������ͬ�Ӵ�Ϊ ��");
        for (int i = 0; i < s1.size(); i++) {
            System.out.print(s1.get(i) + ",");
        }
    }

    
    public static List<String> getMaxSubstring(String str1, String str2) {
        String MAX, MIN;
        List<String> s1 = new ArrayList<String>();
        int a = 0;
        MAX = (str1.length() > str2.length()) ? str1 : str2;
        MIN = (str1.length() < str2.length()) ? str1 : str2;
        
        for (int i = 0; i < MIN.length(); i++) {

            for (int j = 0, k = MIN.length() - i; k != MIN.length() + 1; j++, k++) {
                if (s1.size() == 0) {
                    String sub = MIN.substring(j, k);
                    System.out.println(sub);
                    if (MAX.contains(sub)){
                        s1.add(sub);
                        a++;
                    }
                }
                //����s1�е����һ���ַ������Ȳ����ڵ�ǰ����������̵Ĵ�����˵������Ӵ��Ѿ�������ϣ�����
                else if (s1.get(a-1).length() != MIN.substring(j, k).length()) {
                    return s1;
                }
                //������ڣ�����Ҫ������������ͬ�����ȵ���ͬ�Ӵ�
                else if (s1.get(a - 1).length() == MIN.substring(j, k).length()) {
                    String sub = MIN.substring(j, k);    
                    System.out.println(sub);
                    if (MAX.contains(sub)){
                        s1.add(sub);
                        a++;
                    }
                }      
            }
        }
        return null;
    }
        
}