package text;

import java.util.*;

/**cd �ҵ�src·����
 * ��Ҫ�ն����У�javac text.demo_Scanner
 */
public class demo_Scanner {

   public static void main(String[] args) {
       //System.in�����׼���루�������룩
        Scanner sc = new Scanner(System.in);
        //�� �س� ��Ϊ�ָ���
        sc.useDelimiter("\n");
        while (sc.hasNext()) {
            // ���������
            System.out.println("�Ӽ�������������ǣ�" + sc.nextLine());
            if (sc.next().equals("quit")){
                System.out.println("Bye:)");
                break;
            }
        }
        sc.close();
   } 
}