package IO.BIO;

import java.io.*;
import java.net.Socket;

/**
 * ͬ������ʽI/O������Client
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
            1.����Socket
            2.�������������������
            3.�������ӡ��socket,���͵������
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
            // �ر���
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
