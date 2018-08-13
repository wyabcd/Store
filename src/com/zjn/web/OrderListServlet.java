package com.zjn.web;

import com.zjn.domain.OrderListForm;
import com.zjn.domain.User;
import com.zjn.factory.BasicFactory;
import com.zjn.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderListServlet")
public class OrderListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService service=BasicFactory.getFactory().getService(OrderService.class);
        //1.获取用户id
        User user=(User)request.getSession().getAttribute("user");
        int id=user.getId();
        //2.调用service中根据用户id查询用户具有的订单的方法
        List<OrderListForm> list=service.findOrders(id);
        //3.存入request域带到页面显示
        request.setAttribute("list",list);
        request.getRequestDispatcher("/orderList.jsp").forward(request,response);
    }
}
