<%--
  Created by IntelliJ IDEA.
  User: zjn
  Date: 2018/8/10
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style/css/login.css"/>
    <script type="text/javascript">
        window.onload=function(){
            var str = decodeURI('${cookie.remname.value}');
            document.getElementsByName("username")[0].value = str;
        }
    </script>
</head>
<body>
<header>
    <div class="desc">store登录</div>
</header>
<section>
    <div class="txt">
        ${msg}
    </div>

        <form action="/LoginServlet" method="post">

            <div class="login-box">
                <label  class="username_label">用户名
                    <input maxlength="20" type="text" name="username" value=""/>
                </label>
                <div class="bots">

                </div>
            </div>
            <div class="login-box">
                <label  class="username_label">密码
                    <input maxlength="20" type="password" name="password" value=""/>
                </label>
                <div class="bots">

                </div>
            </div>
                <div class="remeber-box">
                <table>
                <tr>
                    <td><input type="checkbox" name="remname" value="true"
                        <c:if test="${cookie.remname != null}">
                            checked="checked"
                        </c:if>
                    />记住用户名</td>
                    <td><input type="checkbox" name="autologin" value="true"/>30天内自动登录</td>
                </tr>
                </table>
                </div>
                <div class="submit_btn">
                        <button type="submit" id="submit_btn">登录</button>

                </div>


        </form>
</section>
</body>
</html>
