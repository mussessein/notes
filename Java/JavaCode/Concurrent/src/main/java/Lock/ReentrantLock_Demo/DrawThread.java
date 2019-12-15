package Lock.ReentrantLock_Demo;
/*
定义取钱的线程
 */
public class DrawThread extends Thread{
    //传入账户
    private Account account;
    private double drawAmount;
    public DrawThread(String name,Account account,double drawAmount){
        super(name);
        this.account=account;
        this.drawAmount=drawAmount;
    }

    @Override
    //重复取钱100次的线程
    public void run() {
        for (int i=0;i<50;i++){
            account.draw(drawAmount);
        }
    }
}
