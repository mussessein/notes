package LeetCode;

import java.util.TreeSet;

/* leetCode 804
给定26个字母的莫尔斯码
使用java的集合类 TreeSet
 */
public class Num_804 {
    private String[] codes={".-","-...","-.-.","-..",".","..-.","--.","....","..",
            ".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...",
            "-","..-","...-",".--","-..-","-.--","--.."};
    public int uniqueMorseRepresentations(String[] words) {
        /*
        1.每一个莫尔斯码对应树的一个节点
        2.计算传入的单词每个字母的莫尔斯码codes[word.charAt(i) - 'a'] 相对位置
        3.添加此莫尔斯码到set集合，如果重复，默认不会添加 set.add(res.toString());
        4.返回set集合的size，即在不重复情况下的总莫尔斯码个数
        4.外圈for是一个单词，内圈for是一个字母
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
