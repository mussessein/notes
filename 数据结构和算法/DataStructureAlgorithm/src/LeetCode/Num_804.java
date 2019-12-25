package LeetCode;

import java.util.TreeSet;

/* leetCode 804
����26����ĸ��Ī��˹��
ʹ��java�ļ����� TreeSet
 */
public class Num_804 {
    private String[] codes={".-","-...","-.-.","-..",".","..-.","--.","....","..",
            ".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...",
            "-","..-","...-",".--","-..-","-.--","--.."};
    public int uniqueMorseRepresentations(String[] words) {
        /*
        1.ÿһ��Ī��˹���Ӧ����һ���ڵ�
        2.���㴫��ĵ���ÿ����ĸ��Ī��˹��codes[word.charAt(i) - 'a'] ���λ��
        3.��Ӵ�Ī��˹�뵽set���ϣ�����ظ���Ĭ�ϲ������ set.add(res.toString());
        4.����set���ϵ�size�����ڲ��ظ�����µ���Ī��˹�����
        4.��Ȧfor��һ�����ʣ���Ȧfor��һ����ĸ
         */
        TreeSet<String> set=new TreeSet<>();
        for (String word:words){
            StringBuilder res =new StringBuilder();
            for (int i = 0; i<word.length(); i++) {
                res.append(codes[word.charAt(i) - 'a']);
            }
            set.add(res.toString());
        }
        return set.size();
    }
    public static void main(String[] args) {

        String[] words={"gin","zen","gig","msg"};
        System.out.println(new Num_804().uniqueMorseRepresentations(words));
    }
}
