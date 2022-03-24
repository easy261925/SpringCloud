package com.th.settingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cc
 * @date 2020-12-15-下午3:02
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.th"})
@EnableDiscoveryClient
public class SettingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SettingApplication.class, args);
    }
}
