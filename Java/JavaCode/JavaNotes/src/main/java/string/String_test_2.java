package string;

/**
 * 判断一个子串出现的次数 1. 先判断子串是否存在，如果存在获取位置 indexOf() 2. 记录位置之后 在剩余的子串中继续查找 3.
 *
 * <p>循环查找，记录位置信息
 */
class String_test_2 {
  public static void main(String[] args) {
    String str = "nbauynnbauimnanbajij";
    String key = "nba";
    // 方法一 （不建议使用）
    long startTime1 = System.currentTimeMillis();
    int count_1 = getKeyCounnt_1(str, key);
    System.out.println(key + "  出现的次数 ：" + count_1);
    long endTime1 = System.currentTimeMillis();
    // 方法二
    long startTime2 = System.currentTimeMillis();
    int count_2 = getKeyCounnt_2(str, key);
    System.out.println(key + "  出现的次数 ：" + count_2);
    long endTime2 = System.currentTimeMillis();
    System.out.println("方法一执行时间：" + (endTime1 - startTime1));
    System.out.println("方法二执行时间：" + (endTime2 - startTime2));
  }

  public static int getKeyCounnt_1(String str, String key) {
    int count = 0;
    int index;

    // 每次找到子串截取掉,会造成不停的在常量池中创建新的字符串，造成垃圾
    while ((index = str.indexOf(key)) != -1) {
      // 截取掉第一个nba，剩余uynnbauimnanbajij传入str
      str = str.substring(index + key.length());
      count++;
    }
    return count;
  }

  public static int getKeyCounnt_2(String str, String key) {
    int count = 0;
    int index = 0;

    // 改变每次判断的起始位置，不会生成新的字符串，从index开始查找key
    while ((index = str.indexOf(key, index)) != -1) {
      index = index + key.length();
      count++;
    }

    return count;
  }
}
