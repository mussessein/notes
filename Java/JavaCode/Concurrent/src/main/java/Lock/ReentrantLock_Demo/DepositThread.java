package Lock.ReentrantLock_Demo;
/*
定义存钱的线程
 */
public class DepositThread extends Thread{

    private Account account;
    private double depositAmount;
    public DepositThread(String name,Account account,double depositAmount){
        super(name);
        this.account=account;
        this.depositAmount=depositAmount;
    }

    @Override
    //重复存钱100次的线程
    public void run() {
        for (int i=0;i<50;i++){
            account.deposit(depositAmount);
        }
    }
}
