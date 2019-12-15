# JUC并发

参考：

<https://github.com/doGdx/JavaGuide#%E5%B9%B6%E5%8F%91>

概述 java.util.concurrent包的主要类：（分三部分），最后介绍一下AQS

#### java.util.concurrent：

BlockingQueue接口（阻塞队列）：详见Java多线程.md、消费者生产者设计模式

​	|－ArrayBlockingQueue

​	|－LinkedBlockingQueue

​	|－PriorityBlockingQueue

​	|－DelayQueue

Executor（顶层接口）：只有一个方法：executor（）

​	|－ExecutorService（Executor的子接口，进行了增强）

​			|－ThreadPoolExecutor（实现类）

Executors类：返回ExecutorService实例

Callable接口

Future接口

​	|－FutureTask（实现类）

**同步器**：

​	｜－CountDownLatch类（计数器）

​	｜－Semaphore类（信号量）

​	｜－CyclicBarrier（回环栅栏）

**容器类**：

ConcurrentHashMap
ConcurrentMap
CopyOnWriteArrayList
ConcurrentLinkedQueue

**AQS**

#### java.util.concurrent.Locks：

Lock接口：

​	｜－ReentrantLock

Condition接口：

​	｜－Contition

ReadWriteLock接口：

​	｜－ReentrantReadWriteLock

#### java.util.concurrent.atomic:(原子类)

基本类型

数组类型

引用类型

对象属性修改类型

#### AQS（AbstractQueuedSynchronizer）

构建锁和同步器的框架

---

#### java.util.concurrent：

### 阻塞队列

Java提供了一个BlockingQueue接口，是Queue接口的子接口。

对于消费者生产者场景：

- **当生产者线程试图向BlockingQueue队列中放入元素，如果队列已满，则该线程阻塞**
- **当消费者线程试图从BlockingQueue队列中取出元素，如果队列为空，则该线程阻塞**

BlockingQueue的子接口：(BlockingQueue是Queue的子接口)

- ArrayBlockingQueue：基于数组，有界阻塞队列，大小一旦初始化完成，无法修改，类似于数组大小无法修改。
- LinkedBlockingQueue：基于链表，是一个可选有界队列，不允许null值。可以设置上线，如果不设置，则默认Integer.MAX_VALUE，**容易内存耗尽！！**
- PriorityBlockingQueue：排序队列，所有元素，实现Comparable接口。取出元素根据元素排序方式，CompareTo方法的实现可以自定义。是一个无界队列，不允许null值，入队出队时间复杂度O（logn）。
- DelayQueue：延迟队列，

非阻塞方法：（抛出异常）

- add（E e）：队尾插入元素，**已满抛出异常**
- remove（）：队首删除元素，**已空抛出异常**
- offer（E e）：队尾插入元素，**已满返回false**
- poll（）：队首删除元素，**已空返回false**
- peek（）

阻塞方法：（不会抛出异常，阻塞）

- put（E e）：队尾插入元素，**已满阻塞。**
- take（）：队首删除元素，**已空阻塞**

```java
// 定义一个长度为2的阻塞队列
BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
bq.put("a");
bq.put("b");
bq.put("c");	// 阻塞
```

### Callable和Future

创建线程有两种方式：继承Thread，实现Runnable。

这两种方式特点：线程执行完任务，调用者无法获取结果。（ run方法是没有返回结果的 ）

还有一种创建线程的方式：实现Callable接口。并通过Future类，可以获得执行结果。

#### Callable接口

先看一下Runnable接口：一个run方法，void类型。

```java
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
```

只有一个call方法，相当于一个有泛型返回值的run方法。

```java
@FunctionalInterface
public interface Callable<V> {
    V call() throws Exception;
}
```

#### Future接口

```java
public interface Future<V> {
    // 取消任务
    boolean cancel(boolean mayInterruptIfRunning);
    boolean isCancelled();
    boolean isDone();
    // 获取任务执行结果,是一个阻塞方法,一直阻塞,直到返回结果
    V get() throws InterruptedException, ExecutionException;
    // timeout时间内没有获取结果,就返回null
    V get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
}
```

