package Net.Chat;

import java.io.*;
import java.net.Socket;

/*
java.net.Socket
Socket 类 封装了TCP协议（相当于电话）
1.使用Socket对象，就可以基于TCP协议，进行网络通讯
2.Socket是运行在客户端的，需要 new
3.IP决定哪台电脑，端口决定哪个程序
4.客户端，永远主动发起连接
 */
public class Client implements Runnable{



    BufferedReader br1;

    //初始化客户端的输入流，用于接受信息
    public Client(Socket socket) throws IOException {

        //br1=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    @Override
    public void run() {
//        try{
//            // 此run方法没卵用
//            String line=null;
//            while ((line=br1.readLine())!=null){
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    //先启动服务端，服务端会一直处于等待状态
    public static void main(String[] args) throws IOException {

        Socket socket=new Socket("localhost",9999);
        //new Thread(new Client(socket)).start();
        PrintStream ps=new PrintStream(socket.getOutputStream());
        String line;
        // 发送信息
        BufferedReader br2=new BufferedReader(new InputStreamReader(System.in));
        while ((line=br2.readLine())!=null){
            ps.println(line);
        }
    }
}
