package Net.Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
运行在服务端的ServerSocket
1.向系统申请服务端口，客户端就是通过此端口进行链接
2.监听申请的服务端口，
  当客户端通过该端口尝试链接，ServerSocket会在服务端创建一个Socket与客户端建立链接
3.accept()方法：阻塞方法，用于监听服务端口，直到一个客户端链接并创建一个socket
                如果没有接到客户端请求，会一直等待，处于卡住状态
4.启动之后，会一直处于等待状态，等待客户端的连接
5.底层为IO流
 */
public class Server implements Runnable{

    //定义一个List用于保存多个客户端Socket，并包装为线程安全
    public static List<Socket> socketList=Collections.synchronizedList(new ArrayList<>());
    //定义一个socket输入处理流
    BufferedReader br=null;

    //初始化传入的socket对象
    public Server(Socket socket) throws IOException {

        br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    @Override
    public void run() {
        try{
            String line=null;
            //不断的接收客户端socket传来的信息
            while ((line=br.readLine())!=null){
                //遍历socket集合中的socket，将从每一个socket读取到的line，写入输出流，即发送
                for (Socket socket:socketList){
                    PrintStream ps=new PrintStream(socket.getOutputStream());
                    ps.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("断开连接");
        }
    }

    //此server用于转发各个Client的消息，实现多个Client交流
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(8888);
        //先启动服务端，服务端会一直处于等待状态
        while (true){
            //等待连接
            Socket socket =server.accept();
            System.out.println("连接成功");
            socketList.add(socket);
            new Thread(new Server(socket)).start();
        }
    }
}