Future类提供的功能:

- 判断任务是否完成
- 能中断任务
- 能获取任务结果

### FutureTask类

**FutureTask是Future接口的唯一的实现类，间接实现了Future接口和Callable接口**

```java
public class FutureTask<V> implements RunnableFuture<V>
public interface RunnableFuture<V> extends Runnable, Future<V>
```

具体这两类有什么区别，看代码实现JavaCode下Concurrent

### Executor接口

```java
public interface Executor {
    void execute(Runnable command);
}
```

**线程池有两个方法可以运行线程：execute和submit**

区别：

1. 参数有区别
2. submit有返回值，可以返回Future对象，比如每个线程返回是否执行成功。execute没有返回值

### Executor类（尽量不使用此方法，而是使用ThreadPoolExecutor类）

Executors 返回的线程池对象的弊端如下：

1. FixedThreadPool 和 SingleThreadPool : 允许的请求队列长度为 Integer.MAX_VALUE ，可能会堆积大量的请求，从而导致 OOM 。
2. CachedThreadPool 和 ScheduledThreadPool : 允许的创建线程数量为 Integer.MAX_VALUE ，可能会创建大量的线程，从而导致 OOM 。

只要用于返回ExecutorService对象

**四种方法都返回一个ExecutorService的实现类ThreadPoolExecutor的实例对象，创建四种不同的线程池。**

```java
public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>());
}
```

- 创建固定数目线程的线程
- 缓存队列：LinkedBlockingQueue：无界阻塞队列，队列最大值为Integer.MAX_VALUE，如果任务提交速度持续大余任务处理速度，会造成队列大量阻塞。

```java
public static ExecutorService newCachedThreadPool() {
     return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                   60L, TimeUnit.SECONDS,
                                   new SynchronousQueue<Runnable>());
}
```

- 创建一个可缓存的线程池
- 缓存队列：SynchronousQueue 同步队列

```java
public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService(new ThreadPoolExecutor
                                                   (1, 1,
                                   					0L, TimeUnit.MILLISECONDS,
                                    			new LinkedBlockingQueue<Runnable>()));
}
```

- 创建一个单线程化的线程池，其中corePoolSize，maximumPoolSize，都为1，即只有一个线程在运行。

```java
public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
    return new ScheduledThreadPoolExecutor(corePoolSize);
}
```

- 创建一个支持定时及周期性的任务执行的线程池

### ExecutorService接口

实现Executor接口并进行增强。

上述的前三种方法，都返回了一个ExecutorService对象，这个对象代表了一个线程池。

```java
public interface ExecutorService extends Executor {
    void execute(Runnable command);  // 这个方法是Executor接口的方法
    void shutdown();
    boolean isShutdown();
    boolean isTerminated();
    boolean awaitTermination(long timeout, TimeUnit unit)
        throws InterruptedException;
	
    <T> Future<T> submit(Callable<T> task);
    <T> Future<T> submit(Runnable task, T result);
    Future<?> submit(Runnable task);
    .....
}
```

- **ExecutorService对象，会尽快执行线程池中的线程，只要有空闲线程，就会立即执行线程任务**

ExecutorService类提供了几个方法：

- Future<?> submit（Runnable task）：将一个Runnable对象提交给指定线程池，线程池有空闲线程就会执		行。返回Future对象，run方法执行结束后，返回null
- Future<?> submit（Runnable task，T result）：在上面方法的基础上，显示指定run方法执行结束后的返回值result
- Future<?> submit（Callable<T> task）：返回结果。

#### 使用线程池的一般步骤：

1. 调用Executors类的静态工厂方法创建一个ExecutorService对象，一个线程池创建成功
2. 创建线程：Runnable实现类、Callable实现类
3. 调用ExecutorService对象的submit方法提交线程
4. shutdown方法关闭线程池。

