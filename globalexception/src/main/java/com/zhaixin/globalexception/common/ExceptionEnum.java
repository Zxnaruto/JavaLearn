package com.zhaixin.globalexception.common;

import com.zhaixin.globalexception.exception.BaseExceptionInfo;

public enum ExceptionEnum implements BaseExceptionInfo {
    // 状态描述定义
    SUCCESS(200, "success"),
    PARAM_ERROR(400,"req param be null"),
    NULL_POINTER_ERROR(401,"null pointer error");

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
