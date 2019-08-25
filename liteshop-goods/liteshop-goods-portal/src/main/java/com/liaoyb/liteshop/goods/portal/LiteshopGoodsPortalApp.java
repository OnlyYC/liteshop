package com.liaoyb.liteshop.goods.portal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 商品中心前台api
 *
 * @author liaoyb
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LiteshopGoodsPortalApp {
    private static final Logger log = LoggerFactory.getLogger(LiteshopGoodsPortalApp.class);

    public static void main(String[] args) {
        SpringApplication.run(LiteshopGoodsPortalApp.class, args);
    }
}
