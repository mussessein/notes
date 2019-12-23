package IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Redirect_Out {

    public static void main(String[] args) {

        try(
                PrintStream ps = new PrintStream(new FileOutputStream("./Notes/src/main/java/IO/newFile.txt"));
                )
        {
            /**
             * 将标准输出重定向为ps处理流
             * 之后打印的信息，不会打印到控制台
             * 而是打印重定向之后的处理流中，再写入文件
             */
            System.setOut(ps);
            System.out.println("重定向标准输出流");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
