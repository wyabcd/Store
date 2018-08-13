package com.zjn.dao;


import com.zjn.domain.Order;
import com.zjn.domain.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends Dao{
    /**
     * 在订单表中插入记录
     */
    void addOrder(Order order) throws SQLException;
    /**
     * 在订单项表中插入记录
     */
    void addOrderItem(OrderItem item) throws SQLException;
    /**
     * 查找指定用户的所有订单
     */
    List<Order> findOrderByUserId(int user_id);
    /**
     * 查询指定订单的所有订单项
     */
    List<OrderItem> findOrderItems(String id);
}
