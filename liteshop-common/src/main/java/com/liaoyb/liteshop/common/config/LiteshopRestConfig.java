package com.liaoyb.liteshop.common.config;

import com.liaoyb.liteshop.common.error.GlobalExceptionTranslator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * rest api的基本配置
 *
 * @author liaoyb
 */
@Configuration
@Import(value = GlobalExceptionTranslator.class)
public class LiteshopRestConfig {

}
