package com.zhaixin.globalexception.exception;

/**
 * @Program: globalexception
 * @Classname: MyException
 * @Author: Zhai
 * @Description:
 * @Date: 2021/06/04 10:42
 */
public class MyException extends RuntimeException {
    protected Integer code;
    protected String messages;

    public MyException() {
        super();
    }

    public MyException(Integer code, String messages) {
        super(messages);
        this.code = code;
    }

    public MyException(Integer code, String messages, Throwable throwable) {
        super(messages, throwable);
        this.code = code;
    }

    public MyException(BaseExceptionInfo baseExceptionInfo) {
        super(baseExceptionInfo.getMessage());
        this.code = baseExceptionInfo.getCode();
    }

    public MyException(BaseExceptionInfo baseExceptionInfo, Throwable throwable) {
        super(baseExceptionInfo.getMessage(), throwable);
        this.code = baseExceptionInfo.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}

