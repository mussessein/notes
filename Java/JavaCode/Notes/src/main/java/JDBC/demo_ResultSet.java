package JDBC;
import java.sql.*;
/* 可滚动，可更新的结果据
 * 在创建Statement()对象的时候传入参数可以控制ResultSet
 * 1.ResultSet.Type_FORWARD_ONLY            控制记录指针只能向前移动
 * 2.ResultSet.Type_SCROLL_INSENSITIVE      控制记录指针可以自由移动，但底层数据改变不影响ResultSet输出
 * 3.
 *
 * 1.ResultSet.CONCUR_READ_ONLY        指定ResultSet只读的并发模式
 * 2.ResultSet.CONCUR_UPDATABLE         指定ResultSet可更新的并发模式
 * */
public class demo_ResultSet {
    /*
    * 设置结果集可滚动/可更新
    * 通过控制指针的移动
    * 1.完成表的逆序输出
    * 2.批量更改了第二列的内容
    *
    * */
    public static final String Driver_Name="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/student?serverTimezone=UTC&useSSL=false";
    public static final String User_Name="root";
    public static final String Password="123456";

    public void query(String sql) throws Exception{

        Class.forName(Driver_Name);
        try(
                Connection connection=DriverManager.getConnection(URL,User_Name,Password);
                //创建PreparedStatement对象，传入结果集可滚动/可更新的参数
                PreparedStatement pstmt=connection.prepareStatement(sql
                        ,ResultSet.TYPE_SCROLL_INSENSITIVE
                        ,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs=pstmt.executeQuery()
                ){
            //使指针逆序，从后向前走
            rs.last();
            //得到行值
            int rowCount =rs.getRow();
            //循环逆序操作
            for (int i=rowCount;i>0;i--){
                /* i如果为正，指针移动到相对于结果集正向的对应行号i
                *  i如果为负，指针移动到相对于结果集负向的对应行号i
                *  此处i为正
                *  */
                rs.absolute(i);
                //输出此行的每一列
                System.out.println(rs.getString(1)+"\t"
                    +rs.getString(2));
                //更该第二列的内容（更改列，更改内容）
                rs.updateString(2,"学生名"+i);
                //更新当前结果集的当前行的数据
                rs.updateRow();
            }
        }
    }

    public static void main(String[] args) throws  Exception{

        demo_ResultSet dr=new demo_ResultSet();
        dr.query("select * from stu3");
    }
}
