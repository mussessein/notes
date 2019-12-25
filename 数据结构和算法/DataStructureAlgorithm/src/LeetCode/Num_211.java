package LeetCode;


import java.util.TreeMap;
/*
LeetCode 211
Trie��ʵ������������ʣ���������������
search(word) �����������ֻ�������ʽ�ַ������ַ���ֻ������ĸ . �� a-z   .���Ա�ʾ�κ�һ����ĸ��

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
        public TreeMap<Character, Node> next; //***��TreeMap�е�valueΪ�ڵ�

        // ���쵥�ʽ�β�Ľڵ�
        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        //����ǽ�β���ʵĽڵ�
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
        // ��root��ʼ������0��ʼƥ��
        return match(root, word, 0);
    }

    /*
    ͨ���������ķ�����ʵ��ͨ���
     */
    private boolean match(Node node, String word, int index) {
        /*
        1.index������length������ȫ��index�����ҹ��ˣ�ƥ�䣬����true
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
            // ������.��ȡ����һ���ڵ�map�е�����key�����ν��б���
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
