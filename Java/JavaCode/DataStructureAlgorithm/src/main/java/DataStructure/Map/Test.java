package DataStructure.Map;

import DataStructure.Set.FileOperation;
import DataStructure.Tree.RedBlackTree.RBTree;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        // ����ṹ-->ͳ���ļ��ĵ�����
        ArrayList<String> words=new ArrayList<>();
        FileOperation.readFile("./DataStructureAlgorithm/src/main/java/DataStructure/Map/linux.txt",words);
        System.out.println("�ܵ��ʣ�"+words.size());

        /*
        ����LinkedListMap
         */
        long st1=System.currentTimeMillis();
        LinkedListMap<String,Integer> map1=new LinkedListMap<>();
        for (String word:words){
            //����Ѵ���word������ע����word�ĳ��ִ���
            if (map1.contains(word))
                map1.set(word,map1.get(word)+1);
            else
                map1.add(word,1);
        }
        long end1=System.currentTimeMillis();
        // �鿴���ظ��ܵ�������key����������map��size
        System.out.println("���ظ��ܵ�������"+map1.getSize());
        //�鿴ĳ�ʳ��ֵĴ�������ĳkey��value
        System.out.println("mysql���ִ�����"+map1.get("mysql"));
        /*
        ����BSTMap
         */
        long st2=System.currentTimeMillis();
        BSTMap<String,Integer> map2=new BSTMap<>();
        for (String word:words){
            //����Ѵ���word������ע����word�ĳ��ִ���
            if (map2.contains(word))
                map2.set(word,map2.get(word)+1);
            else
                map2.add(word,1);
        }
        long end2=System.currentTimeMillis();
        // �鿴���ظ��ܵ�������key����������map��size
        System.out.println("���ظ��ܵ�������"+map2.getSize());
        //�鿴ĳ�ʳ��ֵĴ�������ĳkey��value
        System.out.println("mysql���ִ�����"+map2.get("mysql"));

        /*
        ���Ժ����
         */
        long st3=System.currentTimeMillis();
        RBTree<String,Integer> map3=new RBTree<>();
        for (String word:words){
            //����Ѵ���word������ע����word�ĳ��ִ���
            if (map3.contains(word))
                map3.set(word,map3.get(word)+1);
            else
                map3.add(word,1);
        }
        long end3=System.currentTimeMillis();
        // �鿴���ظ��ܵ�������key����������map��size
        System.out.println("���ظ��ܵ�������"+map3.getSize());
        //�鿴ĳ�ʳ��ֵĴ�������ĳkey��value
        System.out.println("mysql���ִ�����"+map3.get("mysql"));


        System.out.println("==================================================");
        System.out.println("LinkedListMap��ʱ��"+(end1-st1));
        System.out.println("BSTMap��ʱ��"+(end2-st2));
        System.out.println("RBTMap��ʱ��"+(end3-st3));
    }
}
