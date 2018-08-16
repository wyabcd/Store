package com.zjn.dao;


import com.zjn.domain.Order;
import com.zjn.domain.OrderItem;
import com.zjn.domain.SaleInfo;

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

    /**
     * 删除指定id订单所关联的所有订单项
     * @param order_id 订单id
     */
    void delOrderItem(String order_id);

    /**
     * 删除指定id的订单
     * @param id
     */
    void delOrder(String id);

    /**
     * 根据id查询订单
     * @param order_id
     * @return
     */
    Order findOrderById(String order_id);

    /**
     * 查询销售榜单
     * @return
     */
    List<SaleInfo> saleList();
}
