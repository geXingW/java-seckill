package com.example.redislock.controller;

import com.example.common.enums.RespCode;
import com.example.common.util.R;
import com.example.redislock.dto.CreateOrderRequest;
import com.example.redislock.service.OrderService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/6
 * @time: 11:38
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    OrderService orderService;

    @PostMapping
    public R create(@RequestBody CreateOrderRequest request){
        try {
            long orderId = orderService.create(request.getProductId(), request.getQuantity());
            return R.ok(orderId);
        }catch (Exception e){
            return R.failure(RespCode.ORDER_CREATE_FAILED);
        }
    }
}
