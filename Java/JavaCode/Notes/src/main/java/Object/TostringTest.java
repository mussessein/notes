package Object;


/**
 * ��дToString����???????????????????????????
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

        return "���ƻ������ɫ�ǣ�" + color + ",�����ǣ�" + weight+"g";
    }
}
public class TostringTest {
    public static void main(String[] args) {
        Apple a = new Apple("��ɫ", 30);
        System.out.println(a);
    }
    
}