package com.liaoyb.liteshop.common.util;

import com.liaoyb.liteshop.common.domain.EmptyMeta;
import com.liaoyb.liteshop.common.domain.Response;
import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * Response工具
 *
 * @author liaoyb
 */
public class ResponseUtil {
    /**
     * 包装响应内容，如果数据为null，返回NOT_FOUND
     *
     * @param data
     * @return
     */
    public static <T> Response<T, EmptyMeta> wrapOrNotFound(T data) {
        return wrapOrNotFound(Optional.ofNullable(data), null);
    }

    /**
     * 包装响应内容，如果数据为null，返回NOT_FOUND
     *
     * @param maybeData
     * @return
     */
    public static <T> Response<T, EmptyMeta> wrapOrNotFound(Optional<T> maybeData) {
        return wrapOrNotFound(maybeData, null);
    }

    /**
     * 包装响应内容
     *
     * @param maybeData
     * @param meta
     * @return
     */
    public static <T, M extends EmptyMeta> Response<T, M> wrapOrNotFound(Optional<T> maybeData, M meta) {
        return maybeData.map(data -> Response.success(data, meta))
                .orElse(Response.error(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase()));
    }
}
