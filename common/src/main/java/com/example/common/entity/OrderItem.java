package com.example.common.entity;

import java.io.Serializable;

/**
 * (OrderItem)实体类
 *
 * @author makejava
 * @since 2022-11-05 13:32:19
 */
public class OrderItem implements Serializable {
    private static final long serialVersionUID = -52500653128086876L;

    private Long id;
    /**
     * 订单ID
     */
    private Long oId;
    /**
     * 商品ID
     */
    private Long pId;
    /**
     * 商品名称
     */
    private String pName;
    /**
     * 商品数量
     */
    private Long pQuantity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOId() {
        return oId;
    }

    public void setOId(Long oId) {
        this.oId = oId;
    }

    public Long getPId() {
        return pId;
    }

    public void setPId(Long pId) {
        this.pId = pId;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public Long getPQuantity() {
        return pQuantity;
    }

    public void setPQuantity(Long pQuantity) {
        this.pQuantity = pQuantity;
    }

}

