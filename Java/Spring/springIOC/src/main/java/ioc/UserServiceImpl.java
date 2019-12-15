package ioc;

public class UserServiceImpl implements UserService{

    //添加属性，DI 依赖注入
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void sayHello() {

        System.out.println("Hello Spring!"+name);
    }
}
