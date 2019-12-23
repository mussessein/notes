package IO.BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ͬ������ʽI/Oģ��:
 * ��һ��������Acceptor�̸߳�������ͻ��˵����ӣ�
 * �����յ��ͻ�����������֮��Ϊÿ���ͻ��˴���һ���µ��߳̽�����·����û������ɺ�
 * ͨ�����������Ӧ����ͻ��ˣ��߳����١�
 * �����͵�һ����һӦ��ͨ��ģ�͡�
 * �߳���������,�����½�
 *
 * α�첽IO:ֻ��������̳߳�
 *
 */
public class Server {
    // ����һ��Ĭ�϶˿ں�
    private static int DEFUALT_PORT=9999;
    // ����Server
    private static ServerSocket serverSocket;
    //α�첽IO:�̳߳� ����ʽ�ĵ���
    private static ExecutorService executorService = Executors.newFixedThreadPool(60);
    // Ϊ��������� ����start����
    public static void start() throws IOException {
        start(DEFUALT_PORT);
    }
    public synchronized static void start(int port) throws IOException {
        // ��ֻ֤����һ�������,�Ѵ�����ֱ�ӷ���
        if (serverSocket!=null) return;
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server started : "+port);
            // �����ͻ��� ����accept
            while(true){
                Socket socket = serverSocket.accept();
                // ����ServerHandler�߳� �����socket
                //new Thread(new ServerHandler(socket)).start();
                // α�첽IO���̳߳�
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
