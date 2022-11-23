package com.example.dbtransaction.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 13:03
 */
@Data
public class CreateOrderRequest {

    /**
     * 购买商品ID
     */
    private long productId;

    /**
     * 购买数量
     */
    private int quantity;
}
