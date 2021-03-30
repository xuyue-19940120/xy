package com.dkf.springcloud.service;

import com.dkf.springcloud.entities.Payment;
import org.springframework.stereotype.Service;
import com.dkf.springcloud.dao.PaymentMapper;

import javax.annotation.Resource;

@Service
public class PaymentServiceimpl implements PaymentService{

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public int create(Payment payment) {
        return paymentMapper.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
