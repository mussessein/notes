package Algorithm;

/**
 * 判定一个数是否为素数
 */
public class isPrime {

    public static boolean isPrime(int n){
        if (n<2) return false;
        for (int i=2;i*i<=n;i++){
            if (n%i==0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(1.0/0/0);
        Integer i =1;
        Integer a= 2;
        System.out.println(i<a);
    }
}
