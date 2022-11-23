package com.example.optimistic.lock.service.impl;

import com.example.common.entity.Order;
import com.example.common.mapper.OrderMapper;
import com.example.common.util.IdGenerator;
import com.example.optimistic.lock.service.OrderService;
import com.example.optimistic.lock.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 13:16
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductService productService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    IdGenerator idGenerator;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long create(long productId, int quantity) throws InterruptedException {
        if(!productService.reduceStock(productId, quantity)){
            throw new RuntimeException("库存扣减失败！");
        }

        long orderId = idGenerator.nextId();
        if(orderMapper.create(orderId, productId, "", quantity) <= 0) {
            throw new RuntimeException("订单创建失败！");
        }

        return orderId;
    }
}
