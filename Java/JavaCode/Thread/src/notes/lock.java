/**
 * （图解）
 * 接口 lock 代替 synchronization同步代码块
 * 将隐式自动的synchronization,更改为显式可控的lock
 * 实现语句：(Lock为接口，ReentrantLock是Lock的实现类)
 * synchronized L1 = new ReentrantLock();
 * 锁上： L1.lock();
 * try{
 * code...
 * }
 * catch( ){ }
 * finally{
 * 解锁：    L1.unlock();
 * }
 * <p>
 * Condition接口方法 代替 Object创建的监视器对象
 * Condition C1 = L1.newCondition();  直接指向对应的锁
 * 等待唤醒方法也同样替换：
 * await();
 * singal();
 * singalAll();
 * <p>
 * 新旧区别：synchronizated(obj) 这种锁，只有一个监视器，所有线程都依赖于此监视器
 * lock/Condition 在一个锁上，可以拥有多个监视器。
 * 这样，在不同的对象监视器下就可以等待唤醒对应的线程。
 * 在这种锁下， 多个线程根据不同的对象监视器 实现了明确。
 */

//以下为java API 解释Condition的代码实例：
package notes;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBuffer {
    //创建一个锁
    final Lock lock = new ReentrantLock();
    //在锁下创建两个监视器
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    //创建一个数组，实现放入和拿出两个线程
    final Object[] items = new Object[100];
    int putptr, takeptr, count;


    public void put(Object x) throws InterruptedException {     //在此抛出异常，无需加入catch
//上锁 
        lock.lock();
        try {
            while (count == items.length)
//当满状态时，将notFull监视器等待
                notFull.await();
            items[putptr] = x;    //未满状态，则放入
            if (++putptr == items.length) putptr = 0; //如果此时索引已经指向满状态，从头开始放入
            ++count;
//唤醒拿出监视器
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
//上锁 
        lock.lock();
        try {
            while (count == 0)
//当空状态时，将notEmpty监视器等待
                notEmpty.await();
            Object x = items[takeptr];  //未空状态则提取
            if (++takeptr == items.length) takeptr = 0; //如果此时索引已经指向满状态，从头开始拿出
            --count;
//唤醒放入监视器
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
 