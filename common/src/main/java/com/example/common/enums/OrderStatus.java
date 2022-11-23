package com.example.common.enums;

import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/5
 * @time: 12:01
 */
@Getter
public enum OrderStatus {
    /**
     * 待支付
     */
    UN_PAY(0, "待支付"),

    /**
     * 已支付
     */
    PAID(1, "已支付"),
    ;

    /**
     * 状态表示
     */
    private int code;

    /**
     * 状态字符串
     */
    private String message;

    OrderStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
