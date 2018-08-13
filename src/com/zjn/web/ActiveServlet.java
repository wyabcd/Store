package com.zjn.web;

import com.zjn.factory.BasicFactory;
import com.zjn.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ActiveServlet")
public class ActiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service=BasicFactory.getFactory().getService(UserService.class);
        //1.获取激活码
        String activecode=request.getParameter("activecode");
        //2.调用service中的方法激活
        service.acitveUser(activecode);
        //3.登录用户
        //request.getSession().setAttribute("user",user);
        //4.激活成功，3秒后回到主页
        response.getWriter().write("恭喜您激活成功,3秒后回到主页....");
        response.setHeader("Refresh", "3;url=/index.jsp");
    }
}
