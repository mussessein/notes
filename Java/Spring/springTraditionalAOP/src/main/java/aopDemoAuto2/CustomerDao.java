package aopDemoAuto2;

/*
此类没有实现接口
 */
public class CustomerDao {
    public void save() {
        System.out.println("客户保存...");
    }

    public void update() {

        System.out.println("客户更新..."
        );
    }

    public void delete() {
        System.out.println("客户删除...");

    }

    public void savefind() {
        System.out.println("客户查找...");

    }
}
