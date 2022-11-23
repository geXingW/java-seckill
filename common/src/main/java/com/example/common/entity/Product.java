package com.example.common.entity;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 11:57
 */
@Data
public class Product {

    private long id;

    private String name;

    private int stock;

    private int saleCnt;

    private long version;
}
