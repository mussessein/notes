package JDBC;

import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/*
测试sql注入漏洞的方法
 */
public class demo_sqlError {
  @Test
  public void testDemo() {
    /*
    正常登录login("aaa", "123456")；
    1.第一种注入：login("aaa' or '1=1 ", "shdasuji")
       select * from user where username='aaa' or '1=1' and password = 'shdasujd'
       无论密码输入为多少，where判定为true，
    2.第二种注入：login("aaa' --", "shdasuji")
       将--后面的内容变成了注释，即密码不参与比较
     */
    boolean flag = demo_sqlError.login("aaa' -- ", "1456");
    if (flag == true) {
      System.out.println("登陆成功!!!");
    } else {
      System.out.println("登录失败!!!");
    }
  }

  /*
  此方法会造成sql注入漏洞！
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
  /** 避免SQL注入漏洞的方法
   * PreparedStatement预处理，避免sql漏洞 */
  public static boolean login2(String username, String password) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    boolean flag = false;
    try {
      conn = JDBCUtils.getConnection();
      String sql = "select * from user where username = ? and password = ?";
      // 预处理SQL:
      pstmt = conn.prepareStatement(sql);
      // 设置参数:
      pstmt.setString(1, username);
      pstmt.setString(2, password);
      // 执行SQL：
      rs = pstmt.executeQuery();
      // 判断结果街
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
