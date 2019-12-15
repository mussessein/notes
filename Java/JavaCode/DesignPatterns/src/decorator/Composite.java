package decorator;

/**   (装饰设计模式) ：对已有类进行增强，比继承方式更灵活
 *              Composite，组合，非继承！
 * 组合实现的类似于继承的一种复用模式！！！！
 */
class Animal {
    private void beat() {

        System.out.println("心跳");
    }

    public void breath() {
        beat();
        System.out.println("呼吸");
    }
}

class Bird {
    //将Animal类直接 组合 到Bird类中~！
    private Animal a;

    public Bird(Animal a) {

        this.a = a;
    }

    //直接使用Animal提供的breath()方法！！
    public void breath() {

        a.breath();
    }

    public void fly() {

        System.out.println("飞");
    }
}

public class Composite {
    public static void main(String[] args) {
        //显示创建被组合的对象！！
        Animal a1 = new Animal();
        Bird b1 = new Bird(a1);    //Bird b1 = new Bird(new Animal());
        //实现了类似于“父类”的方法
        b1.breath();
        b1.fly();
    }
    
}