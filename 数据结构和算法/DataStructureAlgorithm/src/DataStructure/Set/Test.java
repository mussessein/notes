package DataStructure.Set;

import java.util.ArrayList;

/*
链表结构和二分搜索树结构应用：
对比可以看出：二分搜索树插入速度会快很多
在LinkedListSet中：
    add【O（1）】 / contains【O（n）】 / remove【O（n）】
    综合时间复杂度都为：O（n）
在BSTSet中：
    假设树高h:(每次遍历，都只会从分叉处往一个方法走，不会全部遍历)
    add【O（h）】 / contains【O（h）】 / remove【O（h）】

 */
public class Test {
    public static void main(String[] args) {
        // 数组结构-->统计文件的单词数
        ArrayList<String> words1=new ArrayList<>();
        FileOperation.readFile("./DataStructureAlgorithm/src/main/java/DataStructure/Container/linux.txt",words1);
        System.out.println("总单词："+words1.size());

        //二分搜索树-->统计文件单词书，set自带过滤重复单词
        long starttime1=System.currentTimeMillis();
        BSTSet<String> set1=new BSTSet<>();
        for (String word:words1){
            set1.add(word);
        }

        long endtime1=System.currentTimeMillis();
        System.out.println("BSTSet过滤后："+set1.getSize()+" 用时："+(endtime1-starttime1));

        // 链表结构-->
        long starttime2=System.currentTimeMillis();
        LinkedListSet<String> set2=new LinkedListSet<>();
        for (String word:words1){
            set2.add(word);
        }
        long endtime2=System.currentTimeMillis();
        System.out.println("LinkedListSet:"+set2.getSize()+" 用时："+(endtime2-starttime2));
    }
}
