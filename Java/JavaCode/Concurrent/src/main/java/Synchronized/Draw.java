package Synchronized;
/*
ȡǮ���߳�
 */

public class Draw extends Thread {
    //ÿ�ν���ȡǮ������Ҫһ���˻�
    private Account account;
    private double drawAmount;
    public Draw(String name,Account account,double drawAmount){
        //�̳��߳����ַ���
        super(name);
        this.account=account;
        this.drawAmount=drawAmount;
    }
    //ȡǮ�������̳�Thread�࣬������дrun�����߳�ִ����
    //����ֱ�Ӷ�run��������ͬ���������������������޷�ʵ��ͬ��������������
    //������ʵ��ͬ�������
    @Override
    public void run() {
        account.draw(drawAmount);
    }

    public static void main(String[] args){
     //����һ���˻�
        Account account=new Account("88888888",100000.0);
        //���������̣߳���ͬһ���˻�����ȡǮ
        new Draw("����",account,90000.0).start();
        new Draw("����",account,90000.0).start();
    }
}
