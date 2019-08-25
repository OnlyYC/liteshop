package com.liaoyb.liteshop.common.util;

import com.liaoyb.liteshop.common.domain.Error;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 异常工具类
 *
 * @author liaoyb
 */
public class ExceptionUtil {
    /**
     * 处理方法级别校验异常
     *
     * @param ex 校验异常
     * @return 错误列表
     */
    public static List<Error> handleMethodValidationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return Collections.emptyList();
        }
        List<Error> errors = new ArrayList<>(constraintViolations.size());
        for (ConstraintViolation<?> cv : constraintViolations) {
            String path = cv.getPropertyPath().toString();
            String message = cv.getMessage();
            errors.add(new Error(path, message));
        }
        return errors;
    }


    /**
     * 处理方法参数bean校验异常
     *
     * @param ex 校验异常
     * @return 错误列表
     */
    public static List<Error> handleBeanValidationException(MethodArgumentNotValidException ex) {
        return handleFieldErrors(ex.getBindingResult().getFieldErrors());
    }

    /**
     * 处理方法参数bean get/form校验异常
     *
     * @param ex 校验异常
     * @return 错误列表
     */
    public static List<Error> handleBindException(BindException ex) {
        return handleFieldErrors(ex.getFieldErrors());
    }

    private static List<Error> handleFieldErrors(List<FieldError> fieldErrors) {
        if (CollectionUtils.isEmpty(fieldErrors)) {
            return Collections.emptyList();
        }
        List<Error> errors = new ArrayList<>(fieldErrors.size());
        for (FieldError fieldError : fieldErrors) {
            String path = fieldError.getObjectName() + "." + fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errors.add(new Error(path, message));
        }
        return errors;
    }


    /**
     * 处理请求参数缺失校验异常
     *
     * @param ex 校验异常
     * @return 错误信息
     */
    public static Error handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return new Error(ex.getParameterName(), ex.getMessage());
    }

    /**
     * 处理请求路径参数缺失校验异常
     *
     * @param ex 校验异常
     * @return 错误信息
     */
    public static Error handleMissingPathVariableException(MissingPathVariableException ex) {
        return new Error(ex.getVariableName(), ex.getMessage());
    }

    /**
     * 处理请求参数条件不满足校验异常
     *
     * @param ex 校验异常
     * @return 错误信息
     */
    public static Error handleUnsatisfiedServletRequestParameterException(UnsatisfiedServletRequestParameterException ex) {
        return new Error(StringUtil.join(ex.getParamConditions(), ","), ex.getMessage());
    }

    /**
     * 处理其余所有异常
     *
     * @param ex 异常
     * @return 错误列表
     */
    public static List<Error> handleAllException(Exception ex) {
        String key = "";
        String message = ExceptionUtils.getRootCauseMessage(ex);
        return Collections.singletonList(new Error(key, message));
    }
}
