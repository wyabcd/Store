<%--
  Created by IntelliJ IDEA.
  User: zjn
  Date: 2018/7/30
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
  </head>
  <body>
      <h1>store</h1>
      <c:if test="${sessionScope.user==null}">
        欢迎光临，游客
        <a href="/regist.jsp">注册</a>
        <a href="/login.jsp">登录</a>
      </c:if>
      <c:if test="${sessionScope.user!=null}">
        欢迎回来，${sessionScope.user.username }
        <a href="/LogoutServlet">注销</a>
      </c:if>
  </body>
</html>
