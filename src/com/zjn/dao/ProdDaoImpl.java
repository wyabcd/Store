package com.zjn.dao;

import com.zjn.domain.Product;

import com.zjn.util.TransactionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProdDaoImpl implements ProdDao {

    /**
     * 添加商品
     *
     * @param prod
     */
    @Override
    public void addProd(Product prod) {
        String sql="insert into products values (?,?,?,?,?,?,?)";
        try {
            QueryRunner runner=new QueryRunner(TransactionManager.getSource());
            runner.update(sql,prod.getId(),prod.getName(),prod.getPrice(),prod.getCategory(),prod.getPnum(),prod.getImgurl(),prod.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有
     */
    @Override
    public List<Product> findAllProd() {
        String sql="select * from products";
        try {
            QueryRunner runner=new QueryRunner(TransactionManager.getSource());
            return runner.query(sql,new BeanListHandler<Product>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id查找商品
     *
     * @param id
     */
    @Override
    public Product findProdById(String id) {
         String sql="select * from products where id=?";
        try {
            QueryRunner runner=new QueryRunner(TransactionManager.getSource());
            return runner.query(sql,new BeanHandler<Product>(Product.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 扣除商品库存数量
     *
     * @param product_id
     * @param buynum
     */
    @Override
    public void delPnum(String product_id, int buynum) throws SQLException {
        String sql="update products set pnum=pnum-? where id=? and pnum-?>=0";
        QueryRunner runner=new QueryRunner(TransactionManager.getSource());
        int count=runner.update(sql,buynum,product_id,buynum);
        if(count<=0){
            throw new SQLException("库存不足");
        }
    }

    @Override
    public void addProd(String product_id) {
        try {
            String sql="update products set pnum=pnum+? where id=?";
            QueryRunner runner=new QueryRunner(TransactionManager.getSource());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
