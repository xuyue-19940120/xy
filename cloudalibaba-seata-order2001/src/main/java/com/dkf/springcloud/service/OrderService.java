package com.dkf.springcloud.service;
import com.dkf.springcloud.domain.Order;
public interface OrderService {


    void create(Order order);

    void update(Long userId, Integer status);
}
