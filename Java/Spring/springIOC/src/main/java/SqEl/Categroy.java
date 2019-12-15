package SqEl;

public class Categroy {
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Categroy{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
