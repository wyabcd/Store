package com.zjn.dao;

import com.zjn.domain.Order;
import com.zjn.domain.OrderItem;
import com.zjn.domain.SaleInfo;
import com.zjn.util.TransactionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    /**
     * 在订单表中插入记录
     *
     * @param order
     */
    @Override
    public void addOrder(Order order) throws SQLException {
        String sql = "insert into orders values (?,?,?,?,null,?)";
        QueryRunner runner = new QueryRunner(TransactionManager.getSource());
        runner.update(sql, order.getId(), order.getMoney(), order.getReceiverinfo(), order.getPaystate(), order.getUser_id());
    }


    /**
     * 在订单项表中插入记录
     *
     * @param item
     */
    @Override
    public void addOrderItem(OrderItem item) throws SQLException {
        String sql = "insert into orderitem values(?,?,?)";
        QueryRunner runner = new QueryRunner(TransactionManager.getSource());
        runner.update(sql, item.getOrder_id(), item.getProduct_id(), item.getBuynum());
    }

    /**
     * 查找指定用户的所有订单
     *
     * @param user_id
     */
    @Override
    public List<Order> findOrderByUserId(int user_id) {
        String sql = "select * from orders where user_id=?";
        try {
            QueryRunner runner = new QueryRunner(TransactionManager.getSource());
            return runner.query(sql, new BeanListHandler<Order>(Order.class), user_id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 查询指定订单的所有订单项
     *
     * @param order_id
     */
    @Override
    public List<OrderItem> findOrderItems(String order_id) {
        String sql = "select * from orseritem where order_id=?";
        try {
            QueryRunner runner = new QueryRunner(TransactionManager.getSource());
            return runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), order_id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除指定id订单所关联的所有订单项
     *
     * @param order_id 订单id
     */
    @Override
    public void delOrderItem(String order_id) {
        String sql="delete from orderitem where order_id=?";
        try {
            QueryRunner runner=new QueryRunner(TransactionManager.getSource());
            runner.update(sql,order_id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除指定id的订单
     *
     * @param id
     */
    @Override
    public void delOrder(String id) {
        String sql="delete from orders where id=?";
        try {
            QueryRunner runner=new QueryRunner(TransactionManager.getSource());
            runner.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id查询订单
     *
     * @param order_id
     * @return
     */
    @Override
    public Order findOrderById(String order_id) {
         String sql="select * from orders where id=?";
        try {
            QueryRunner runner=new QueryRunner(TransactionManager.getSource());
            return runner.query(sql,new BeanHandler<Order>(Order.class),order_id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询销售榜单
     *
     * @return
     */
    @Override
    public List<SaleInfo> saleList() {
        String sql=
                "select products.id prod_id,products.name,prod_name,sum(orderitem.buynum),sale_num"+
                        "from orders,orderitem,products"+
                        "where"+
                        "orders.id=orderitem.order_id"+
                        "and"+
                        "orderitem.product_id=products.id"+
                        "group by products.id"+
                        "order by sale_num desc";
        try {
            QueryRunner runner=new QueryRunner(TransactionManager.getSource());
            return runner.query(sql,new BeanListHandler<SaleInfo>(SaleInfo.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

