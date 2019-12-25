package LeetCode;

/*
leetCode 387 �⣨��ϣ�ļ�ʵ�֣�
����һ���ַ������ҵ����ĵ�һ�����ظ����ַ���������������������������ڣ��򷵻� -1��
�ٶ����ַ���ֻ����Сд��ĸ��
s = "loveleetcode",
���� 2.
 */
class Solution {
    public int firstUniqChar(String s) {
        int[] freq=new int[26];

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i)-'a']==1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "wuuddfw";
        System.out.println(new Solution().firstUniqChar(s));
    }
}