```java
// 创建一个具有固定线程数6的线程池
ExecutorService pool= Executors.newFixedThreadPool(6);
// 使用lambda创建Runnable对象
Runnable target=()->{
    for (int i=0;i<20;i++){
        System.out.println(Thread.currentThread().getName()+"---i值："+i);
    }
};
// 向线程池提交两个线程
pool.submit(target);
pool.submit(target);
//关闭线程池
pool.shutdown();
```

### ThreadPoolExecutor类（尽量使用此类）

使用此方法，更明确线程池的运行规则，规避资源耗尽的风险

可以认为是ExecutorService的实现类

**先说一下线程池的流程：**

1.  线程先进入核心池运行；
2. 核心池满了，进队列等待；
3. 队列满了，就创建新线程，直到最大线程数满了，之外的线程就被拒绝rejected；

```java
// 最多的七参构造器，前五个参数必须
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,	
                          ThreadFactory threadFactory,	//可以不写
                          RejectedExecutionHandler handler) // 可以不写
```

ThreadPoolExecutor的四种构造器的各项参数：

- **corePoolSize：核心池的大小，并非线程的最大数量**

  - maximumPoolSize > corePoolSize
  - 在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到**缓存队列当中**

- maximumPoolSize：线程池的最大线程数，表示线程池中最多能创建多少个线程

- keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止

  - **默认：只有线程池内线程数大于corePoolSize的线程，keepAliveTime才会对其计时**
  - 当一个线程的空闲时间大于keepAliveTime，则会被终止
  - 如果调用了allowCoreThreadTimeOut(boolean)，线程池内线程数小于corePoolSize，keepAliveTime也会起作用

- unit：参数keepAliveTime的时间单位（七种单位）

  ```java
  TimeUnit.DAYS;              //天
  TimeUnit.HOURS;             //小时
  TimeUnit.MINUTES;           //分钟
  TimeUnit.SECONDS;           //秒
  TimeUnit.MILLISECONDS;      //毫秒
  TimeUnit.MICROSECONDS;      //微妙
  TimeUnit.NANOSECONDS;       //纳秒
  ```

- workQueue：选择一个阻塞队列

  ```java
  LinkedBlockingQueue;	// 常用，无界阻塞队列，不传值默认为Integer.MAX_VALUE，容易内存耗尽
  SynchronousQueue;
  ArrayBlockingQueue;
  PriorityBlockingQueue // 优先队列
  ```

- threadFactory：线程工厂，主要用来创建线程。如果不传此参数，默认：Executors.defaultThreadFactory()

- handler：表示当拒绝处理任务时的策略，有以下四种取值：

  如果不传此参数，默认：ThreadPoolExecutor.AbortPolicy

  ```java
  // 丢弃任务并抛出RejectedExecutionException异常。 
  ThreadPoolExecutor.AbortPolicy 
  // 也是丢弃任务，但是不抛出异常。
  ThreadPoolExecutor.DiscardPolicy 
  // 丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
  ThreadPoolExecutor.DiscardOldestPolicy
  // 由调用线程处理该任务 
  ThreadPoolExecutor.CallerRunsPolicy
  ```

ThreadPoolExecutor的重要方法：

- execute（Runnable command）

  通过这个方法可以向线程池提交一个任务，交由线程池去执行

  此方法在执行的时候，会判断当前线程数是否大于corePoolSize

  如果当前线程数大于corePoolSize，并且，当前线程池处于RUNNING状态，则将此任务加入任务缓冲队列

- submit（）

  内部调用execute（）方法

  这个方法也是用来向线程池提交任务的，但是它和execute()方法不同

  它能够返回任务执行的结果，利用了Future来获取任务执行结果

- shutdown（）

  关闭线程池，此时线程池不能够接受新的任务，**它会等待所有任务执行完毕**

- shutdownNow（）

  关闭线程池，线程池处于STOP状态，此时线程池不能接受新的任务，**并且会去尝试终止正在执行的任务**

举个例子：演示线程池的流程，线程都去哪了？

