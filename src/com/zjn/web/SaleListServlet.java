package com.zjn.web;

import com.zjn.domain.SaleInfo;
import com.zjn.factory.BasicFactory;
import com.zjn.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SaleListServlet")
public class SaleListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用Service中查询销售榜单的方法
        OrderService service= BasicFactory.getFactory().getService(OrderService.class);
        List<SaleInfo> list=service.saleList();
        //2.将销售榜单信息组织csv格式的数据
        StringBuffer buffer=new StringBuffer();
        buffer.append("商品编号，商品名称，销售数量\r\n");
        for(SaleInfo si:list){
            buffer.append(si.getProd_id()+","+si.getProd_name()+","+si.getSale_num()+"\r\n");
        }
        String data=buffer.toString();
        //3.提供下载
        String filename="store销售榜单_"+new Date().toLocaleString()+".csv";
        response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(filename,"utf-8"));
        response.setContentType(this.getServletContext().getMimeType(filename));
        response.getWriter().write(data);
    }
}
