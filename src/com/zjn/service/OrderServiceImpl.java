package com.zjn.service;

import com.zjn.dao.OrderDao;
import com.zjn.dao.ProdDao;
import com.zjn.dao.UserDao;
import com.zjn.domain.*;
import com.zjn.factory.BasicFactory;
import org.apache.commons.beanutils.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = BasicFactory.getFactory().getDao(OrderDao.class);
    ProdDao prodDao = BasicFactory.getFactory().getDao(ProdDao.class);
    UserDao userDao = BasicFactory.getFactory().getDao(UserDao.class);


    /**
     * 增加订单
     *
     * @param order
     */
    @Override
    public void addOrder(Order order) {
        try {
            //1.生成订单
            orderDao.addOrder(order);
            //2.生成订单项/扣除商品数量
            for (OrderItem item : order.getList()) {
                orderDao.addOrderItem(item);
                prodDao.delPnum(item.getProduct_id(), item.getBuynum());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询指定用户所有订单的方法
     *
     * @param user_id
     */
    @Override
    public List<OrderListForm> findOrders(int user_id) {
        try {
            List<OrderListForm> olfList = new ArrayList<OrderListForm>();
            //1.根据用户id查询这个id用户的所有订单
            List<Order> list = orderDao.findOrderByUserId(user_id);
            //2.遍历订单生成orserListForm对象，存入List中
            for (Order order : list) {
                //--设置订单信息
                OrderListForm olf = new OrderListForm();
                BeanUtils.copyProperties(olf, order);
                //--设置用户名
                User user = userDao.findUserById(order.getUser_id());
                olf.setUsername(user.getUsername());
                //--设置商品信息
                //--查询当前订单所有订单项
                List<OrderItem> itemList = orderDao.findOrderItems(order.getId());
                //--遍历所有订单项，获取商品id，查找对应的商品，存入list
                Map<Product, Integer> prodMap = new HashMap<Product, Integer>();
                for (OrderItem item : itemList) {
                    String prod_id = item.getProduct_id();
                    Product prod = prodDao.findProdById(prod_id);
                    prodMap.put(prod, item.getBuynum());
                }
                olf.setProdMap(prodMap);
                olfList.add(olf);
            }
            return olfList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
