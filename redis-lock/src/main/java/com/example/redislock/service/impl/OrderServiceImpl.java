package com.example.redislock.service.impl;

import com.example.common.constant.ProductConstant;
import com.example.common.entity.Product;
import com.example.common.enums.RespCode;
import com.example.common.exception.ApiException;
import com.example.common.exception.StockOverException;
import com.example.common.mapper.OrderMapper;
import com.example.common.util.IdGenerator;
import com.example.redislock.service.OrderService;
import com.example.redislock.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
    RedissonClient redissonClient;

    @Autowired
    ProductService productService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    IdGenerator idGenerator;

    @Override
    public long create(long productId, int quantity) throws InterruptedException {
        long orderId = idGenerator.nextId();

        RLock stockLock = redissonClient.getLock(String.format(ProductConstant.PRODUCT_STOCK_LOCK, productId));
        try {
            stockLock.tryLock(orderId, 2L, TimeUnit.SECONDS);
            Product product = productService.findById(productId);

            // 检查库存，库存不足抛出异常
            if(product.getStock() - quantity < 0){
                throw new StockOverException();
            }

            // 扣减库存
            if(!productService.updateStockById(productId, product.getStock() - quantity)){
                throw new ApiException(RespCode.PRODUCT_STOCK_OVER);
            }

            Product product2 = productService.findById(productId);
            System.out.println("扣减前库存：" + product.getStock() + "，扣减后库存：" + product2.getStock());

            // 下单
            if(orderMapper.create(orderId, productId, product.getName(), quantity) <= 0){
                throw new ApiException(RespCode.ORDER_CREATE_FAILED);
            }

            return orderId;
        } finally {
            // 如果锁还在，则释放锁
            if (stockLock.isLocked()) {
                stockLock.unlock();
            }
        }
    }
}
