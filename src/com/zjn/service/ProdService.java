package com.zjn.service;

import com.zjn.domain.Product;

import java.util.List;

public interface ProdService extends Service{
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
}
