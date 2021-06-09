package com.zhaixin.globalexception.exception;

public interface BaseExceptionInfo {
    // 状态码
    Integer getCode();
    // 状态描述
    String getMessage();
}
