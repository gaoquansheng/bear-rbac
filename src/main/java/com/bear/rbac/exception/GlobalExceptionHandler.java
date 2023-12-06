package com.bear.rbac.exception;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.bear.rbac.common.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author bear
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private HttpServletRequest request;

    /**
     * 处理@RequestParam @PathVariable注解传递的Date参数
     * @param webDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) {
                setValue(DateUtil.parse(text));
            }
        });
        webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) {
                DateTime parse = DateUtil.parse(text);
                if (parse == null) {
                    return;
                }
                LocalDate localDate = LocalDate.ofInstant(parse.toInstant(), ZoneId.systemDefault());
                setValue(localDate);
            }
        });
        webDataBinder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) {
                DateTime parse = DateUtil.parse(text);
                if (parse == null) {
                    return;
                }
                LocalDateTime localDateTime = LocalDateTime.ofInstant(parse.toInstant(), ZoneId.systemDefault());
                setValue(localDateTime);
            }
        });
    }

    @ExceptionHandler(BusinessException.class)
    public Response handlerBusinessException(BusinessException e) {

        log.error(e.getMessage());
        return Response.failed(e.getMessage());
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {

        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return Response.failed(e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Response handleRuntimeException(RuntimeException e) {

        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return Response.failed(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {

        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return Response.failed(e.getMessage());
    }
}
