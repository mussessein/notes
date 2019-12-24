package DataStructure.Trie;

import java.util.TreeMap;

/*
字典树的实现
以查英文单词为例
1.TreeMap中的value为节点
2.key为字符
3.TreeMap中可以有很多key，例如，根节点的next中有26个key，使用get()方法选择，要进行的分支

 */
public class Trie {

    private class Node{

        public boolean isWord;
        public TreeMap<Character,Node> next; //***在TreeMap中的value为节点
        // 构造单词结尾的节点
        public Node(boolean isWord){
            this.isWord=isWord;
            next=new TreeMap<>();
        }
        //构造非结尾单词的节点
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
    // 整个树存放的单词数量，而非字符数量
    public int getSize(){
        return size;
    }

    public void insert(String word){
        /*
        添加方法：(insert)
        1.设置根节点。传入单词，再进行拆分
        2.判断每个字符是是否已经在树中（map.get(key),key不存在，返回null）
            不在：进行添加，在：跳过添加新的字符
        3.cur指向下一个节点  cur=cur.next.get(c)
        4.循环完毕，cur指向单词末尾,设置isWord=true，单词数量+1
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
        查询整个单词（不包含前缀）：(search)
        1.循环查看每个字符，任意一个字符不存在即 没查到
        2.否则，存在
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
        查询前缀：(startWith)
        1.查看是否有这个前缀，不判断是否是单词
        2.和contains差不多
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
