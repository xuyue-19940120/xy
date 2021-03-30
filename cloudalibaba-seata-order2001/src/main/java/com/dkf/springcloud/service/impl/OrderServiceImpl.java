package com.dkf.springcloud.service.impl;

import com.dkf.springcloud.dao.OrderDao;
import com.dkf.springcloud.domain.Order;
import com.dkf.springcloud.service.AccountService;
import com.dkf.springcloud.service.OrderService;
import com.dkf.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl  implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "dkf-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("--------》 开始创建订单");
        orderDao.create(order);
        log.info("--------》 订单微服务开始调用库存，做扣减---Count-");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("--------》 订单微服务开始调用库存，库存扣减完成！！");
        log.info("--------》 订单微服务开始调用账户，账户扣减---money-");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("--------》 订单微服务开始调用账户，账户扣减完成!!");
        //修改订单状态，从0到1
        log.info("--------》 订单微服务修改订单状态，start");
        orderDao.update(order.getUserId(),0);
        log.info("--------》 订单微服务修改订单状态，end");

        log.info("--订单结束--");
    }

    @Override
    public void update(Long userId, Integer status) {

    }
}
