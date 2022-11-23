package com.example.common.constant;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/6
 * @time: 10:32
 */
public interface OrderConstant {
    String REDIS_ORDER_CREATE_LOCK = "order:create-lock";

    Integer REDIS_ORDER_CREATE_LOCK_TTL = 2;

}
