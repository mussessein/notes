package notes;

/**  下面两类方法以哪一个为主？
       覆盖的方法 以子类为主
 *  
 */
public class problem{
    public static void main(String[] args) {
        new Thread(new Runnable(){
        
            @Override
            public void run() {
                System.out.println("runnable run");
            }
        })
        {
            public void run() {
                System.out.println("subThread..run");
            }
        }.start();
    }
}