package Container;

import java.util.*;

/**
 * List�� �ڲ���ͬ����Ч�ʸߣ�     ���������ݹ����Զ���50%�ӳ�����ʡ�ռ�   **��ѯ��죬��ɾ����**
 * Vector��    �ڲ��Զ�ʵ���߳�ͬ����Ч�ʵ� ��100%�ӳ����ٶ���
 * Linkedist: �ڲ����������ݽṹ����ͬ����                 **��ɾԪ�ص��ٶȺܿ죬��ѯ����ÿ�β�ѯ�ӵ�һ����ʼ**
 * ˫������ʵ�֣�ֻ��˳����ʣ����ǿ��Կ��ٵ��������м�����ɾ��Ԫ��
 */
public class demo_list {
    public static void main(String[] args) {

        //List������
        List<String> L1 = new ArrayList<String>();
        L1.add("abc1");
        L1.add("abc2");
        L1.add("abc4");
        L1.add("abc5");

        //����Ԫ��
        L1.add(2, "abc3");
        System.out.println(L1);
        //ɾ��
        L1.remove(0);
        System.out.println(L1);
        //�޸�
        L1.set(0, "abc1");
        System.out.println(L1);
        //��ȡ��������ѭ������������Ҫ��������ֻ��list�У�
        System.out.println(L1.get(0));
        for (int i = 0; i < L1.size(); i++) {
            System.out.print(L1.get(i) + ",");
        }
        System.out.println("\r");//����
        //��ȡ���б�
        List<String> L2 = L1.subList(0, 2);//����0��������2
        System.out.println(L2);

        //java.util.Arrays������ת��ΪList
        Integer[] arr = {1, 2, 3};
        List list = Arrays.asList(arr);


//Vector:
        System.out.println("\r");
        System.out.println("����ΪVector�ķ�����");
        //����һ��Vector���󣬳�ʼ������Ϊ5,������������
        //<String>��ʾVector�����ͷ���ֵ������ΪString����
        Vector<String> v1 = new Vector<String>(5);
        //����ַ�
        v1.addElement(new String("One"));
        v1.addElement("Three");
        v1.addElement("Four");
        // �ڶ�Ӧλ�ò����ַ�
        v1.insertElementAt("Zero", 0);
        v1.insertElementAt("Two", 2);
        v1.insertElementAt("Five", 5);
        System.out.println("v1:" + v1);
        System.out.println("v1������Ϊ��" + v1.capacity());

        //����һ��Vector���󣬳�ʼ������Ϊ5����������Ϊ1
        Vector<String> v2 = new Vector<String>(5, 1);
        v2.addElement("One");
        v2.addElement("Three");
        v2.addElement("Four");
        v2.insertElementAt("Zero", 0);
        v2.insertElementAt("Two", 2);
        v2.insertElementAt("Five", 5);
        System.out.println("v2:" + v2);
        System.out.println("v2������Ϊ��" + v2.capacity());


//LinkedList
        System.out.println("\r");
        System.out.println("����ΪLinkedList�ķ�����");
        LinkedList<String> L3 = new LinkedList<String>();
        //��������Ԫ��
        for (int i = 0; i < 5; i++) {
            L3.add(String.valueOf(i));
        }
        //������ͷ���Ԫ��
        L3.addFirst("7");
        System.out.println(L3);
        System.out.println("���һ��Ԫ��Ϊ��" + L3.getLast());
        //�ҵ�����λ��Ϊ3��Ԫ��
        System.out.println("���ĸ�Ԫ���� ��" + L3.get(3));
        //��λ��Ϊ3��Ԫ������Ϊaaa
        L3.set(3, "aaa");
        System.out.println(L3);
        //�ҵ���һ��Ԫ��
        System.out.println(L3.peek());
        System.out.println(L3);
        //����һ��Ԫ���ҳ�����ɾ��
        System.out.println(L3.poll());
        System.out.println(L3);
        System.out.println("��һ��Ԫ���ǣ�" + L3.getFirst());
        System.out.println("���һ��Ԫ���ǣ�" + L3.getLast());
    }


}