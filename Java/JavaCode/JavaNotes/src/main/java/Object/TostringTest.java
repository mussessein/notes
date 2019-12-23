package Object;


/**
 * 重写ToString方法???????????????????????????
 */
class Apple{
    private String color;
    private double weight;
    public Apple(){}
    public Apple(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }
    public String ToString() {

        return "这个苹果的颜色是：" + color + ",重量是：" + weight+"g";
    }
}
public class TostringTest {
    public static void main(String[] args) {
        Apple a = new Apple("红色", 30);
        System.out.println(a);
    }
    
}