package com.dkf.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id")Integer id){
        return "nacos registry , serverPort : " + serverPort + ", id: " + id;
    }

    @GetMapping("/nacos/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }

}
