package MyThread;

public class Thread_Data_Shared extends Thread {

    private volatile int count = 50;

    public Thread_Data_Shared() {
    }

    public Thread_Data_Shared(String name) {

        super();
        this.setName(name);
    }

    /*
    �������synchronized �ؼ���,����ʵ���߳��Ŷ�ִ��run����
    �����߳�ִ�����,�Ż��ͷ���
    �����Ĵ����Ϊ ������
     */
    @Override
    public void run() {
        super.run();
        while (count > 0) {
            count--;
            System.out.println(this.currentThread().getName() + " ����count: " + count);
        }
    }

    public static void main(String[] args) {

        // �����߳����Լ���countֵ,ÿ���߳������Լ���ֵ

        System.out.println("==========���ݲ�����==========");
        Thread_Data_Shared a = new Thread_Data_Shared("A");
        Thread_Data_Shared b = new Thread_Data_Shared("B");
        Thread_Data_Shared c = new Thread_Data_Shared("C");
        a.start();
        b.start();
        c.start();

        // �����̹߳���һ��countֵ

/*        System.out.println("==========���ݹ���==========");
        Thread_Data_Shared threadShared =new Thread_Data_Shared();
        Thread a1=new Thread(threadShared,"A1");
        Thread b1=new Thread(threadShared,"B1");
        Thread c1=new Thread(threadShared,"C1");
        a1.start();
        b1.start();
        c1.start();*/

    }

}

