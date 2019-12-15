package DataStructure.Tree.RedBlackTree;

import DataStructure.Set.FileOperation;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        ArrayList<String> words=new ArrayList<>();
        FileOperation.readFile("./src/DataStructure/Container/linux.txt",words);
        System.out.println("�ܵ��ʣ�"+words.size());

        RBTree<String,Integer> map=new RBTree<>();

        for (String word:words){
            //����Ѵ���word������ע����word�ĳ��ִ���
            if (map.contains(word))
                map.set(word,map.get(word)+1);
            else
                map.add(word,1);
        }
        // �鿴���ظ��ܵ�������key����������map��size
        System.out.println("���ظ��ܵ�������"+map.getSize());
        //�鿴ĳ�ʳ��ֵĴ�������ĳkey��value
        System.out.println("mysql���ִ�����"+map.get("mysql"));

    }
}
