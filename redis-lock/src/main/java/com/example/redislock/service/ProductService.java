package com.example.redislock.service;

import com.example.common.entity.Product;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 12:05
 */
public interface ProductService {
    Product findById(long id);

    /**
     * 扣减库存
     *
     * @param productId
     * @param quantity
     * @return
     */
    boolean reduceStock(long productId, int quantity);

    List<Product> queryList();

    boolean updateStockById(long productId, long l);
}
