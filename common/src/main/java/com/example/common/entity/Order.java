package com.example.common.entity;

import com.example.common.enums.OrderStatus;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 13:08
 */
@Data
public class Order {

    private long id;

    private long pId;

    private String pName;

    private long pQuantity;

    private OrderStatus status;
}
