package com.example.dbtransaction.controller;

import com.example.common.enums.RespCode;
import com.example.common.service.RedisService;
import com.example.common.util.IdGenerator;
import com.example.common.util.R;
import com.example.dbtransaction.dto.CreateOrderRequest;
import com.example.dbtransaction.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 13:02
 */
@Slf4j
@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    RedisService redisService;

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping
    @ResponseBody
    public R create(@RequestBody CreateOrderRequest request) {
        try {
            // 商品ID
            long productId = request.getProductId();
            if (productId == 0) {
                return R.failure(RespCode.PRODUCT_NOT_EXIST);
            }

            // 秒杀数量
            int quantity = request.getQuantity();
            long orderId = orderService.create(productId, quantity);

            return R.ok(orderId);
        } catch (Exception e) {
            log.error("Create order error: {}", e.getMessage());
            return R.failure(RespCode.ORDER_CREATE_FAILED);
        }
    }

}
