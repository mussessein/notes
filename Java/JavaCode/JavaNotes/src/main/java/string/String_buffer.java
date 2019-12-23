package string;

/**
 * StringBuffer ���߳�ͬ�������ڶ��̣߳�
 * ����һ���ַ���������
 * ��ʼ����Ϊ16���ַ����൱�ڷ�װ�����飬�����ö����൱�ڴ���һ��16�ַ����飻
 * ���ڴ洢���ݵ�������
 * �ص㣺
 * 1. �����ǿɱ�ģ�
 * 2. ���Դ洢��ͬ���͵����ݣ�
 * 3. ����Ҫת���ַ������д���
 * 
 * ���ܣ�
 * 1. ��� ��append(); ����StringBuffer;
 *          insert( , );����
 * 2. ɾ�� ��delete( start,end );��ͷ������β
 *          deleteCharAt(index);ɾ��ָ��λ�õ�Ԫ��
 * 3. ���� ��char  charAt(index);
 *          int   indexOf(String);
 *          int  lastIndexOf(String)
 * 4. �޸� ��StringBuffer  replace(start,end,String);
 *           void setCharAt(index,char);
 * 
 * StrigBuilder���̲߳�ͬ�������ڵ��̣߳����и��죩
 * JDK 5 ��ʼ���³���StringBuffer��ͬ���ܵ�API��
 * StringBuffer��ʵ���߳�ͬ�������������ٶȸ��졣
 *           
 * Ϊʲô�����̰߳�ȫ������
 *      ��StringBuffer�ķ��������ͬʱ������Ӻ�ɾ��Ԫ�أ������߳̾ͻ���ڰ�ȫ�������⡣
 *    ����StringByffer���Զ�����߳�ͬ����ÿ�ε��÷��������ж�����
 *    StringBuilder�����ڵ��̣߳�����Ҫͬ���������ٶȸ��죬Ч�ʸ��� ��
 */
class String_buffer {

    public static void main(String[] args) {

        StringBuffer s = new StringBuffer();
        StringBuffer s1 = s.append(4);
        s.append(true);
        System.out.println(s == s1);
    //���������Դ洢�κ����͵�����
        System.out.println(s);
    //ָ��λ�ò����ַ�����
        s.insert(1, "haha");
        System.out.println(s);
    //���û���������,���Ȳ����Զ���ӿո�
        s.setLength(15);
        System.out.println("s=" + s);
    // ��ջ�����
        s.delete(0, s.length());
    //������õ��ַ�������16���ַ������������Զ����ӳ���
        StringBuffer s2 = new StringBuffer("asdfghjkloiuytrewqwxcv");
        System.out.println(s2);
        
    }
}