package com.bear.rbac.enums;

public enum UserStatusEnum {


    NORMAL("01", "正常"),
    DISABLE("99","停用");

    private String code;
    private String message;

    UserStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
