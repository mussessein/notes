package JDBC;

import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/*
����sqlע��©���ķ���
 */
public class demo_sqlError {
  @Test
  public void testDemo() {
    /*
    ������¼login("aaa", "123456")��
    1.��һ��ע�룺login("aaa' or '1=1 ", "shdasuji")
       select * from user where username='aaa' or '1=1' and password = 'shdasujd'
       ������������Ϊ���٣�where�ж�Ϊtrue��
    2.�ڶ���ע�룺login("aaa' --", "shdasuji")
       ��--��������ݱ����ע�ͣ������벻����Ƚ�
     */
    boolean flag = demo_sqlError.login("aaa' -- ", "1456");
    if (flag == true) {
      System.out.println("��½�ɹ�!!!");
    } else {
      System.out.println("��¼ʧ��!!!");
    }
  }

  /*
  �˷��������sqlע��©����
   */
  public static boolean login(String username, String password) {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    boolean flag = false;
    try {
      connection = JDBCUtils.getConnection();
      statement = connection.createStatement();
      String sql =
          "select * from user where username='" + username + "'and password='" + password + "'";
      resultSet = statement.executeQuery(sql);
      if (resultSet.next()) {
        flag = true;
      } else {
        flag = false;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtils.release(resultSet, statement, connection);
    }
    return flag;
  }
  /** ����SQLע��©���ķ���
   * PreparedStatementԤ��������sql©�� */
  public static boolean login2(String username, String password) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    boolean flag = false;
    try {
      conn = JDBCUtils.getConnection();
      String sql = "select * from user where username = ? and password = ?";
      // Ԥ����SQL:
      pstmt = conn.prepareStatement(sql);
      // ���ò���:
      pstmt.setString(1, username);
      pstmt.setString(2, password);
      // ִ��SQL��
      rs = pstmt.executeQuery();
      // �жϽ����
      if (rs.next()) {
        flag = true;
      } else {
        flag = false;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtils.release(rs, pstmt, conn);
    }
    return flag;
  }
}
