package Container;

import java.util.*;


/**
 *           TreeSet��(����������)
 *  TreeSet����ȷ��Ԫ�ش�������״̬���̲߳�ͬ����
 * 
 * TreeSet��ͨ��ComparaTo�����б�����С���޹���equals
 * �Զ�����ComparaTo(Object obj)����������Ȼ�����������У��˷���������Comparable�ӿ��У�
 * ���磺obj1.comparaTo(obj2), ���ؽ���� 0-->obj1==obj2;   ������-->obj1>obj2;  ������-->obj1<obj2;
 * 
 * ******�����ͼ��������ӽ�TreeSet���������ʵ��Comparable�ӿڣ����ṩ�Ƚϴ�С�ı�׼�����򱨴�
 * ******TreeSetֻ�����ͬһ�����͵Ķ���~�������׳�ClassCastException
 * ******���Ҫ�Զ�������ʽ����Ҫ�����������дequals������ComparaTo���������ұ�֤��equals����True��ͬʱ��ComparaTo����0��
 * ******Ҳ����˵��TreeSet�������ʱ���б����ComparaTo����������ӣ�ɾ�����޸ĵ�ʱ���б����equals����
 * ******���鲻Ҫ�޸�TreeSet��HashSet�е�Ԫ�أ�������
 *                                       
 */
class TreeSet_demo {

    public static void main(String[] args) {

        TreeSet<Integer> ts1 = new TreeSet<>();
        ts1.add(10);
        ts1.add(8);
        ts1.add(-9);
        ts1.add(3);
        //�Զ���������
        System.out.println(ts1);
        //�����һ��Ԫ��
        System.out.println(ts1.first());
        //����С��4���Ӽ�
        System.out.println(ts1.headSet(4));
        //���ش���5���Ӽ�
        System.out.println(ts1.tailSet(5));
        //���ش���4��С��9���Ӽ�
        System.out.println(ts1.subSet(4, 9));

        System.out.println("�Զ����������£�");

        //�Զ�������ʹ��Ŀ������ΪComparaTo��Lambda���ʽ���Զ�������ʽ��������Ӵ�С
        TreeSet<Person_1> ts2 = new TreeSet<>( (p1,p2)->{
            return p1.getAge()>p2.getAge()? -1      //������б�ʽ�������ֽ�������������ַ�ʽ������������
                : p1.getAge()<p2.getAge()? 1:0;
        });
        ts2.add(new Person_1("p_1", 12));
        ts2.add(new Person_1("p_3", 11));
        ts2.add(new Person_1("p_2", 15));
        ts2.add(new Person_1("p_4", 18));
        //����дtoString����֮�󣬿��Դ�ӡ�������ӡ��ϣֵ
        System.out.println(ts2);
        //���ߵ���������
        for (Iterator<Person_1> it2 = ts2.iterator(); it2.hasNext();) {
            Person_1 p = (Person_1) it2.next();
            System.out.println(p.getName() + "--" + p.getAge());
        }
    }       
}
