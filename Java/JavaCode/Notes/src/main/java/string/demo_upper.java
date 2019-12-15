package string;

public class demo_upper {
    private void test(){
        String a="?One£¿";
        String b=a;
        String c = a.toUpperCase();
        b.trim();
        System.out.println("["+a+","+b+"]");
        System.out.println(c);
    }
    public static void main(String[] args){
        new demo_upper().test();
    }
}
