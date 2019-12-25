package DataStructure.Map;

import DataStructure.Set.FileOperation;
import DataStructure.Tree.RedBlackTree.RBTree;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        // 数组结构-->统计文件的单词数
        ArrayList<String> words=new ArrayList<>();
        FileOperation.readFile("./DataStructureAlgorithm/src/main/java/DataStructure/Map/linux.txt",words);
        System.out.println("总单词："+words.size());

        /*
        测试LinkedListMap
         */
        long st1=System.currentTimeMillis();
        LinkedListMap<String,Integer> map1=new LinkedListMap<>();
        for (String word:words){
            //如果已存在word，可以注明此word的出现次数
            if (map1.contains(word))
                map1.set(word,map1.get(word)+1);
            else
                map1.add(word,1);
        }
        long end1=System.currentTimeMillis();
        // 查看非重复总单词量：key的数量，即map的size
        System.out.println("非重复总单词量："+map1.getSize());
        //查看某词出现的次数，即某key的value
        System.out.println("mysql出现次数："+map1.get("mysql"));
        /*
        测试BSTMap
         */
        long st2=System.currentTimeMillis();
        BSTMap<String,Integer> map2=new BSTMap<>();
        for (String word:words){
            //如果已存在word，可以注明此word的出现次数
            if (map2.contains(word))
                map2.set(word,map2.get(word)+1);
            else
                map2.add(word,1);
        }
        long end2=System.currentTimeMillis();
        // 查看非重复总单词量：key的数量，即map的size
        System.out.println("非重复总单词量："+map2.getSize());
        //查看某词出现的次数，即某key的value
        System.out.println("mysql出现次数："+map2.get("mysql"));

        /*
        测试红黑树
         */
        long st3=System.currentTimeMillis();
        RBTree<String,Integer> map3=new RBTree<>();
        for (String word:words){
            //如果已存在word，可以注明此word的出现次数
            if (map3.contains(word))
                map3.set(word,map3.get(word)+1);
            else
                map3.add(word,1);
        }
        long end3=System.currentTimeMillis();
        // 查看非重复总单词量：key的数量，即map的size
        System.out.println("非重复总单词量："+map3.getSize());
        //查看某词出现的次数，即某key的value
        System.out.println("mysql出现次数："+map3.get("mysql"));


        System.out.println("==================================================");
        System.out.println("LinkedListMap用时："+(end1-st1));
        System.out.println("BSTMap用时："+(end2-st2));
        System.out.println("RBTMap用时："+(end3-st3));
    }
}
