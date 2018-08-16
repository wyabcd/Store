package com.zjn.service;

import com.zjn.annotation.Tran;
import com.zjn.domain.Order;
import com.zjn.domain.OrderListForm;
import com.zjn.domain.SaleInfo;

import java.beans.Transient;
import java.util.List;

public interface OrderService extends Service{
    /**
     * 增加订单
     */
    @Tran
    void addOrder(Order order);
    /**
     * 查询指定用户所有订单的方法
     */
    List<OrderListForm> findOrders(int user_id);

    /**
     * 根据订单编号删除订单
     * @param id
     */
    @Tran
    void delOrderByID(String id);
    /**
     * 根据id查询订单
     */
    Order findOrderById(String order_id);
    /**
     * 查询销售榜单
     */
    List<SaleInfo> saleList();
}
