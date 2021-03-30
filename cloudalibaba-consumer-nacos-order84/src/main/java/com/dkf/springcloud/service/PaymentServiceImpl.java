package com.dkf.springcloud.service;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dkf.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {

    @Override
    public CommonResult paymentSql(Long id) {
        return new CommonResult(414, "open-feign 整合 sentinel 实现的全局服务降级策略",null);
    }

    public CommonResult paymentSql(Long id,BlockException e) {
        return new CommonResult(414, "sentinel异常降级，我做的测试",null);
    }
}
