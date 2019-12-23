package IO.BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 用于处理一个客户端的线程
 */
public class ServerHandler implements Runnable {

    // 这个线程需要传入一个Socket对象
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 不需要输出流,直接打印到屏幕的
            //out = new PrintWriter(socket.getOutputStream(), true);
            String expression;
            while (true) {
                /*
                1.判断是否读取完毕,读取完毕则break
                2.打印消息
                 */
                if ((expression = in.readLine()) == null) break;
                System.out.println("Server catched Message : " + expression);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    in = null;
                }
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