```java
/**
 * 这个例子演示了线程进入线程池的一个流程
 * 线程会先占用核心池，满了之后取队列等待，
 * 队列满了之后，如果还没有达到最大线程数量，继续创建线程。
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = 
                                   new ThreadPoolExecutor(
                                                2, 3, 2000,
                                                TimeUnit.MILLISECONDS, 
                                                new ArrayBlockingQueue(5), 
                                                new MyRejectedHandler());
        Runnable runTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    String name = "Task_" + i;
                    Task task = new Task(name);
                    try {
                        threadPoolExecutor.execute(task);
                        System.out.println(
                            "PoolSize: " + threadPoolExecutor.getPoolSize()+
                                ",Queue"+threadPoolExecutor.getQueue());
                        System.out.println();
                        //Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println("Refused:" + name);
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runTask);
        thread.start();
    }
}
```

```java
public class Task implements Runnable {
    private String name;
    public Task(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return this.name;
    }
}
// 重写RejectedExecutionHandler
public class MyRejectedHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Rejected："+r.toString());
    }
}
```

运行结果：

```java
PoolSize: 1,Queue[]
PoolSize: 2,Queue[]
// 到这里，核心池满了，之后线程，进入队列
PoolSize: 2,Queue[Task_2]
PoolSize: 2,Queue[Task_2, Task_3]
PoolSize: 2,Queue[Task_2, Task_3, Task_4]
PoolSize: 2,Queue[Task_2, Task_3, Task_4, Task_5]
PoolSize: 2,Queue[Task_2, Task_3, Task_4, Task_5, Task_6]
// 队列满了，继续创建线程到线程池，这一个多余的线程会在等待,并倒计时keepAliveTime
PoolSize: 3,Queue[Task_2, Task_3, Task_4, Task_5, Task_6]
// 最大线程数已满，拒绝后续线程
Rejected：Task_8
PoolSize: 3,Queue[Task_2, Task_3, Task_4, Task_5, Task_6]
Rejected：Task_9
PoolSize: 3,Queue[Task_2, Task_3, Task_4, Task_5, Task_6]
```

#### 执行execute()方法和submit()方法的区别是什么呢？

（1）**execute() 方法用于提交不需要返回值的任务，所以无法判断任务是否被线程池执行成功与否；**

（2）**submit()方法用于提交需要返回值的任务。线程池会返回一个future类型的对象，通过这个future对象可以判断任务是否执行成功**，并且可以通过future的get()方法来获取返回值，get()方法会阻塞当前线程直到任务完成，而使用 `get（long timeout，TimeUnit unit）`方法则会阻塞当前线程一段时间后立即返回，这时候有可能任务没有执行完。

#### 使用线程池的好处 

1. 降低资源消耗。重复利用已创建线程，降低线程创建与销毁的资源消耗。 
2. 提高响应效率。任务到达时，不需等待创建线程就能立即执行。 
3. 提高线程可管理性。
4. 防止服务器过载。内存溢出、CPU耗尽。

### 几个同步器

这两个类都拥有内部类Sync继承AQS类。

####　CountDownLatch类

是一个多线程控制工具类。可以实现计数器的功能。

如：任务A需要等任务B执行完毕之后执行，就可以用到此类。

底层通过AQS共享锁来实现。AQS（队列同步器）



```java
// 传入一个count，指定线程个数
public CountDownLatch(int count)
```

主要方法：

```java
// 调用await的线程会被挂起，直到count==0，才会继续执行。
public void await(){}；

// 等待一定时间，count还没有为0，线程继续执行
public boolean await(long timeout, TimeUnit unit){}；

// 将count值减1，一般在线程执行结束，调用此方法，代表计数器减1.
public void countDown() {};
```

举个栗子：

```java
public class Task implements Runnable {
    private String name;
    private CountDownLatch latch;
    public Task(CountDownLatch latch,String name) {
        this.latch = latch;
        this.name = name;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(this.name + " Over");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Task task_A = new Task(latch,"A");
        Task task_B = new Task(latch,"B");
        Task task_C = new Task(latch,"C");
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(task_B);
        executorService.submit(task_C);
        executorService.submit(task_A);
        // 挂起mian线程，等待计数器置0，也就是三个线程执行完毕。
        latch.await();
        executorService.shutdown();
        System.out.println(Thread.currentThread().getName());
    }
}
```

