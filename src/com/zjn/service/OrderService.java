package com.zjn.service;

import com.zjn.annotation.Tran;
import com.zjn.domain.Order;
import com.zjn.domain.OrderListForm;

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
}
