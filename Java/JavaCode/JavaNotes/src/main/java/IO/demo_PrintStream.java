package IO;
import java.io.*;
/*
          实现输入，打印（转换流）

     父类      OutputStream
              FileOutputStream
      子类     PrintStream
 */
public class demo_PrintStream {

    public static void main(String[] args) throws IOException {

        try(
                //将System.in对象转换为Reader对象
                InputStreamReader isRead=new InputStreamReader(System.in);
                //普通Reader包装为缓冲区对象，字节流转换为字符流
                BufferedReader br=new BufferedReader(isRead);
                )
        {
            String line=null;
            //循环读取
            while((line=br.readLine())!=null){
                //如果读到quit，则退出
                if(line.equals("quit")){
                    System.out.println("Bye :)");
                    System.exit(1);
                }
                System.out.println("输入的内容为： "+line);
            }
        }catch (IOException e){e.printStackTrace();}
    }

}