运行结果：

C Over
A Over
B Over
main

#### Semaphore类

参考<https://blog.csdn.net/carson0408/article/details/79475723>

了解一个概念：

通路：Semaphore构造时，设置通路数量，表示最多运行几个线程，每个线程运行即占用通路。

可以用release( )归还通路。（有一种连接池的感觉）

- **Semaphore信号量，控制了同时运行的线程数量。**

构造器：

```java
// 同一时刻只运行permits个线程
public Semaphore(int permits){}；
// 开启公平锁，先启动的线程先获得锁
public Semaphore(int permits, boolean fair){}；
```

主要方法：

```java
// 获取一个通道，可以在线程开始时调用。
public void acquire(){}；
// 获取多个通道。
public void acquire(int permits){}；
// 释放一个通道，线程任务执行完毕时调用
public void release(){}；
// 释放多个通道
public void release(int permits)
```

举例：

```java
/**
 * 设置三条通路，新建七个线程，观察运行结果
 * Semaphore信号量，控制了同时运行的线程数量。
 */
public class SemaphoreTest {
    private static final Semaphore semaphore=new Semaphore(3);
    private static final ExecutorService executor= Executors.newCachedThreadPool();

    private static class InformationThread extends Thread{
        private final String name;
        public InformationThread(String name)
        {
            this.name=name;
        }
		@Override
        public void run()
        {
            try
            {
                Thread.sleep(1000);
                semaphore.acquire();
                System.out.println(
                        Thread.currentThread().getName()+" 拿到通路");
                System.out.println("当前通路数量："+semaphore.availablePermits());
                Thread.sleep(1000);
                semaphore.release();
                System.out.println(Thread.currentThread().getName()+" 已经释放通路");
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args)
    {
        String[] name= {"A","B","C","D","E","F","G"};
        for(int i=0;i<7;i++)
        {
            Thread t1=new InformationThread(name[i]);
            executor.submit(t1);
        }
        executor.shutdown();
    }
}
```

运行结果：

截取部分运行结果：

可以看出，通路数量决定线程运行数量。

```
pool-1-thread-1 拿到通路
当前通路数量：2
pool-1-thread-2 拿到通路
当前通路数量：1
pool-1-thread-3 拿到通路
当前通路数量：0
pool-1-thread-1 已经释放通路
pool-1-thread-4 拿到通路
当前通路数量：0
......
```

### CyclicBarrier类

CyclicBarrier翻译回环栅栏，实现的功能是让多个线程运行到某一标志点后挂起，当所有线程都到此标志位后再一起运行，类似起跑线，当所有人都各就位后才能唤醒，开始奔跑。

应用场景 ：多个线程做任务，等到达集合点同步后交给后面的线程做汇总。

### AQS



---

#### java.util.concurrent.Locks：

参考：

<https://www.cnblogs.com/xiaoxi/p/7651360.html>

<https://github.com/doGdx/JavaGuide/blob/master/docs/java/synchronized.md>

**synchronized和ReentrantLock的区别**：(见下文)

### Lock接口

```java
public interface Lock {
    // 获得锁
	void lock();
    // 获得锁
    void unlock();
    // lock非阻塞版本，成功返回true
    boolean tryLock();
    // 添加尝试时间，时间到返回false
    boolean tryLock(long time, TimeUnit unit)
    // 返回一个监视器对象
    Condition newCondition();
}
```

# 

### ReentrantLock类（实现Lock接口）

独占锁

**锁释放请务必在finally中进行**。

**公平锁的实现见**：	Java锁.md—公平锁与非公平锁

```java
public class ReentrantLock implements Lock,Serializable {
	// 构造器,可以实现公平锁
    public ReentrantLock()
    public ReentrantLock(boolean fair)
    public void lock()
    // 可中断锁
    public void lockInterruptibly()  
    // 可轮询的锁获取,有返回值。获取成功返回true；获取失败，返回false，线程不会阻塞、
    public boolean tryLock()
    public boolean tryLock(long timeout, TimeUnit unit)
    // 返回一个监视器对象
    Condition newCondition();
}
```

