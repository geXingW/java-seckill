package com.example.common.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 21:03
 */
@Getter
public enum RespCode {
    // 正常响应
    SUCCESS(200000, "success"),

    // 异常响应
    TOO_MANY_REQUEST(400001, "操作过于频繁，请稍后重试！"),

    // 资源不存在
    PRODUCT_NOT_EXIST(404001, "商品信息不存在！"),
    ORDER_NOT_EXIST(404002, "订单信息不存在！"),

    // 请求参数异常
    PRODUCT_STOCK_OVER(404002, "商品库存不足！"),

    // 请求错误

    // 创建失败
    ORDER_CREATE_FAILED(201001, "下单失败！"),
    ;

    private final int code;
    private final String message;

    RespCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
