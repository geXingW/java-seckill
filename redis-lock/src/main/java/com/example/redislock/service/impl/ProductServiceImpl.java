package com.example.redislock.service.impl;

import com.example.common.entity.Product;
import com.example.common.mapper.ProductMapper;
import com.example.redislock.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 12:05
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public Product findById(long id) {
        return productMapper.queryById(id);
    }

    @Override
    public boolean reduceStock(long productId, int quantity) {
        return productMapper.reduceStockById(productId, quantity) > 0;
    }

    @Override
    public List<Product> queryList() {
        return productMapper.queryList();
    }

    @Override
    public boolean updateStockById(@Param("id") long id, @Param("quantity") long stock) {
        return productMapper.updateStockById(id, stock) > 0;
    }
}
