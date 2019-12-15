package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Redirect_In {

    public static void main(String[] args) {

        try (
                FileInputStream fis = new FileInputStream("./Notes/src/main/java/IO/demo_File.java");
                )
        {   /*
            ����׼�����ض���fis������
            ���ٽ��ܼ��̵����룬���ǽ���fis�����������룬����ӡfis������
            */
            System.setIn(fis);
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");
            while(sc.hasNext()){
                System.out.println(sc.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
