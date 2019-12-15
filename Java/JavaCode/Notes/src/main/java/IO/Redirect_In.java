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
            将标准输入重定向到fis输入流
            不再接受键盘的输入，而是接受fis输入流的输入，即打印fis的数据
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
