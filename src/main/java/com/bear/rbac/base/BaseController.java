package com.bear.rbac.base;


import com.bear.rbac.common.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * web层通用数据处理
 *
 * @author ruoyi
 */
@Slf4j
public class BaseController<T> {

    /**
     * 返回成功
     */
    public Response<T> success() {
        return Response.success();
    }

    /**
     * 返回失败消息
     */
    public Response<T> error() {
        return Response.failed();
    }

    /**
     * 返回成功消息
     */
    public Response<T> success(T data) {
        return Response.success(data);
    }
}
