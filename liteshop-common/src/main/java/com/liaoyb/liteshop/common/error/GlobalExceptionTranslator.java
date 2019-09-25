package com.liaoyb.liteshop.common.error;

import com.liaoyb.liteshop.common.domain.EmptyMeta;
import com.liaoyb.liteshop.common.domain.Error;
import com.liaoyb.liteshop.common.domain.Response;
import com.liaoyb.liteshop.common.exception.AuthenticationException;
import com.liaoyb.liteshop.common.exception.ServiceException;
import com.liaoyb.liteshop.common.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Collections;

/**
 * 全局异常处理
 *
 * @author liaoyb
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionTranslator {
    /**
     * 统一处理框架方法级别校验异常
     *
     * @param ex 框架方法级别校验异常
     * @return 统一异常处理结果, 422校验错误
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Response<Object, EmptyMeta> handleMethodValidationException(
            ConstraintViolationException ex) {
        return Response.error(HttpStatus.UNPROCESSABLE_ENTITY, ExceptionUtil.handleMethodValidationException(ex));
    }

    /**
     * 统一处理框架bean校验异常
     *
     * @param ex 框架bean校验异常
     * @return 统一异常处理结果, 422校验错误
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Object, EmptyMeta> handleBeanValidationException(
            MethodArgumentNotValidException ex) {
        return Response.error(HttpStatus.UNPROCESSABLE_ENTITY, ExceptionUtil.handleBeanValidationException(ex));
    }

    /**
     * 统一处理框架bean get/form校验异常
     *
     * @param ex 校验异常
     * @return 统一异常处理结果, 422校验错误
     */
    @ExceptionHandler(BindException.class)
    public Response<Object, EmptyMeta> handleBindException(BindException ex) {
        return Response.error(HttpStatus.UNPROCESSABLE_ENTITY, ExceptionUtil.handleBindException(ex));
    }

    /**
     * 统一处理框架MissingServletRequestParameterException校验异常
     *
     * @param ex 缺失请求参数校验异常
     * @return 统一异常处理结果, 422校验错误
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response<Object, EmptyMeta> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return Response.error(HttpStatus.UNPROCESSABLE_ENTITY, ExceptionUtil.handleMissingServletRequestParameterException(ex));
    }

    /**
     * 统一处理框架MissingServletRequestParameterException校验异常
     *
     * @param ex 缺失请求参数校验异常
     * @return 统一异常处理结果, 400校验错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Response<Object, EmptyMeta> handleError(MethodArgumentTypeMismatchException ex) {
        log.warn("Method Argument Type Mismatch", ex);
        return Response.error(HttpStatus.BAD_REQUEST, ExceptionUtil.handleMethodArgumentTypeMismatchException(ex));
    }

    /**
     * 统一处理框架MissingPathVariableException校验异常
     *
     * @param ex 缺失请求路径参数校验异常
     * @return 统一异常处理结果, 422校验错误
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public Response<Object, EmptyMeta> handleMissingPathVariableException(MissingPathVariableException ex) {
        return Response.error(HttpStatus.UNPROCESSABLE_ENTITY, ExceptionUtil.handleMissingPathVariableException(ex));
    }

    /**
     * 统一处理框架UnsatisfiedServletRequestParameterException校验异常
     *
     * @param ex 请求参数条件不满足校验异常
     * @return 统一异常处理结果, 422校验错误
     */
    @ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
    public Response<Object, EmptyMeta> handleUnsatisfiedServletRequestParameterException(UnsatisfiedServletRequestParameterException ex) {
        return Response.error(HttpStatus.UNPROCESSABLE_ENTITY, ExceptionUtil.handleUnsatisfiedServletRequestParameterException(ex));
    }

    /**
     * 参数异常
     *
     * @param ex 异常类
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Response<Object, EmptyMeta> handleError(IllegalArgumentException ex) {
        return Response.error(HttpStatus.BAD_REQUEST, Collections.singletonList(new Error("bad_param", ex.getLocalizedMessage())));
    }


    /**
     * 没有找到匹配的handler
     *
     * @param ex 异常类
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Response<Object, EmptyMeta> handleError(NoHandlerFoundException ex) {
        log.error("404 Not Found", ex);
        return Response.error(HttpStatus.NOT_FOUND, Collections.singletonList(new Error("not_found", ex.getLocalizedMessage())));
    }

    /**
     * 身份验证异常
     *
     * @param ex 异常类
     */
    @ExceptionHandler(AuthenticationException.class)
    public Response<Object, EmptyMeta> handleError(AuthenticationException ex) {
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR, "身份验证失败", Collections.singletonList(new Error("authentication failed", ex.getLocalizedMessage())));
    }

    /**
     * 业务异常
     *
     * @param ex 异常类
     * @return 错误响应
     */
    @ExceptionHandler(ServiceException.class)
    public Response<Object, EmptyMeta> handleServiceException(ServiceException ex) {
        log.error("业务异常===>" + ex.getMessage(), ex);
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMsg(), new Error(ex.getKey(), ex.getMsg()));
    }

    /**
     * 统一处理其余所有异常
     *
     * @param ex 异常类
     * @return 错误响应
     */
    @ExceptionHandler(Throwable.class)
    public Response<Object, EmptyMeta> handleAllException(Throwable ex) {
        log.error("运行时异常===>" + ex.getMessage(), ex);
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ExceptionUtil.handleAllException(ex));
    }
}
