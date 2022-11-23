package com.example.dbtransaction.service.impl;

import com.example.common.entity.Product;
import com.example.common.mapper.ProductMapper;
import com.example.dbtransaction.service.ProductService;
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
}
