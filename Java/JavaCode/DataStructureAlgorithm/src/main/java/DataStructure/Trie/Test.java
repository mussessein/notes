package DataStructure.Trie;

import DataStructure.Set.BSTSet;
import DataStructure.Set.FileOperation;

import java.util.ArrayList;

/*
比较二分搜索树和Trie树
 */
public class Test {

    public static void main(String[] args) {

        ArrayList<String> words=new ArrayList<>();
        FileOperation.readFile("./src/DataStructure/Container/linux.txt",words);
        System.out.println("总单词："+words.size());

        /*
        二分搜索树-->统计文件单词书，set自带过滤重复单词
        1.添加单词
        2.遍历单词
         */
        long starttime1=System.currentTimeMillis();
        BSTSet<String> set1=new BSTSet<>();
        for (String word:words){
            set1.add(word);
        }
        for(String word:words){
            set1.contains(word);
        }
        long endtime1=System.currentTimeMillis();
        System.out.println("BSTSet添加单词数："+set1.getSize()+" 用时："+(endtime1-starttime1));

        // Trie结构-->
        long starttime2=System.currentTimeMillis();
        Trie trie=new Trie();
        for (String word:words){
            trie.insert(word);
        }
        for (String word:words){
            trie.search(word);
        }
        long endtime2=System.currentTimeMillis();
        System.out.println("Trie添加单词数:"+trie.getSize()+" 用时："+(endtime2-starttime2));

    }
}
