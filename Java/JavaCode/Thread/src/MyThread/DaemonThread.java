package MyThread;

/*
Daemon Thread �ػ��̣߳���̨�̣߳�
    1. �ں�̨���У���Ϊ�������߳��ṩ����
    2. ������������е��̶߳������ˣ���̨�߳��Զ�����


 */
public class DaemonThread extends Thread {

    DaemonThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(getName() + i);
        }
    }

    public static void main(String[] args) {

        DaemonThread dt = new DaemonThread("�����߳�");
        //ģ���̨�̣߳���dt����Ϊ�ػ��߳�
        dt.setDaemon(true);
        dt.start();
        //�����߳̽������ػ��߳��Զ�����
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }

    }
}
