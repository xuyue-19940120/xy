package com.dkf.springcloud.service;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * 可以正常访问的方法
     * @param id
     * @return
     */
    public String paymentinfo_Ok(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "--paymentInfo_OK，id:" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
            //设置峰值，超过 3 秒，就会调用兜底方法
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "6000")
    })
    public String paymentinfo_Timeout(Integer id){
        int interTime = 5; //5秒
        try{
            TimeUnit.SECONDS.sleep(interTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "--paymentInfo_Timeout，id:" + id + "耗时" + interTime + "秒钟--";
    }

    public String paymentInfo_timeoutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "--paymentInfo_Timeout--Handler，id:" + id;
    }

    //=====服务熔断
//    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
//            @HystrixProperty(name="circuitBreaker.enabled", value="true"),  // 是否开启断路器
//            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),  //请求次数
//            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000"), // 时间窗口期
//            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="60"),  // 失败率达到多少后跳闸
//            //整体意思：10秒内 10次请求，有6次失败，就跳闸
//    })
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback")
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("*****id，不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数，请稍后再试....";
    }
}
