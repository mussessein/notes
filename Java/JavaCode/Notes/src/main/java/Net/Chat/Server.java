package Net.Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
�����ڷ���˵�ServerSocket
1.��ϵͳ�������˿ڣ��ͻ��˾���ͨ���˶˿ڽ�������
2.��������ķ���˿ڣ�
  ���ͻ���ͨ���ö˿ڳ������ӣ�ServerSocket���ڷ���˴���һ��Socket��ͻ��˽�������
3.accept()�������������������ڼ�������˿ڣ�ֱ��һ���ͻ������Ӳ�����һ��socket
                ���û�нӵ��ͻ������󣬻�һֱ�ȴ������ڿ�ס״̬
4.����֮�󣬻�һֱ���ڵȴ�״̬���ȴ��ͻ��˵�����
5.�ײ�ΪIO��
 */
public class Server implements Runnable{

    //����һ��List���ڱ������ͻ���Socket������װΪ�̰߳�ȫ
    public static List<Socket> socketList=Collections.synchronizedList(new ArrayList<>());
    //����һ��socket���봦����
    BufferedReader br=null;

    //��ʼ�������socket����
    public Server(Socket socket) throws IOException {

        br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    @Override
    public void run() {
        try{
            String line=null;
            //���ϵĽ��տͻ���socket��������Ϣ
            while ((line=br.readLine())!=null){
                //����socket�����е�socket������ÿһ��socket��ȡ����line��д���������������
                for (Socket socket:socketList){
                    PrintStream ps=new PrintStream(socket.getOutputStream());
                    ps.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("�Ͽ�����");
        }
    }

    //��server����ת������Client����Ϣ��ʵ�ֶ��Client����
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(8888);
        //����������ˣ�����˻�һֱ���ڵȴ�״̬
        while (true){
            //�ȴ�����
            Socket socket =server.accept();
            System.out.println("���ӳɹ�");
            socketList.add(socket);
            new Thread(new Server(socket)).start();
        }
    }
}
