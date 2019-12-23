package text;

/**
 * һ�����ô��ݣ�pass by reference��
 * 1.���ô��ݱ����Ǿֲ�����ͨ������ȫ�ֱ������ڴ��ַ���õ�ȫ�ֱ�����ֵ
 *   �������Ծֲ��������д����˾ֲ��������õĵ�ַ����ȫ�ֱ����ĵ�ַ
 * 2.��������ڲ��Ծֲ��������д���
 *   ��java�Զ�����һ���µ��ڴ��ַ�����洦����ľֲ�������ȫ�ֱ�����Ȼ�����ܵ�Ӱ��
 * */

/**
 * ����ֵ���ݣ�pass by value��
 *
 */
public class demo_reference {

  public static void demo_1(int num) {

    int hashcode = System.identityHashCode(num);
    System.out.println("�����д���ľֲ�����num��ַ:" + hashcode);
  }

  public static void demo_2(int num) {
    num += 1;
    int hashcode = System.identityHashCode(num);
    System.out.println("���м���֮��ľֲ�����num��ַ��" + hashcode);
  }


  public static void main(String[] args) {

    /*
    һ�����ô��ݣ�
      1.����һ��ȫ�ֱ���  ������ӡ�ڴ��ַ
      2.�鿴�����ֲ��������ڴ��ַ
     */
    int gl_num = 99;
    int hashcode = System.identityHashCode(gl_num);
    System.out.println("ȫ�ֱ���num��ַ:" + hashcode);

    demo_1(gl_num);
    demo_2(gl_num);
    System.out.println(gl_num);


  }
}
