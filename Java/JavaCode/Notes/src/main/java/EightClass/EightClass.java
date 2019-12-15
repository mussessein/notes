package EightClass;
/**
 * ��������������ͣ�
 * Ϊ�˲������㣬����������������ͷ�װ�ɶ����ڶ����ж��������Ժ���Ϊ��
 * ���������ö������ͳ�Ϊ�����������Ͷ����װ�ࡣ
 * ��Ҫ�����ڻ����������ͺ��ַ���֮���ת��
 * byte         Byte    
 * short        Short
 * int          Integer (-128~127)
 * long         Long
 * float        Float
 * double       Double
 * char         Character
 * boolean      Boolean
 * 
 * ������������---->�ַ���
 *          1. �����������͵�ֵ+" ";
 *          2. ��String���еľ�̬����valueOf(��������������ֵ)�� 
 * �ַ���----->������������
 *          1. ʹ�ð�װ���еľ�̬����   xxx.parsexxx(" "); ת�����ͣ�������
 *                              ���磺Integer.parseInt(" ");   ��Character���⣩
 *
 */
class EightClass {
    public static void main(String[] args) {
        //�鿴���������ֵ
        System.out.println(Integer.MAX_VALUE);
        //Integer�Ĺ�����
        System.out.println("123" + 1);
        //���ַ���"123����ɴ���������123���ٽ������
        System.out.println(Integer.parseInt("123") + 1);
        System.out.println(Boolean.parseBoolean("true"));
        //�鿴��������
        System.out.println(Integer.toBinaryString(-10));
        System.out.println(Integer.toOctalString(60));//�˽���
        System.out.println(Integer.toHexString(60));//ʮ������
        System.out.println(Integer.toString(60, 4));//ת��Ϊ�Ľ���
        //��������--->ʮ����
        System.out.println(Integer.parseInt("1c", 16));//ʮ�����Ƶ�"1c"ת��Ϊʮ����

        //�Զ�װ��ɶ���ֱ�ӽ������������͸�ֵ������ i 
        Integer i = 4;
        //��������ʱ���Զ���������
        i = i + 4;
        //������������ת��Ϊ�ַ�������
        System.out.println(String.valueOf(568)+1);
        System.out.println(String.valueOf(2.345f)+1);
        System.out.println(123+""+1);
    }
}