package com.liaoyb.liteshop.common.web.mvc;

import com.liaoyb.liteshop.common.domain.EmptyMeta;
import com.liaoyb.liteshop.common.domain.Error;
import com.liaoyb.liteshop.common.domain.Response;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 错误响应重写,统一json格式
 *
 * @author liaoyb
 */
@Controller
@RequestMapping("/error")
public class RewriteErrorController extends AbstractErrorController {
    private ErrorProperties errorProperties;

    public RewriteErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        this(errorAttributes, serverProperties.getError(), Collections.emptyList());
    }

    private RewriteErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        this.errorProperties = errorProperties;
    }

    @Override
    public String getErrorPath() {
        return errorProperties.getPath();
    }

    // TODO 暂时只提供json格式的
    @RequestMapping
    @ResponseBody
    public Response<Map<String, Object>, EmptyMeta> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        Error error = new Error(String.valueOf(status.value()), status.getReasonPhrase());
        return Response.error(status.value(), status.getReasonPhrase(), body, null, error);
    }

    private boolean isIncludeStackTrace(HttpServletRequest request,
                                        MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.errorProperties.getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }
}
