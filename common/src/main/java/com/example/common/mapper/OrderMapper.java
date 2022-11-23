package com.example.common.mapper;

import com.example.common.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 13:12
 */
@Mapper
public interface OrderMapper {
    Order queryById(@Param("id") long id);

    /**
     * 创建订单
     *
     * @param orderId 订单号
     * @param productId 商品ID
     * @param productName 商品名
     * @param quantity 商品数量
     * @return 数据库影响行数
     */
    int create(@Param("id") long orderId, @Param("pId") long productId, @Param("pName") String productName, @Param("pQuantity") int quantity);
}
