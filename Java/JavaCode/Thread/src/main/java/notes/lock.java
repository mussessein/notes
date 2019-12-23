/**
 * ��ͼ�⣩
 * �ӿ� lock ���� synchronizationͬ�������
 * ����ʽ�Զ���synchronization,����Ϊ��ʽ�ɿص�lock
 * ʵ����䣺(LockΪ�ӿڣ�ReentrantLock��Lock��ʵ����)
 * synchronized L1 = new ReentrantLock();
 * ���ϣ� L1.lock();
 * try{
 * code...
 * }
 * catch( ){ }
 * finally{
 * ������    L1.unlock();
 * }
 * <p>
 * Condition�ӿڷ��� ���� Object�����ļ���������
 * Condition C1 = L1.newCondition();  ֱ��ָ���Ӧ����
 * �ȴ����ѷ���Ҳͬ���滻��
 * await();
 * singal();
 * singalAll();
 * <p>
 * �¾�����synchronizated(obj) ��������ֻ��һ���������������̶߳������ڴ˼�����
 * lock/Condition ��һ�����ϣ�����ӵ�ж����������
 * �������ڲ�ͬ�Ķ���������¾Ϳ��Եȴ����Ѷ�Ӧ���̡߳�
 * ���������£� ����̸߳��ݲ�ͬ�Ķ�������� ʵ������ȷ��
 */

//����Ϊjava API ����Condition�Ĵ���ʵ����
package notes;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBuffer {
    //����һ����
    final Lock lock = new ReentrantLock();
    //�����´�������������
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    //����һ�����飬ʵ�ַ�����ó������߳�
    final Object[] items = new Object[100];
    int putptr, takeptr, count;


    public void put(Object x) throws InterruptedException {     //�ڴ��׳��쳣���������catch
//���� 
        lock.lock();
        try {
            while (count == items.length)
//����״̬ʱ����notFull�������ȴ�
                notFull.await();
            items[putptr] = x;    //δ��״̬�������
            if (++putptr == items.length) putptr = 0; //�����ʱ�����Ѿ�ָ����״̬����ͷ��ʼ����
            ++count;
//�����ó�������
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
//���� 
        lock.lock();
        try {
            while (count == 0)
//����״̬ʱ����notEmpty�������ȴ�
                notEmpty.await();
            Object x = items[takeptr];  //δ��״̬����ȡ
            if (++takeptr == items.length) takeptr = 0; //�����ʱ�����Ѿ�ָ����״̬����ͷ��ʼ�ó�
            --count;
//���ѷ��������
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
 