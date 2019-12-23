package text;

import java.util.*;

/**cd 找到src路径，
 * 需要终端运行，javac text.demo_Scanner
 */
public class demo_Scanner {

   public static void main(String[] args) {
       //System.in代表标准输入（键盘输入）
        Scanner sc = new Scanner(System.in);
        //将 回车 作为分隔符
        sc.useDelimiter("\n");
        while (sc.hasNext()) {
            // 输出输入项
            System.out.println("从键盘输入的内容是：" + sc.nextLine());
            if (sc.next().equals("quit")){
                System.out.println("Bye:)");
                break;
            }
        }
        sc.close();
   } 
}