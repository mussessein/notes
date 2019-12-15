package IO.BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ���ڴ���һ���ͻ��˵��߳�
 */
public class ServerHandler implements Runnable {

    // ����߳���Ҫ����һ��Socket����
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
            // ����Ҫ�����,ֱ�Ӵ�ӡ����Ļ��
            //out = new PrintWriter(socket.getOutputStream(), true);
            String expression;
            while (true) {
                /*
                1.�ж��Ƿ��ȡ���,��ȡ�����break
                2.��ӡ��Ϣ
                 */
                if ((expression = in.readLine()) == null) break;
                System.out.println("Server catched Message : " + expression);
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
}
