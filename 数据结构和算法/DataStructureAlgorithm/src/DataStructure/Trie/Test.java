package DataStructure.Trie;

import DataStructure.Set.BSTSet;
import DataStructure.Set.FileOperation;

import java.util.ArrayList;

/*
�Ƚ϶�����������Trie��
 */
public class Test {

    public static void main(String[] args) {

        ArrayList<String> words=new ArrayList<>();
        FileOperation.readFile("./src/DataStructure/Container/linux.txt",words);
        System.out.println("�ܵ��ʣ�"+words.size());

        /*
        ����������-->ͳ���ļ������飬set�Դ������ظ�����
        1.��ӵ���
        2.��������
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
        System.out.println("BSTSet��ӵ�������"+set1.getSize()+" ��ʱ��"+(endtime1-starttime1));

        // Trie�ṹ-->
        long starttime2=System.currentTimeMillis();
        Trie trie=new Trie();
        for (String word:words){
            trie.insert(word);
        }
        for (String word:words){
            trie.search(word);
        }
        long endtime2=System.currentTimeMillis();
        System.out.println("Trie��ӵ�����:"+trie.getSize()+" ��ʱ��"+(endtime2-starttime2));

    }
}
