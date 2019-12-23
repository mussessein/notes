package Lambda;

import java.util.ArrayList;
/**
 * ��ȫɾ��ListԪ��
 * ���ʼ� Java����.md
 */
public class Lambda_List {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.removeIf(e->3==e);
        System.out.println(list);
    }
}
