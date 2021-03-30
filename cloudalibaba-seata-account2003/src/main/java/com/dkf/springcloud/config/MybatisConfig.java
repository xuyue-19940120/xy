package com.dkf.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.dkf.springcloud.alibaba.dao"})
public class MybatisConfig {
}
