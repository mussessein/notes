package Reflect;

public class Person {
    public String name;
    private String sex;

    public Person() {
    }

    public Person(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    /**
     * 提供一个方法
     */
    public void eat(){
        System.out.println("吃东西...");
    }
    private void shower(){
        System.out.println("老子正在洗澡...");
    }
    private String speak(String str){
        return str;
    }
}
