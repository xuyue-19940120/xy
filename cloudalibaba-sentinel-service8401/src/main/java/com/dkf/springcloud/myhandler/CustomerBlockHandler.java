package com.dkf.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dkf.springcloud.entities.CommonResult;
import com.dkf.springcloud.entities.Payment;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException e){
        return new CommonResult(414, "用户自定义的全局降级处理类----1", new Payment(2525L, "serialdkf1"));
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(414, "用户自定义的全局降级处理类====2", new Payment(2525L, "serialdkf1"));
    }
}
