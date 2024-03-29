package com.bear.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bear.enums.ResponseEnum;
import lombok.Data;

/**
 * @author bear
 */
@Data
public class Response<T> {

    /**
     * 状态码
     */
    private int code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据封装
     */
    private T data;

    protected Response() {}

    protected Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> success() {
        return new Response<T>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Response<T> success(T data, String message) {
        return new Response<T>(ResponseEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> Response<PageResult<T>> toPage(IPage<T> iPage) {
        PageResult<T> pageResult = new PageResult<>(iPage.getRecords(),iPage.getTotal());
        return new Response<PageResult<T>>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), pageResult);
    }

    public static <T> Response<T> failed() {
        return failed(ResponseEnum.FAILED);
    }

    public static <T> Response<T> failed(String message) {
        return new Response<T>(ResponseEnum.FAILED.getCode(), message, null);
    }

    public static <T> Response<T> failed(ResponseEnum resultCode) {
        return new Response<T>(resultCode.getCode(), resultCode.getMessage(), null);
    }

}