**tryLock（）实现：线程没拿到锁，依然可以执行别的任务，不会阻塞！！！**

```java
public class ReentrantLock_tryLock {
    private static final Lock lock = new ReentrantLock();
    public static void test(){
        if (lock.tryLock()){
            // trylock返回true，即拿到锁
            System.out.println(Thread.currentThread().getName()+"拿到锁！");
        }
        else {
            /**
             * 没拿到锁，可以让线程继续做别的事，不会阻塞
             */
            System.out.println(Thread.currentThread().getName()+"没拿到锁！");
        }
    }
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + "线程启动");
            try {
                Thread.sleep(1000);
                test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread t1 =new Thread(task);
        Thread t2 =new Thread(task);
        Thread t3 =new Thread(task);
        Thread t4 =new Thread(task);
        Thread[] threads ={t1,t2,t3,t4};
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
// 运行结果：
Thread-0线程启动
Thread-1线程启动
Thread-2线程启动
Thread-3线程启动
Thread-0拿到锁！	
Thread-1没拿到锁！ // 没拿到锁的线程依然在执行！！
Thread-3没拿到锁！
Thread-2没拿到锁！
```

lockInterruptibly（）可中断锁，目前没有合适的使用场景。

### ReadWriteLock接口

```java
public interface ReadWriteLock {
    Lock readLock();	// 读锁
    Lock writeLock();	// 写锁
}
```

### ReentrantReadWriteLock类（实现ReadWriteLock接口）

```java
public class ReentrantReadWriteLock implements ReadWriteLock,Serializable {
    // 构造函数，可实现公平锁
    public ReentrantReadWriteLock(boolean fair) {
        sync = fair ? new FairSync() : new NonfairSync();
        readerLock = new ReadLock(this);
        writerLock = new WriteLock(this);
    }
}
```

首先明白两个概念：（在Java锁.md中已经描述很清楚了）

读锁：共享锁：多个线程进入读锁，交替执行。

写锁：独占锁(互斥)

锁升级：读锁进入写锁（ReentrantReadWriteLock不能实现）

锁降级：写锁进入读锁（可以实现）

**举个例子看一下读锁（共享锁）相比与独占锁的优势：**

明显在只有读的情况下：读锁的效率高出很多。

```java
public class ReentrantReadWriteLock_Test {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    // 读锁：
    public static void test_1(){
        System.out.println("start： "+System.currentTimeMillis());
        try{
            lock.readLock().lock();
            // 读取5次
            for (int i = 0; i < 5; i++) {
                Thread.sleep(20);
                System.out.println(Thread.currentThread().getName()+" 正在读取....");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
            System.out.println("end： "+System.currentTimeMillis());
        }
    }
    // synchronized独占锁：
    public static synchronized void test_2(){
        try{
            System.out.println("start： "+System.currentTimeMillis());
            for (int i = 0; i < 5; i++) {
                Thread.sleep(20);
                System.out.println(Thread.currentThread().getName()+" 正在读取....");
            }
            System.out.println("end： "+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
		// 两个任务，分别进入两个锁
        Runnable task_1 = ()->{
            test_1();
        };
        Runnable task_2 = ()->{
            test_2();
        };
        // 读锁线程：
//        Thread t1 = new Thread(task_1);
//        Thread t2 = new Thread(task_1);
        // 独占锁线程：
        Thread t1 = new Thread(task_2);
        Thread t2 = new Thread(task_2);
        t1.start();
        t2.start();
    }
}
分别执行两种锁之后的运行结果：
// synchronized独占锁:用时200ms（串行执行）
start： 1562942959951
Thread-0 正在读取....
Thread-0 正在读取....
Thread-0 正在读取....
Thread-0 正在读取....
Thread-0 正在读取....
end： 1562942960052
start： 1562942960052
Thread-1 正在读取....
Thread-1 正在读取....
Thread-1 正在读取....
Thread-1 正在读取....
Thread-1 正在读取....
end： 1562942960153
// 读锁：用时：100ms（交替执行，共享锁）
start： 1562943141496
start： 1562943141497
Thread-0 正在读取....
Thread-1 正在读取....
Thread-0 正在读取....
Thread-1 正在读取....
Thread-0 正在读取....
Thread-1 正在读取....
Thread-0 正在读取....
Thread-1 正在读取....
Thread-0 正在读取....
end： 1562943141598
Thread-1 正在读取....
end： 1562943141598
```

