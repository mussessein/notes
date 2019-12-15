package AspectJXML;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public void save() {
        System.out.println("....保存客户....");
    }

    @Override
    public String update() {
        System.out.println("....更新客户....");
        return "hello";
    }

    @Override
    public void delete() {
        System.out.println("....删除客户....");
        //int i=1/0;
    }

    @Override
    public void find() {
        System.out.println("....查询客户....");
//        int i=1/0;
    }
}
