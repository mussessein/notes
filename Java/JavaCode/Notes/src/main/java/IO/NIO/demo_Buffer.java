package IO.NIO;

import java.nio.CharBuffer;
/*
Bufer��������
Channel��ͨ��
���͸�һ��ͨ�����������ݶ��������ȷŵ��������У�ͬ���أ���ͨ���ж�ȡ���κ����ݶ�Ҫ�ȶ����������С�
Ҳ����˵������ֱ�Ӷ�ͨ�����ж�д���ݣ�����Ҫ�Ⱦ�����������
 */
public class demo_Buffer {

    public static void main(String[] args){
        //����Buffer,CharBuffer��һ�������࣬allocate�Ǵ���һ��������,����ռ�
        CharBuffer buff=CharBuffer .allocate(8);
        System.out.println("Buffer������"+buff.capacity());
        System.out.println("limitָ���λ�ã�"+buff.limit());
        System.out.println("position��λ�ã�"+buff.position());

        //����Ԫ��
        buff.put("a");
        buff.put("b");
        buff.put("c");
        System.out.println("��������Ԫ��֮��position="+buff.position());
        //��ִ��flip��positionλ��ָ������޷�get
        //System.out.println(buff.get());

        //flip��������������������limitָ��ָ�����һ���ַ�
        buff.flip();
        System.out.println("ִ��flip֮��limit="+buff.limit());
        System.out.println("ִ��flip֮��position= "+buff.position());

        //ȡ����һ��Ԫ��
        System.out.println("ȡ����һ��Ԫ��"+buff.get());
        System.out.println("ȡ����һ��Ԫ�غ�position="+buff.position());

        //clean����,������ջ������������޸�limitָ�룬��flip��Ӧ��
        // ����position����ָ���һ��Ԫ��
        buff.clear();
        System.out.println("ִ��clean��limit="+buff.limit());
        System.out.println("ִ��clean��position="+buff.position());
        System.out.println(buff.get());
        System.out.println("ִ��clean���þ��Զ�ȡȡ�õ�����Ԫ��:"+buff.get(2));
    }
}
