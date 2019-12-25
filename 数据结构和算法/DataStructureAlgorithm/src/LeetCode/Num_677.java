package LeetCode;

import java.util.TreeMap;

/*
实现一个 MapSum 类里的两个方法，insert 和 sum。
1.对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，
    整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。
2.对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
 */
class MapSum {
    private class Node {
        public int value;//代表单词的value，不代表字符
        public TreeMap<Character, Node> next;

        public Node(int value){
            this.value=value;
            next=new TreeMap<>();
        }
        public Node(){
            this(0);
        }
    }
    private Node root;
    /**
     * Initialize your data structure here.
     */
    public MapSum() {
        root=new Node();
    }

    public void insert(String key, int val) {
        Node cur=root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (cur.next.get(c)==null){
                cur.next.put(c, new Node());
            }
            cur=cur.next.get(c); // 返回TreeMap中的Node
        }
        cur.value=val;
    }
    /*
    找使用这个前缀的所有字符串，并返回这些字符串的value总和
    (如果此前缀至少有一个字符串在用，这个前缀的字符就都不会返回null)
    1.将此prefix遍历完全，找到此前缀最后的节点
    2.遍历最后的节点后面还有多少个单词
    3.计算这些单词的value和
     */
    public int sum(String prefix) {
        Node cur =root;
        // 1
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c)==null){
                return 0;
            }
            cur=cur.next.get(c);
        }
        // 2
        return sum(cur);
    }
    private int sum(Node node){

        //如果遍历到了叶子节点
/*        if (node.next.size()==0){
            return node.value;
        }*/
        int res =node.value;
        /*
        1.通过当前node的所有key，拿到所有的下一层node，进而拿到每个node的value
        2.全部相加，非单词结尾的node的value=0
        3.如果到了叶子节点，keySet为0 ，for循环不会进行，自动跳过，return
         */
        for (char c:node.next.keySet()) {
            res+=sum(node.next.get(c));
        }
        return res;
    }
}

