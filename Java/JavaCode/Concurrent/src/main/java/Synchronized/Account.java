package Synchronized;
/*
��ͳ�߳�ͨ�ŵķ�ʽ��synchronized���η����������׳����߳�����

ʵ�ֶ�ͬһ���˻����� ȡǮ����

 */
public class Account {
    //��װ�˻��ͽ��
    private String account_number;
    private double balance;
    //�����Ƿ��д��ı�־λ
    private boolean flag=false;

    public Account(String account_number,double balance){
        this.account_number=account_number;
        this.balance=balance;
    }
    //����GETTER,SETTER����
    public String getAccount_number() {
        return account_number;
    }
    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }
    public double getBalance() {
        return balance;
    }

    //ͬ���µ�ȡǮ����
    public synchronized void draw(double drawAmount){

        //�жϣ����������ȡǮ�������ִ��ȡǮ
        if (balance>=drawAmount){
            System.out.println(Thread.currentThread().getName()+"ȡǮ�ɹ���ȡ����"+drawAmount);

            try{
                Thread.sleep(1);
            }catch (InterruptedException e){e.printStackTrace();}
            //�޸��˻����
            balance-=drawAmount;
            System.out.println("���Ϊ��"+balance);
        }
        else{
            System.out.println("���㣬ȡǮʧ��");
        }
    }
}
