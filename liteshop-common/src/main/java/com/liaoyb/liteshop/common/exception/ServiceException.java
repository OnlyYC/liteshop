package com.liaoyb.liteshop.common.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author liaoyb
 */
@Getter
public class ServiceException extends RuntimeException {
    /**
     * 错误标识
     */
    private String key;
    /**
     * 详细异常信息
     */
    private String error;
    /**
     * 可读的异常信息
     */
    private String msg;

    public ServiceException(String msg, String key, String error) {
        super(error);
        this.key = key;
        this.error = error;
        this.msg = msg;
    }

    public ServiceException(String msg, String key, Throwable cause) {
        super(msg, cause);
        this.key = key;
        this.error = cause.getLocalizedMessage();
        this.msg = msg;
    }
}
