package com.zjn.listener;

import com.zjn.domain.Product;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.LinkedHashMap;

@WebListener()
public class MyHSessionListener implements HttpSessionListener{


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("cartmap",new LinkedHashMap<Product,Integer>());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
