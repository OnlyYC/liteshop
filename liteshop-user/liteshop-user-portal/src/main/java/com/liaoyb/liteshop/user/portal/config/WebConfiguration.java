package com.liaoyb.liteshop.user.portal.config;

import com.liaoyb.liteshop.common.web.mvc.RewriteErrorController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liaoyb
 */
@Slf4j
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    /**
     * 错误响应重写
     */
    @Bean
    public RewriteErrorController rewriteErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        return new RewriteErrorController(errorAttributes, serverProperties);
    }
}
