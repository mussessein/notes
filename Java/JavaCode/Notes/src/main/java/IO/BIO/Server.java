package IO.BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 同步阻塞式I/O模型:
 * 由一个独立的Acceptor线程负责监听客户端的连接，
 * 它接收到客户端连接请求之后为每个客户端创建一个新的线程进行链路处理没处理完成后，
 * 通过输出流返回应答给客户端，线程销毁。
 * 即典型的一请求一应答通宵模型。
 * 线程数量增大,性能下降
 *
 * 伪异步IO:只是添加了线程池
 *
 */
public class Server {
    // 设置一个默认端口号
    private static int DEFUALT_PORT=9999;
    // 单例Server
    private static ServerSocket serverSocket;
    //伪异步IO:线程池 懒汉式的单例
    private static ExecutorService executorService = Executors.newFixedThreadPool(60);
    // 为启动服务端 创建start方法
    public static void start() throws IOException {
        start(DEFUALT_PORT);
    }
    public synchronized static void start(int port) throws IOException {
        // 保证只存在一个服务端,已存在则直接返回
        if (serverSocket!=null) return;
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server started : "+port);
            // 监听客户端 阻塞accept
            while(true){
                Socket socket = serverSocket.accept();
                // 创建ServerHandler线程 处理此socket
                //new Thread(new ServerHandler(socket)).start();
                // 伪异步IO的线程池
                executorService.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket!=null){
                System.out.println("Server Shutdown !");
                serverSocket.close();
                serverSocket=null;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        new Server().start();
    }

}
