package com.liaoyb.liteshop.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 *
 * @author liaoyb
 */
@SpringBootApplication
@EnableEurekaServer
public class LiteshopRegistryApp {
    private static final Logger log = LoggerFactory.getLogger(LiteshopRegistryApp.class);

    public static void main(String[] args) {
        SpringApplication.run(LiteshopRegistryApp.class, args);
    }
}
