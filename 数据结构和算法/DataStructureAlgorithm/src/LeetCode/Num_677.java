package LeetCode;

import java.util.TreeMap;

/*
ʵ��һ�� MapSum ���������������insert �� sum��
1.���ڷ��� insert���㽫�õ�һ�ԣ��ַ������������ļ�ֵ�ԡ��ַ�����ʾ����
    ������ʾֵ��������Ѿ����ڣ���ôԭ���ļ�ֵ�Խ���������µļ�ֵ�ԡ�
2.���ڷ��� sum���㽫�õ�һ����ʾǰ׺���ַ���������Ҫ���������Ը�ǰ׺��ͷ�ļ���ֵ���ܺ͡�
 */
class MapSum {
    private class Node {
        public int value;//�����ʵ�value���������ַ�
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
            cur=cur.next.get(c); // ����TreeMap�е�Node
        }
        cur.value=val;
    }
    /*
    ��ʹ�����ǰ׺�������ַ�������������Щ�ַ�����value�ܺ�
    (�����ǰ׺������һ���ַ������ã����ǰ׺���ַ��Ͷ����᷵��null)
    1.����prefix������ȫ���ҵ���ǰ׺���Ľڵ�
    2.�������Ľڵ���滹�ж��ٸ�����
    3.������Щ���ʵ�value��
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

        //�����������Ҷ�ӽڵ�
/*        if (node.next.size()==0){
            return node.value;
        }*/
        int res =node.value;
        /*
        1.ͨ����ǰnode������key���õ����е���һ��node�������õ�ÿ��node��value
        2.ȫ����ӣ��ǵ��ʽ�β��node��value=0
        3.�������Ҷ�ӽڵ㣬keySetΪ0 ��forѭ��������У��Զ�������return
         */
        for (char c:node.next.keySet()) {
            res+=sum(node.next.get(c));
        }
        return res;
    }
}

