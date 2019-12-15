package Lock.ReentrantLock_Demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
ReentrantLock 比synchronized功能更多，同样实现同步

                使用Condition来控制线程通信
                await()
                signal()
                signalAll()
实现对同一个账户进行 取钱操作
 */
public class Account {
    //显式创建Lock对象
    private final Lock lock = new ReentrantLock();
    //获得指定LOCK对象对应的Condition
    private final Condition cond = lock.newCondition();

    //封装账户和金额
    private String account_number;
    private double balance;
    //设置是否有存款的标志位
    private boolean flag = false;

    public Account() {
    }

    public Account(String account_number, double balance) {
        this.account_number = account_number;
        this.balance = balance;
    }

    //设置GETTER,SETTER方法,对于balance只设置get，不设置set，保证在方法外无法单独进行修改balance
    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public double getBalance() {
        return balance;
    }

    //取钱方法
    public void draw(double drawAmount) {
        //加锁，相当于同步
        lock.lock();
        try {
            //flag位false，表示还没有人存钱，则不能取钱
            if (!flag) {
                cond.await();
            } else { //已经存钱，开始取钱
                System.out.println(Thread.currentThread().getName() + "取钱成功，取出：" + drawAmount);
                balance -= drawAmount;
                System.out.println("余额为：" + balance);
                flag = false;
                //唤醒其他线程
                cond.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //最终解锁！！
            lock.unlock();
        }
    }

    //存钱方法
    public void deposit(double depositAmount) {
        //加锁，相当于同步
        lock.lock();
        try {
            //flag位false，表示还没有人取钱，则不能存钱
            if (flag) {
                cond.await();
            } else { //已经取钱，开始存钱
                System.out.println(Thread.currentThread().getName() + "存钱成功，存出：" + depositAmount);
                balance += depositAmount;
                System.out.println("余额为：" + balance);
                flag = true;
                //唤醒其他线程
                cond.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //最终解锁！！
            lock.unlock();
        }
    }
}
