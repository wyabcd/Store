package com.zjn.filter;

import com.zjn.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "PrivilegeFilter")
public class PrivilegeFilter implements Filter {
    private List<String> admin_list=new ArrayList<String>();
    private List<String> user_list=new ArrayList<String>();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        String uri=request.getRequestURI();
        if(admin_list.contains(uri)||user_list.contains(uri)){
            //说明当前资源需要权限
            if(request.getSession(false)==null||request.getSession().getAttribute("user")=="null"){
                resp.getWriter().write("当前资源需要权限，请先登录！");
                return;
            }
            User user=(User) request.getSession().getAttribute("user");
            if(admin_list.contains(uri)&&"admin".equals(user.getRole())){
                chain.doFilter(req,resp);
            }else if(user_list.contains(uri)&&"user".equals(user.getRole())){
                chain.doFilter(req,resp);
            }else{
                throw new RuntimeException("您不具有对应的权限");
            }
        }else{
            //不需要权限
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        ServletContext context=config.getServletContext();
        try {
            BufferedReader adminReader=new BufferedReader(new FileReader(context.getRealPath("WEB-INF/admin.txt")));
            String line=null;
            while((line=adminReader.readLine())!=null){
                admin_list.add(line);
            }
            BufferedReader userReader=new BufferedReader(new FileReader(context.getRealPath("WEB-INF/user.txt")));
            line=null;
            while((line=userReader.readLine())!=null){
                user_list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
