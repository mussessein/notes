package DataStructure.Set;

import java.util.ArrayList;

/*
����ṹ�Ͷ����������ṹӦ�ã�
�Աȿ��Կ��������������������ٶȻ��ܶ�
��LinkedListSet�У�
    add��O��1���� / contains��O��n���� / remove��O��n����
    �ۺ�ʱ�临�Ӷȶ�Ϊ��O��n��
��BSTSet�У�
    ��������h:(ÿ�α�������ֻ��ӷֲ洦��һ�������ߣ�����ȫ������)
    add��O��h���� / contains��O��h���� / remove��O��h����

 */
public class Test {
    public static void main(String[] args) {
        // ����ṹ-->ͳ���ļ��ĵ�����
        ArrayList<String> words1=new ArrayList<>();
        FileOperation.readFile("./DataStructureAlgorithm/src/main/java/DataStructure/Container/linux.txt",words1);
        System.out.println("�ܵ��ʣ�"+words1.size());

        //����������-->ͳ���ļ������飬set�Դ������ظ�����
        long starttime1=System.currentTimeMillis();
        BSTSet<String> set1=new BSTSet<>();
        for (String word:words1){
            set1.add(word);
        }

        long endtime1=System.currentTimeMillis();
        System.out.println("BSTSet���˺�"+set1.getSize()+" ��ʱ��"+(endtime1-starttime1));

        // ����ṹ-->
        long starttime2=System.currentTimeMillis();
        LinkedListSet<String> set2=new LinkedListSet<>();
        for (String word:words1){
            set2.add(word);
        }
        long endtime2=System.currentTimeMillis();
        System.out.println("LinkedListSet:"+set2.getSize()+" ��ʱ��"+(endtime2-starttime2));
    }
}
