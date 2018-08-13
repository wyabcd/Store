<%--
  Created by IntelliJ IDEA.
  User: zjn
  Date: 2018/8/11
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
    <font color="red"><h1>${prod.name}</h1><hr></font>
    <table width="100%">
        <tr>
            <td><img src="/ImgServlet?imgurl=${prod.imgurl}"/></td>
            <td>
                商品名称：${prod.name}<br>
                商品种类：${prod.category}<br>
                商品库存：${prod.pnum}<br>
                商品价格：${prod.price}<br>
                商品描述：${prod.description}<br>
                <a href="/AddCartServlet?id=${prod.id}"><img src="/img/buy.bmp"/></a> <br>

            </td>
        </tr>
    </table>
</body>
</html>
