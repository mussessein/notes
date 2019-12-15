<%--
  Created by IntelliJ IDEA.
  User: whr
  Date: 2019/3/15
  Time: 10:43
  将请求转发到下一个页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    response.sendRedirect("/to_login");
%>
</body>
</html>
