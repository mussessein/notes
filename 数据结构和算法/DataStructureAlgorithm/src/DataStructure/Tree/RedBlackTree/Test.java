package DataStructure.Tree.RedBlackTree;

import DataStructure.Set.FileOperation;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        ArrayList<String> words=new ArrayList<>();
        FileOperation.readFile("./src/DataStructure/Container/linux.txt",words);
        System.out.println("总单词："+words.size());

        RBTree<String,Integer> map=new RBTree<>();

        for (String word:words){
            //如果已存在word，可以注明此word的出现次数
            if (map.contains(word))
                map.set(word,map.get(word)+1);
            else
                map.add(word,1);
        }
        // 查看非重复总单词量：key的数量，即map的size
        System.out.println("非重复总单词量："+map.getSize());
        //查看某词出现的次数，即某key的value
        System.out.println("mysql出现次数："+map.get("mysql"));

    }
}
