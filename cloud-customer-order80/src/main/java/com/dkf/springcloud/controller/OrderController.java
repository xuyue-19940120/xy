package com.dkf.springcloud.controller;

import com.dkf.springcloud.entities.CommonResult;
import com.dkf.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENY_URL = "http://CLOUD-PROVIDER-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/customer/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for(String service: services){
            log.info("*****service: " + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-ORDER-SERVICE");
        for(ServiceInstance serviceInstance:instances){
            log.info(serviceInstance.getServiceId() + "\t" + serviceInstance.getHost()
                    + "\t" + serviceInstance.getPort() + "\t" + serviceInstance.getUri());
        }
        return this.discoveryClient;
    }

    @PostMapping("customer/payment/create")
    public CommonResult<Payment> create (Payment payment){
        return restTemplate.postForObject(PAYMENY_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("customer/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENY_URL + "/payment/" + id, CommonResult.class);
    }

    @GetMapping("customer/payment/forEntity/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id")Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENY_URL + "/payment/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444, "操作失败");
        }
    }

    //==========zipkin + sleuth
    @GetMapping("/customer/zipkin")
    public String testzipkin(){
        String result = restTemplate.getForObject(PAYMENY_URL+"/payment/zipkin", String.class);
        return result;
    }

}
