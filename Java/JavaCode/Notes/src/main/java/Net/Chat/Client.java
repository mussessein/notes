package Net.Chat;

import java.io.*;
import java.net.Socket;

/*
java.net.Socket
Socket �� ��װ��TCPЭ�飨�൱�ڵ绰��
1.ʹ��Socket���󣬾Ϳ��Ի���TCPЭ�飬��������ͨѶ
2.Socket�������ڿͻ��˵ģ���Ҫ new
3.IP������̨���ԣ��˿ھ����ĸ�����
4.�ͻ��ˣ���Զ������������
 */
public class Client implements Runnable{



    BufferedReader br1;

    //��ʼ���ͻ��˵������������ڽ�����Ϣ
    public Client(Socket socket) throws IOException {

        //br1=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    @Override
    public void run() {
//        try{
//            // ��run����û����
//            String line=null;
//            while ((line=br1.readLine())!=null){
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    //����������ˣ�����˻�һֱ���ڵȴ�״̬
    public static void main(String[] args) throws IOException {

        Socket socket=new Socket("localhost",9999);
        //new Thread(new Client(socket)).start();
        PrintStream ps=new PrintStream(socket.getOutputStream());
        String line;
        // ������Ϣ
        BufferedReader br2=new BufferedReader(new InputStreamReader(System.in));
        while ((line=br2.readLine())!=null){
            ps.println(line);
        }
    }
}
