package Lock.ReentrantLock_Demo;

/*
�˴��뷢���������󣡣���������������
������������
������һ���߳�ִ�н������ȴ������߳�ִ�У�
������һ���߳�ִ�н������ȴ������߳��ͷ�ͬ����������

ȡ��Ǯ���ͷ�����Ȼ������ȡǮ�߳��õ����������˵ȴ������ѣ���Ǯ�߳����ò��������޷�����
 */
public class demo {

    public static void main(String[] args){
        //����һ���˻�
        Account account=new Account("8888888",0);
        //������ȡ�߳�
        new DepositThread("����",account,1000).start();
        new DrawThread("����",account,1000).start();
    }
}
