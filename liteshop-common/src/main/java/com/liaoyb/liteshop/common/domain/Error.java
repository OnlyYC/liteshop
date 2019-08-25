package com.liaoyb.liteshop.common.domain;

import lombok.Data;

/**
 * 错误
 *
 * @author liaoyb
 */
@Data
public class Error {
    /**
     * 错误标识
     */
    private String key;
    /**
     * 错误消息
     */
    private String message;

    public Error(String key, String message) {
        this.key = key;
        this.message = message;
    }
}
