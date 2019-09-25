package com.liaoyb.liteshop.user.portal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 用户中心前台api
 *
 * @author liaoyb
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LiteshopUserPortalApp {
    private static final Logger log = LoggerFactory.getLogger(LiteshopUserPortalApp.class);

    public static void main(String[] args) {
        SpringApplication.run(LiteshopUserPortalApp.class, args);
    }
}
