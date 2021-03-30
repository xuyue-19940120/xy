package com.dkf.springcloud.controller;

import com.dkf.springcloud.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "paymentInfo_timeoutHandler")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/customer/payment/hystrix/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id){
        log.info("paymentInfo_OKKKKOKKK");
        return orderService.paymentInfo_OK(id);
    }

//    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
//            //设置峰值，超过 3 秒，就会调用兜底方法
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    @GetMapping("/customer/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id")Integer id){
        log.info("paymentInfo_timeout");
        return orderService.paymentInfo_Timeout(id);
    }

    public String paymentInfo_timeoutHandler(){
        log.info("paymentInfo_timeout--handler");
        return "访问 payment 失败----人工报错";
    }


}
