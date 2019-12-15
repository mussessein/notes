package string;
/*
String������Դ�룺
public String(String original) {
    this.value = original.value;
    this.hash = original.hash;
}
 */

/**
 * String �� ��
 * 1. �ַ���Ϊ�������ַ�������һ������ʼ���Ͳ��ᱻ�ı䣬���Թ���
 * 2.�����ַ�����ʱ�򣬻������ַ����������������������ַ��������û���ҵ����򴴽��� ����ҵ����򲻻ᴴ���µ��ַ�����ֱ��ʹ�����е��ַ�����
 * String s2= s1.intern()�ӳ��������õ�s1��ֵ��s2
 * <p>
 * String��ķ�����(String s;)
 * 1. ��ȡ�ַ������� int s.length();
 * 2. ��ȡָ��λ�õ��ַ� char charAt(int index);
 * 3. ��ȡָ���ַ���һ�γ��ֵ�λ�� int indexOf(int ch);
 * ��ָ��λ�ÿ�ʼ���� int indexOf(int ch,int fromindex);
 * ��ȡָ���ַ�����һ�γ��ֵ�λ�� int indexOf(String str); int indexOf(String str,int fromindex); ���������� int lastIndexOf();
 * 4. ��ȡ�ַ����е��Ӵ� String substring(int beginIndex,int endindex);
 * �ִ���begin��ʼ��begin���Բ�д��Ĭ��0����end-1 ����������
 * <p>
 * 5. �ַ�������ַ������飨�ַ����и String[] split(String regex);������ʽ
 * 6. ���ַ�������ַ����� char[] toCharArray();
 * 7. �ַ���ת����Сд ��д ��String toUpperCase();
 * Сд : String toLowerCase();
 * 8. �ַ�(��)�滻 String replace(char oldch,char newcha);
 * 9. ����ַ�����
 * str.split(" ",2) ��������ʽ��֣����ո�Ͳ�2��ʾӦ�ô��������Բ�д��������ʾ�𵽵�
 * *****
 * 9. �Զ�ȥ���ַ������˵Ŀո� .trim();
 * 10. �ַ������� �� "str".concat(String)
 * 11. �ı������������ String.valueOf();
 * ***** �жϣ�
 * 1. �Ƚ��ַ������ݣ� .equals( ); ���ִ�Сд .equalsIgnoreCase();�����ִ�Сд
 * 2. �ж����Ƿ���ĳ�ַ��� boolean startsWith(String);
 * 3. �ж��ַ����Ƿ���ָ���ַ�����ͷ���Ƿ���ָ���ַ�����β boolean stratWith(str); boolean endsWith(str);
 * <p>
 * *****
 * �Ƚϣ�
 * str1.compareTo(str2);
 */
/*
String������Ϊfinal�����ɱ��̳У����ɱ�
�ô���
    1.���Ի��� hash ֵ��
        ��Ϊ String �� hash ֵ������ʹ�ã�
        ���� String ���� Container.Map.HashMap �� key�����ɱ�����Կ���ʹ�� hash ֵҲ���ɱ䣬���ֻ��Ҫ����һ�μ��㡣
    2.String Pool ����Ҫ��
        ���һ�� String �����Ѿ����������ˣ���ô�ͻ�� String Pool ��ȡ�����á�
        ֻ�� String �ǲ��ɱ�ģ��ſ���ʹ�� String Pool��
    3.��ȫ�ԣ�
        String�������������������ɱ���Ա�֤�������ɱ�
    4.�̰߳�ȫ��
        ��Ȼ�̰߳�ȫ
 */
/*
String ���ɱ�
StringBuffer  �ɱ䣬���̰߳�ȫ
StringBuilder �ɱ䣬�̰߳�ȫ���ڲ�ʹ��synchronizedͬ��
 */
class notes {

    public static void main(String[] args) {
        // �ڳ������д���һ������
        String s = "abc"; // ��������
        String s1 = "abc";// ���ö���

        // ʹ�ù��캯����������������һ��new��һ���ַ��������ڶ��ڴ���
        String s2 = new String("abc");
        // ��s���ַ�����أ��ٷ���s9
        String s9 = s2.intern();

        System.out.println(s9 == s);
        System.out.println(s == s1); // �������е�ͬһ�ַ���ֻ��һ����ָ��ĵ�ַ��ͬ
        System.out.println(s == s2); // ��ַ��һ��
        System.out.println(s.equals(s2)); // String����д��equals���������Ƚ��ַ�����ֵ

        // String���캯�� ���Խ��������ַ���
        byte[] arr = {97, 66, 67, 68};
        String s3 = new String(arr);
        System.out.println("s3= " + s3);

        char[] arr1 = {'w', 'a', 'b', 'c', 'd'};
        String s4 = new String(arr1);
        // �������һ���֣�����ַ�����
        String s5 = new String(arr1, 1, 3);
        System.out.println("s4= " + s4);
        System.out.println("s5= " + s5);

        System.out.println("s4.length= " + s4.length());
        System.out.println("s4���ĸ��ַ�Ϊ�� " + s4.charAt(3));
        System.out.println("s4��'a'�����" + s4.indexOf('a'));
        System.out.println("s��ab���ֵ�λ�ã�" + s.indexOf("ab"));
        System.out.println("s4��'a'�����" + s4.lastIndexOf('a'));
        // indexOf ���Ϊ-1��ʱ��˵�������ҵ��ַ�������
        System.out.println("s4���Ƿ���'k'�ַ�: " + s4.indexOf('k'));

        // �и��ַ�����
        String s6 = "����,����,ţ��";
        String[] arr3 = s6.split(",");
        for (int i = 0; i < arr3.length; i++) {
            System.out.println(arr3[i]);
        }
        // �ַ���ת��Ϊ�ַ����飺
        char[] arr4 = s6.toCharArray();
        for (int j = 0; j < arr4.length; j++) {
            System.out.println(arr4[j]);
        }

        // ���ַ�����Ϊbyte���� ���浽byte�����У�ASSIC���ʾ��
        String s7 = "ab��";
        byte[] b1 = s7.getBytes();
        for (int i = 0; i < b1.length; i++) {
            System.out.print(b1[i] + ",");
        }
        System.out.println("\r"); // ���У��൱�ڼ��� Enter
        // ת����Сд
        System.out.println(s.toUpperCase());

        // �������˿ո�
        String s8 = "    -   a d   -      ";
        System.out.println(s8);
        System.out.println(s8.trim());
        // �ַ�������
        System.out.println(s.concat(s4));
        // String.valueOf
        System.out.println(String.valueOf(4) + 1);// �����ַ���������

        // �ж��ַ����Ƿ���ָ���ַ�����ͷ���Ƿ���ָ���ַ�����β
        String str = "Arraydemo.java";
        System.out.println(str.startsWith("Array")); // ����boolean��
        System.out.println(str.endsWith(".java"));
        System.out.println(str.contains("demo"));
        // �Ƚϣ�
        System.out.println("abc".compareTo("defg"));// ����int���ͣ��Ƚ�ASSIC��

    }
}