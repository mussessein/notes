package Lock.ReentrantLock_Demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
ReentrantLock ��synchronized���ܸ��࣬ͬ��ʵ��ͬ��

                ʹ��Condition�������߳�ͨ��
                await()
                signal()
                signalAll()
ʵ�ֶ�ͬһ���˻����� ȡǮ����
 */
public class Account {
    //��ʽ����Lock����
    private final Lock lock = new ReentrantLock();
    //���ָ��LOCK�����Ӧ��Condition
    private final Condition cond = lock.newCondition();

    //��װ�˻��ͽ��
    private String account_number;
    private double balance;
    //�����Ƿ��д��ı�־λ
    private boolean flag = false;

    public Account() {
    }

    public Account(String account_number, double balance) {
        this.account_number = account_number;
        this.balance = balance;
    }

    //����GETTER,SETTER����,����balanceֻ����get��������set����֤�ڷ������޷����������޸�balance
    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public double getBalance() {
        return balance;
    }

    //ȡǮ����
    public void draw(double drawAmount) {
        //�������൱��ͬ��
        lock.lock();
        try {
            //flagλfalse����ʾ��û���˴�Ǯ������ȡǮ
            if (!flag) {
                cond.await();
            } else { //�Ѿ���Ǯ����ʼȡǮ
                System.out.println(Thread.currentThread().getName() + "ȡǮ�ɹ���ȡ����" + drawAmount);
                balance -= drawAmount;
                System.out.println("���Ϊ��" + balance);
                flag = false;
                //���������߳�
                cond.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //���ս�������
            lock.unlock();
        }
    }

    //��Ǯ����
    public void deposit(double depositAmount) {
        //�������൱��ͬ��
        lock.lock();
        try {
            //flagλfalse����ʾ��û����ȡǮ�����ܴ�Ǯ
            if (flag) {
                cond.await();
            } else { //�Ѿ�ȡǮ����ʼ��Ǯ
                System.out.println(Thread.currentThread().getName() + "��Ǯ�ɹ��������" + depositAmount);
                balance += depositAmount;
                System.out.println("���Ϊ��" + balance);
                flag = true;
                //���������߳�
                cond.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //���ս�������
            lock.unlock();
        }
    }
}
