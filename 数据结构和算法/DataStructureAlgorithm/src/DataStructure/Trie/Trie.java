package DataStructure.Trie;

import java.util.TreeMap;

/*
�ֵ�����ʵ��
�Բ�Ӣ�ĵ���Ϊ��
1.TreeMap�е�valueΪ�ڵ�
2.keyΪ�ַ�
3.TreeMap�п����кܶ�key�����磬���ڵ��next����26��key��ʹ��get()����ѡ��Ҫ���еķ�֧

 */
public class Trie {

    private class Node{

        public boolean isWord;
        public TreeMap<Character,Node> next; //***��TreeMap�е�valueΪ�ڵ�
        // ���쵥�ʽ�β�Ľڵ�
        public Node(boolean isWord){
            this.isWord=isWord;
            next=new TreeMap<>();
        }
        //����ǽ�β���ʵĽڵ�
        public Node(){
            this(false);
        }
    }
    private Node root;
    private int size;

    public Trie(){
        root=new Node();
        size=0;
    }
    // ��������ŵĵ��������������ַ�����
    public int getSize(){
        return size;
    }

    public void insert(String word){
        /*
        ��ӷ�����(insert)
        1.���ø��ڵ㡣���뵥�ʣ��ٽ��в��
        2.�ж�ÿ���ַ����Ƿ��Ѿ������У�map.get(key),key�����ڣ�����null��
            ���ڣ�������ӣ��ڣ���������µ��ַ�
        3.curָ����һ���ڵ�  cur=cur.next.get(c)
        4.ѭ����ϣ�curָ�򵥴�ĩβ,����isWord=true����������+1
         */
        Node cur= root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c)==null){
                cur.next.put(c,new Node());
            }
            cur=cur.next.get(c);
        }
        cur.isWord = true;
        size++;
    }

    public boolean search(String word){
        /*
        ��ѯ�������ʣ�������ǰ׺����(search)
        1.ѭ���鿴ÿ���ַ�������һ���ַ������ڼ� û�鵽
        2.���򣬴���
         */
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c =word.charAt(i);
            if (cur.next.get(c)==null){
                return false;
            }
            cur=cur.next.get(c);
        }
        if(cur.isWord) {
            return true;
        }
        return false;
    }

    public boolean startsWith(String prefix){
        /*
        ��ѯǰ׺��(startWith)
        1.�鿴�Ƿ������ǰ׺�����ж��Ƿ��ǵ���
        2.��contains���
         */
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c =prefix.charAt(i);
            if (cur.next.get(c)==null){
                return false;
            }
            cur=cur.next.get(c);
        }
        return true;
    }


}