锁降级示例：（从写锁顺利进入读锁）

```java
public class Write_Read {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                // 先进入写锁，还可以顺利进入读锁
                lock.writeLock().lock();
                System.out.println("进入了写锁");
                lock.readLock().lock();
                System.out.println("进入了读锁");
            } finally {
                lock.readLock().unlock();
                lock.writeLock().unlock();
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
//运行结果：
进入了写锁
进入了读锁 // 程序结束
```

无法从读锁进入写锁：（锁升级）

```java
public class Write_Read {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                // 先进入读锁，无法进入写锁，线程发生阻塞
                lock.readLock().lock();
                System.out.println("进入了读锁");
                lock.writeLock().lock();
                System.out.println("进入了写锁");
            } finally {
                lock.writeLock().unlock();
                lock.readLock().unlock();
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
//运行结果：
进入了读锁 // 阻塞在这里
```

### Condition接口

实现线程通信等待、唤醒

```java
public interface Condition {
    // 对应线程wait（）方法，进入等待队列
    void await()
    boolean await(long time, TimeUnit unit)
    // 相当于notify（）、notifyAll（）
    void signal();
    void signalAll();
}
```

具体实现见：JavaCode/Concurrent/Lock/ReentrantLock_Demo下的代码实现

### Synchronized 和 ReenTrantLock 的对比

1. 都是可重入锁

   可以再次获取自己的内部锁，即：一个线程获取某对象锁，在没有释放此对象锁的同时，可以再次获得此锁；

   一个锁每次被线程获取，锁计数器就增加1，知道锁计数器将为0，才能释放锁；

2. Synchronized依赖于JVM，ReenTrantLock依赖于API

   Synchronized的很多优化，都是在JVM层面优化的，并不暴露给用户；

   ReenTrantLock的各种功能实现，都可以用户自己通过调用API进行实现（lock，unLock）；

3. ReenTrantLock增加了高级功能

   1. 等待可中断

      调用：

      lock.lockInterruptibly( )；

      让等待锁的线程，放弃等待，去执行别的事情。

   2. 可实现公平锁

      Synchronized默认非公平；ReenTrantLock默认也是非公平锁；

      构造公平锁：

      Lock lock=new ReentrantLock(true)

   3. 锁可以绑定Condition，实现选择性通知

      synchronized关键字与wait()和notify/notifyAll()方法相结合可以实现等待/通知机制；

      ReentrantLock借助于与锁绑定的condition对象，来调用await()和signal/signalAll()方法来实现；

   4. ReentrantLock可以实现tryLock

      对于没有拿到锁的线程，可以令其做一些别的事情，不会阻塞；

4. Synchronized与ReentrantLock性能持平

   在JVM各种锁机制的优化下，Synchronized在很多地方都默认使用CAS的乐观锁，进行优化了；

   所以并不是ReentrantLock性能更强！！

### 容器类

#### ConcurrentHashMap

线程安全版本的HashMap，读写效率要比加锁后的HashMap高。

Hashtable也是加锁后的HashMap版本，但是效率底下，因为其使用了synchronized对整个对象进行加锁。

ConcurrentHashMap使用**分段加锁机制**。



---

#### java.util.concurrent.atomic:（原子类）

#### 基本类型

- AtomicInteger：整型原子类（以此类为例，用法类似）
- AtomicLong：长整型原子类
- AtomicBoolean ：布尔型原子类

