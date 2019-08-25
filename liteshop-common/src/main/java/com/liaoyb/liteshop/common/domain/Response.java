package com.liaoyb.liteshop.common.domain;

import com.liaoyb.liteshop.common.util.CollectionUtil;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 响应对象
 *
 * @author liaoyb
 */
@Data
public class Response<T, M extends EmptyMeta> implements Serializable {
    /**
     * 成功
     */
    public static final String SUCCESS_MESSAGE = "成功";
    /**
     * 失败
     */
    public static final String FAIL_MSG = "失败";
    /**
     * 响应码（前三位与http请求码一致，后面的为业务码）
     */
    private int code;
    /**
     * 人性化的错误信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 详细错误信息(一级一级传递，最后为当前错误信息)
     */
    private List<Error> errors;

    /**
     * 额外元数据
     */
    private M meta;

    public Response(int code, String message, T data, List<Error> errors, M meta) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.errors = errors;
        this.meta = meta;
    }

    /**
     * 成功
     *
     * @param message 消息
     * @param data    数据
     * @param meta    元数据
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> success(String message, T data, M meta) {
        return new Response<>(HttpStatus.OK.value(), message, data, null, meta);
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> success(T data, M meta) {
        return success(SUCCESS_MESSAGE, data, meta);
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> success(T data) {
        return success(SUCCESS_MESSAGE, data, null);
    }

    /**
     * 成功
     *
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> success() {
        return success(null);
    }

    /**
     * 错误
     *
     * @param code    响应码
     * @param message 消息
     * @param data    数据
     * @param meta    元数据
     * @param errors  错误列表
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> error(int code, String message, T data, M meta, List<Error> errors) {
        return new Response<>(code, message, data, errors, meta);
    }

    /**
     * 错误
     *
     * @param code    响应码
     * @param message 消息
     * @param data    数据
     * @param meta    元数据
     * @param errors  错误列表
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> error(int code, String message, T data, M meta, Error... errors) {
        return new Response<>(code, message, data, CollectionUtil.notNull(errors), meta);
    }

    /**
     * 错误
     *
     * @param code    响应码
     * @param message 消息
     * @param errors  错误列表
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> error(int code, String message, Error... errors) {
        return error(code, message, null, null, errors);
    }

    /**
     * 错误
     *
     * @param code    响应码
     * @param message 消息
     * @param errors  错误列表
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> error(int code, String message, List<Error> errors) {
        return error(code, message, null, null, errors);
    }

    /**
     * 错误
     *
     * @param httpStatus http响应
     * @param message    消息
     * @param errors     错误列表
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> error(HttpStatus httpStatus, String message, Error... errors) {
        return error(httpStatus.value(), message, errors);
    }

    /**
     * 错误
     *
     * @param httpStatus http响应
     * @param message    消息
     * @param errors     错误列表
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> error(HttpStatus httpStatus, String message, List<Error> errors) {
        return error(httpStatus.value(), message, CollectionUtil.notNull(errors));
    }

    /**
     * 错误
     *
     * @param httpStatus http响应
     * @param errors     错误列表
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> error(HttpStatus httpStatus, List<Error> errors) {
        errors = CollectionUtil.notNull(errors);
        return error(httpStatus.value(), getFailMsg(errors), errors);
    }

    /**
     * 错误
     *
     * @param httpStatus http响应
     * @param errors     错误列表
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> error(HttpStatus httpStatus, Error... errors) {
        List<Error> errorList = CollectionUtil.notNull(errors);
        return error(httpStatus.value(), getFailMsg(errorList), errorList);
    }

    /**
     * 错误
     *
     * @param httpStatus http响应
     * @param message    错误信息
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> error(HttpStatus httpStatus, String message) {
        return error(httpStatus.value(), message, Collections.emptyList());
    }

    /**
     * 错误（服务器错误500）
     *
     * @param message 错误信息
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> error(String message) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    /**
     * 错误（服务器错误500）
     *
     * @param message 错误信息
     * @param errors  错误列表
     * @return 响应
     */
    public static <T, M extends EmptyMeta> Response<T, M> error(String message, List<Error> errors) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, errors);
    }

    private static String getFailMsg(List<Error> errors) {
        return CollectionUtils.isEmpty(errors) ? FAIL_MSG : errors.stream()
                .map(Error::getMessage)
                .reduce((err1, err2) -> err1 + "\\n" + err2)
                .orElse(FAIL_MSG);
    }

}
