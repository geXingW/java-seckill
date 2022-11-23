package com.example.optimistic.lock.service;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/4
 * @time: 13:16
 */
public interface OrderService {
    long create(long productId, int quantity) throws InterruptedException;
}
