package LeetCode;


import java.util.TreeMap;
/*
LeetCode 211
Trie树实现添加搜索单词，并包含正则搜索
search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z   .可以表示任何一个字母。

 */

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
class WordDictionary {
    private class Node {

        public boolean isWord;
        public TreeMap<Character, Node> next; //***在TreeMap中的value为节点

        // 构造单词结尾的节点
        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        //构造非结尾单词的节点
        public Node() {
            this(false);
        }
    }

    private Node root;

    public WordDictionary() {
        root = new Node();
    }
    /*
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        // 从root开始，索引0开始匹配
        return match(root, word, 0);
    }

    /*
    通过遍历树的方法，实现通配符
     */
    private boolean match(Node node, String word, int index) {
        /*
        1.index增长到length，代表全部index都查找过了，匹配，返回true
         */
        if (index == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(index);
        if (c != '.') {
            if (node.next.get(c) == null) {
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        } else {
            // 遇到‘.’取出下一个节点map中的所有key，依次进行遍历
            for (char nextChar : node.next.keySet()) {
                if (match(node.next.get(nextChar), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }

    }

    public static void main(String[] args) {

        WordDictionary wd=new WordDictionary();
        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");
        System.out.println(wd.search("pad"));
        System.out.println(wd.search("bad"));
        System.out.println(wd.search(".ad"));
        System.out.println(wd.search("b.."));
    }
}
