package com.zjn.service;

import com.zjn.dao.ProdDao;
import com.zjn.domain.Product;
import com.zjn.factory.BasicFactory;

import java.util.List;
import java.util.UUID;

public class ProdServiceImpl implements ProdService {
    ProdDao dao=BasicFactory.getFactory().getDao(ProdDao.class);
    /**
     * 添加商品
     *
     * @param prod
     */
    @Override
    public void addProd(Product prod) {
        prod.setId(UUID.randomUUID().toString());
        dao.addProd(prod);
    }

    /**
     * 查询所有
     */
    @Override
    public List<Product> findAllProd() {
        return dao.findAllProd();
    }

    /**
     * 根据id查找商品
     *
     * @param id
     */
    @Override
    public Product findProdById(String id) {
        return dao.findProdById(id);
    }
}
