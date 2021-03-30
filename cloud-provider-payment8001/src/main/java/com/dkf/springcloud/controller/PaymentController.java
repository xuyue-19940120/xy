package com.dkf.springcloud.controller;


import com.dkf.springcloud.entities.CommonResult;

import com.dkf.springcloud.entities.Payment;
import com.dkf.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("****插入结果：" + result);
        if(result > 0){
            return new CommonResult(200, "插入数据库成功", result);
        }
        return new CommonResult(444, "插入数据库失败", null);
    }

    @GetMapping(value = "/payment/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("****查询结果：" + result);
        if(result != null){
            return new CommonResult(200, "查询成功", result);
        }
        return new CommonResult(444, "没有对应id的记录", null);
    }

    @GetMapping("/payment/get/{id}")
    public String getGateWayTest1(@PathVariable("id") Integer id){
        return "***8001 get方法收到请求，参数id为：" + id;
    }

    @GetMapping("/payment/lb/{id}")
    public String getGateWayTest2(@PathVariable("id") Integer id){
        return "---8001 lb方法受到请求，参数id为：" + id;
    }


    @GetMapping("/payment/zipkin")
    public String testzipkin(){
        return "zipkin ------";
    }
}
