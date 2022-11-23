package com.example.dbtransaction.service.impl;

import com.example.common.constant.OrderConstant;
import com.example.common.entity.Product;
import com.example.common.enums.RespCode;
import com.example.common.exception.ApiException;
import com.example.common.exception.StockOverException;
import com.example.common.mapper.OrderMapper;
import com.example.common.util.IdGenerator;
import com.example.dbtransaction.service.OrderService;
import com.example.dbtransaction.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

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
    public long create(long productId, int quantity) {
        Product product = productService.findById(productId);
        if (product == null) {
            throw new ApiException(RespCode.PRODUCT_NOT_EXIST);
        }

        return create(productId, product.getName(), quantity);
    }

    @Transactional(rollbackFor = Exception.class)
    public long create(long productId, String productName, int quantity) {
        long orderId = idGenerator.nextId();

        // 避免重复下单，2S内同样请求直接返回

        // Lua脚本
        String script = "if redis.call('SET', KEYS[1], ARGV[1], 'EX', ARGV[2], 'NX') then return ARGV[1] else return redis.call('GET', KEYS[1]) end";
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);

        // 脚本参数
        List<String> keys = Arrays.asList(OrderConstant.REDIS_ORDER_CREATE_LOCK, "");
        Long lockedOrderId = (Long)redisTemplate.execute(redisScript, keys, orderId, OrderConstant.REDIS_ORDER_CREATE_LOCK_TTL);

        // 如果在指定时间内，同一用户产生多次提交，则哦判定
        if(!lockedOrderId.equals(orderId)){
            return lockedOrderId;
        }

        // 库存扣减
        if (!productService.reduceStock(productId, quantity)) {
            // 扣减失败，抛出异常，DB事务回滚
            throw new StockOverException(RespCode.PRODUCT_STOCK_OVER);
        }

        // 保存订单
        if (orderMapper.create(orderId, productId, productName, quantity) <= 0) {
            throw new ApiException(RespCode.ORDER_CREATE_FAILED);
        }

        return orderId;
    }
}
