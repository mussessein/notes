package string;

/**
 * �ж�һ���Ӵ����ֵĴ��� 1. ���ж��Ӵ��Ƿ���ڣ�������ڻ�ȡλ�� indexOf() 2. ��¼λ��֮�� ��ʣ����Ӵ��м������� 3.
 *
 * <p>ѭ�����ң���¼λ����Ϣ
 */
class String_test_2 {
  public static void main(String[] args) {
    String str = "nbauynnbauimnanbajij";
    String key = "nba";
    // ����һ ��������ʹ�ã�
    long startTime1 = System.currentTimeMillis();
    int count_1 = getKeyCounnt_1(str, key);
    System.out.println(key + "  ���ֵĴ��� ��" + count_1);
    long endTime1 = System.currentTimeMillis();
    // ������
    long startTime2 = System.currentTimeMillis();
    int count_2 = getKeyCounnt_2(str, key);
    System.out.println(key + "  ���ֵĴ��� ��" + count_2);
    long endTime2 = System.currentTimeMillis();
    System.out.println("����һִ��ʱ�䣺" + (endTime1 - startTime1));
    System.out.println("������ִ��ʱ�䣺" + (endTime2 - startTime2));
  }

  public static int getKeyCounnt_1(String str, String key) {
    int count = 0;
    int index;

    // ÿ���ҵ��Ӵ���ȡ��,����ɲ�ͣ���ڳ������д����µ��ַ������������
    while ((index = str.indexOf(key)) != -1) {
      // ��ȡ����һ��nba��ʣ��uynnbauimnanbajij����str
      str = str.substring(index + key.length());
      count++;
    }
    return count;
  }

  public static int getKeyCounnt_2(String str, String key) {
    int count = 0;
    int index = 0;

    // �ı�ÿ���жϵ���ʼλ�ã����������µ��ַ�������index��ʼ����key
    while ((index = str.indexOf(key, index)) != -1) {
      index = index + key.length();
      count++;
    }

    return count;
  }
}
