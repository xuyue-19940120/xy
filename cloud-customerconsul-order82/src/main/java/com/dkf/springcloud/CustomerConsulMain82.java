package com.dkf.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CustomerConsulMain82 {

    public static void main(String[] args) {
        SpringApplication.run(CustomerConsulMain82.class,args);
    }
}
