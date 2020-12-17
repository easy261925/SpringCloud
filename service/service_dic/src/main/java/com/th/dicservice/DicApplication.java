package com.th.dicservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cc
 * @date 2020-11-03-15:35
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.th"})
@EnableDiscoveryClient
@EnableFeignClients
public class DicApplication {
    public static void main(String[] args) {
        SpringApplication.run(DicApplication.class, args);
    }
}
