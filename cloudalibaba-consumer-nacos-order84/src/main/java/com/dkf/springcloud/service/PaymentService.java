package com.dkf.springcloud.service;


import com.dkf.springcloud.entities.CommonResult;
import com.dkf.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider", fallback = PaymentServiceImpl.class)
public interface PaymentService {

    @GetMapping("/payment/get/{id}")
    public CommonResult paymentSql(@PathVariable("id")Long id);
}
