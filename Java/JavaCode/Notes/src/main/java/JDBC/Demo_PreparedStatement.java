package JDBC;
import java.sql.*;
/*
 *  PreparedStatement�ӿڣ�ʵ��ִ�д�������sql��䣬����ִ��sql���
 *  �˽ӿ�Ҳ�ṩ�ˣ�execute/executeQuery/executeUpdate���ַ�����
 *  �����ַ������������ò�������Ϊ�˽ӿڣ��Ѿ��洢��Ԥ�����sql���
 * */

public class Demo_PreparedStatement {
	//���Ӳ���
	public static final String Driver_Name="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://localhost:3306/student?serverTimezone=UTC&useSSL=false";
	public static final String User_Name="root";
	public static final String Password="123456";

	public static void main(String[] args) {
		/*
		* 1. ��������connection
		* 2. ����statement����
		* 3. ql���
		* 4. �����
		* */
		Connection connection=null;
		PreparedStatement prst;
		String sql;
		//ResultSet rs=null;
		try {
			//��������
			Class.forName(Driver_Name);
			connection = DriverManager.getConnection(URL, User_Name, Password);
			//����sql���
			sql="insert into stu3 value (?,?,?)";
			prst = connection.prepareStatement(sql);
			//��sql����ָ������λ�ò�������
			prst.setInt(1,3);
			prst.setString(2,"ls");
			prst.setInt(3,1000);
			//�����,��ʱexecuteQuery�����������ΪprepareStatement�Ѿ�Ԥ����sql���
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