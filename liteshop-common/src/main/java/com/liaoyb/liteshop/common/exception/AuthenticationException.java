package com.liaoyb.liteshop.common.exception;

/**
 * 身份验证异常
 *
 * @author liaoyanbo
 */
public class AuthenticationException extends ServiceException {
    private static final long serialVersionUID = 1L;
    private static final String ERROR_MESSAGE = "【身份验证异常】";


    public AuthenticationException(String message, String error) {
        super(message, "authentication failed", ERROR_MESSAGE + error);
    }

    public AuthenticationException(String message) {
        super(message, "authentication failed", ERROR_MESSAGE + message);
    }
}
