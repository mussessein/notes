package notes;

/**  �������෽������һ��Ϊ����
       ���ǵķ��� ������Ϊ��
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