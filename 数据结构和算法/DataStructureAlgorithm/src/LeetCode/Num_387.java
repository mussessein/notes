package LeetCode;

/*
leetCode 387 题（哈希的简单实现）
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
假定该字符串只包含小写字母。
s = "loveleetcode",
返回 2.
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