#### 数组类型

- AtomicIntegerArray：整型数组原子类（以此类为例，用法类似）
- AtomicLongArray：长整型数组原子类
- AtomicReferenceArray ：引用类型数组原子类

#### 引用类型

- AtomicReference：引用类型原子类（以此类为例，用法类似）
- AtomicStampedRerence：原子更新引用类型里的字段原子类
- AtomicMarkableReference ：原子更新带有标记位的引用类型

#### 对象属性原子类

- AtomicIntegerFieldUpdater：针对整型的对象属性
- AtomicLongFieldUpdater：针对Long型对象属性
- AtomicStampedReference：

### AtomicInteger

构造器：

```java
// 传入一个数值
public AtomicInteger(int initialValue) {
    value = initialValue;
}
```

常用方法：

```java
// 获取当前值
public final int get()
// 获取当前值并设置新值,返回的是get的值，也就是没有设置之前的值
public final int getAndSet(int newValue)
// 自+1
public final int getAndIncrement()
// 自-1    
public final int getAndDecrement()
// 获取当前值，加上delta
public final int getAndAdd(int delta)	
```

栗：

```java
public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(0);
        // 返回的是0，get的值，而非结果
        int original =i.getAndAdd(3);
        System.out.println("original(原值)："+original+"=====i(结果):"+i.get());
        original = i.getAndIncrement();
        System.out.println("original(原值)："+original+"=====i(结果):"+i);
        original = i.getAndSet(1);
        System.out.println("original(原值)："+original+"=====i(结果):"+i);
    }
}
// 运行结果
original(原值)：0=====i(结果):3
original(原值)：3=====i(结果):4
original(原值)：4=====i(结果):1
```

原子操作：

AtomicInteger 类主要利用 CAS (compare and swap) + volatile（构造器传入的值）和 native 方法来保证原子操作，从而避免 synchronized 的高开销，执行效率大为提升。

### AtomicIntegerArray（数组原子类）

使用原子的方式更新数组元素，使用方法基本类似。

```java
public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        int[] nums ={1,2,3,4,5};
        AtomicIntegerArray atomicnums =new AtomicIntegerArray(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(atomicnums.get(i));
        }
        int ori = atomicnums.getAndSet(0,10);
        System.out.println("original(原值)："+ori+"=====i(结果):"+atomicnums.get(0));
        ori = atomicnums.getAndIncrement(0);
        System.out.println("original(原值)："+ori+"=====i(结果):"+atomicnums.get(0));
        ori=atomicnums.getAndAdd(0,5);
        System.out.println("original(原值)："+ori+"=====i(结果):"+atomicnums.get(0));
    }
}
// 运行结果
1
2
3
4
5
original(原值)：1=====i(结果):10
original(原值)：10=====i(结果):11
original(原值)：11=====i(结果):16
```

### AtomicReference（引用类型原子类）

将对象引用设置为原子类，用原子类操作进行更新。

```java
public class AtomicReferenceTest {
    public static void main(String[] args) {
        AtomicReference<Student> ar = new AtomicReference<>();
        Student stu_1 = new Student("小明",17);
        Student stu_2 = new Student("大明",18);
        ar.set(stu_1);
        System.out.println(ar.get().toString());
        // 原子更新操作
        ar.compareAndSet(stu_1,stu_2);
        System.out.println(ar.get().toString());
    }
}
```

### AtomicIntegerFieldUpdater

1. 只能传入基本数据类型（int，long）不能修改包装类型
2. 要修饰的属性字段，必须volatile修饰,且不可为private

```java
public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<Student> atomicStudent =
                AtomicIntegerFieldUpdater.newUpdater(Student.class, "age");
        Student student = new Student("ming", 17);
        System.out.println(atomicStudent.getAndSet(student, 20));
        System.out.println(atomicStudent.get(student));
    }
}
public class Student {
   	private String name;
    volatile int age;

    public Student(String name,int age){
        this.name=name;
        this.age=age;
    }
    // getter/setter...
}
运行结果：
17
20
```

