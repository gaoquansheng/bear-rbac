package com.bear.rbac.config;

import lombok.Data;


public class BusinessException extends RuntimeException {

    private Integer code;

    private String message;

    public BusinessException() {
    }

    public BusinessException(String message) {

        this.message = message;
    }

    public BusinessException(Integer code, String message) {

        this.code = code;
        this.message = message;
    }
}
