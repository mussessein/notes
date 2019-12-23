package string;

import java.util.*;

/**                                   未完待续(已完善)
 * （有图解）
 * 找到两个字符串中相同子串中最大的子串   (可能存在多个相同长度的子串)  
 * 1.先判断较大字符串中是否含有较小字符串
 * 2.不是，则将比较短的字符串长度减一，继续检查长字符串中是否包含了此子串
 * 3.以此类推
 */
class String_test_3 {
    public static void main(String[] args) {
        String str1 = "abcdefghijklmnoooopikjjjjkjjjj";
        String str2 = "abcdojklmoloooojjjjljjjj";
        List<String> s1 = new ArrayList<String>();
        s1 = getMaxSubstring(str1, str2);
        System.out.print("找到的最大相同子串为 ：");
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
                //若果s1中的最后一个字符串长度不等于当前遍历到的最短的串，则说明最大子串已经便利完毕，返回
                else if (s1.get(a-1).length() != MIN.substring(j, k).length()) {
                    return s1;
                }
                //如果等于，还需要继续遍历，找同样长度的相同子串
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