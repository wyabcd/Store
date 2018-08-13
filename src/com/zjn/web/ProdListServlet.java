package com.zjn.web;

import com.zjn.domain.Product;
import com.zjn.factory.BasicFactory;
import com.zjn.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProdListServlet")
public class ProdListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProdService service=BasicFactory.getFactory().getService(ProdService.class);
        //1.调用service查询所有商品
        List<Product> list=service.findAllProd();
        //2.将所有商品信息存入requet域后带到页面展示
        request.setAttribute("list",list);
        request.getRequestDispatcher("/prodList.jsp").forward(request,response);

    }
}
