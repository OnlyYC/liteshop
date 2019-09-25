package com.liaoyb.liteshop.user.portal.config;

import com.liaoyb.liteshop.common.config.LiteshopRestConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 应用配置
 *
 * @author liaoyb
 */
@Configuration
@Import(value = LiteshopRestConfig.class)
public class AppConfig {

}
