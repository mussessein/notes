package IO.BIO;

import java.io.*;
import java.net.Socket;

/**
 * 同步阻塞式I/O创建的Client
 */
public class Client {

    private static int DEFUALT_PORT = 9999;
    private static String DEFUALT_IP = "localhost";

    public static void send() {
        send(DEFUALT_PORT);
    }

    public static void send(int port) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            /*
            1.创建Socket
            2.将键盘输出定向到输入流
            3.输出流打印给socket,传送到服务端
             */
            socket = new Socket(DEFUALT_IP, port);
            in = new BufferedReader(new InputStreamReader(System.in));
            String expression;
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            while ((expression = in.readLine()) != null) {
                out.println(expression);
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

    public static void main(String[] args) throws IOException {

        send();
        Socket socket = new Socket("localhost", 9999);
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
//        String expression;
//        while ((expression=in.readLine())!=null){
//            out.println(expression);
//        }
    }


}
