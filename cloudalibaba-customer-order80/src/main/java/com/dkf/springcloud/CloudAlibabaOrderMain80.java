package com.dkf.springcloud;

import ch.qos.logback.core.db.DataSourceConnectionSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class CloudAlibabaOrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaOrderMain80.class,args);
    }
}
