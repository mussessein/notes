package JDBC;
import java.sql.*;
/*
 *  PreparedStatement接口，实现执行带参数的sql语句，批量执行sql语句
 *  此接口也提供了，execute/executeQuery/executeUpdate三种方法，
 *  这三种方法都无需设置参数，因为此接口，已经存储了预编译的sql语句
 * */

public class Demo_PreparedStatement {
	//链接参数
	public static final String Driver_Name="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://localhost:3306/student?serverTimezone=UTC&useSSL=false";
	public static final String User_Name="root";
	public static final String Password="123456";

	public static void main(String[] args) {
		/*
		* 1. 创建链接connection
		* 2. 创建statement对象
		* 3. ql语句
		* 4. 结果集
		* */
		Connection connection=null;
		PreparedStatement prst;
		String sql;
		//ResultSet rs=null;
		try {
			//加载驱动
			Class.forName(Driver_Name);
			connection = DriverManager.getConnection(URL, User_Name, Password);
			//含参sql语句
			sql="insert into stu3 value (?,?,?)";
			prst = connection.prepareStatement(sql);
			//在sql语句的指定参数位置插入数据
			prst.setInt(1,3);
			prst.setString(2,"ls");
			prst.setInt(3,1000);
			//结果集,此时executeQuery无需参数，因为prepareStatement已经预存了sql语句
			prst.executeUpdate();
			prst.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
				try {
					if(connection!=null)connection.close();
				}
				catch (SQLException e){
					e.printStackTrace();
				}
		}
	}

}