package com.dkf.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dkf.springcloud.entities.CommonResult;
import com.dkf.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consutomer/payment/get/{id}")
    @SentinelResource(value = "fallback", fallback = "handleFallback") //fallback只处理业务异常
    public CommonResult getPayment(@PathVariable("id")Long id){
        if(id >= 4){
            throw new IllegalArgumentException("非法参数异常...");
        }else {
            return paymentService.paymentSql(id);
        }
    }
    //====fallback
    public CommonResult handleFallback(@PathVariable("id")Long id, Throwable e){
        return new CommonResult(414, "---非法参-------fallback的提示，数异常--", e);
    }

    //====blockHandler                                       blockHandler的方法必须有这个参数
    public CommonResult handleblockHandler(@PathVariable("id")Long id, BlockException e){
        return new CommonResult(414, "---非法参数异常--", e);
    }
}
