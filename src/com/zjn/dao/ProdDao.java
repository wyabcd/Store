package com.zjn.dao;

import com.zjn.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProdDao extends Dao{
    /**
     * 添加商品
     */
    void addProd(Product prod);
    /**
     * 查询所有
     */
    List<Product> findAllProd();
    /**
     * 根据id查找商品
     */
    Product findProdById(String id);
    /**
     * 扣除商品库存数量
     */
    void delPnum(String product_id,int buynum) throws SQLException;
    /**
     * 加回产品库存数量
     * @param product_id
     * @param buynum
     */
    void addPnum(String product_id, int buynum);
}
