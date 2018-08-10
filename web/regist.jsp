<%--
  Created by IntelliJ IDEA.
  User: zjn
  Date: 2018/8/9
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script src="style/libs/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="style/css/index.css"/>
    <script type="text/javascript">
        function changeImg(img){
            img.src = img.src+"?time="+new Date().getTime();
        }
    </script>
</head>
<body>
<header>
    <div class="desc">store注册</div>
</header>
<section>
    <form action="/RegistServlet" method="POST">
        <div class="register-box">
            <label for="username_msg" id="username_msg" class="username_label">用户名
                <input maxlength="20" type="text" name="username" value="${param.username }" placeholder="请输入用户名"/>
            </label>
            <div class="tips">

            </div>
        </div>
        <div class="register-box">
            <label for="username_msg" class="other_label">设置密码
                <input maxlength="20" type="password" name="password" placeholder="请输入密码"/>
            </label>
            <div class="tips">

            </div>
        </div>
        <div class="register-box">
            <label for="username_msg" class="other_label">确认密码
                <input maxlength="20" type="password" name="password2" placeholder="再次输入密码"/>
            </label>
            <div class="tips">

            </div>
        </div>
        <div class="register-box">
            <label for="username_msg" class="username_label">昵称
                <input maxlength="20" type="text" name="nickname" value="${param.nickname }" placeholder="请起个昵称"/>
            </label>
            <div class="tips">

            </div>
        </div>
        <div class="register-box">
            <label for="username_msg" class="other_label">邮箱
                <input maxlength="20" type="text" name="email" value="${param.email }" placeholder="请输入邮箱"/>
            </label>
            <div class="tips">

            </div>
        </div>
        <div class="register-box">
            <label for="valistr_msg"  class="other_label">验证码
                <input maxlength="20" type="text" name="valistr" placeholder="请输入验证码"/>
                <td id="valistr_msg">${msg }</td>
                <img src="/ValiImg" onclick="changeImg(this)" style="cursor: pointer;"/>
            </label>
            <div class="tips">

            </div>
        </div>
        <div class="submit_btn">
            <button type="submit" id="submit_btn">立即注册</button>

        </div>
    </form>
</section>
<script src="style/js/index.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
