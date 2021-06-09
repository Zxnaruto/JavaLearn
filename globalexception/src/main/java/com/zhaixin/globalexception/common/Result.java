package com.zhaixin.globalexception.common;

import com.zhaixin.globalexception.exception.BaseExceptionInfo;
import lombok.Data;

/**
 * @Program: globalexception
 * @Classname: Result
 * @Author: Zhai
 * @Description:
 * @Date: 2021/06/04 11:35
 */
@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public Result() {

    }

    public Result(BaseExceptionInfo baseExceptionInfo) {
        this.code = baseExceptionInfo.getCode();
        this.msg = baseExceptionInfo.getMessage();
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(ExceptionEnum.SUCCESS.getCode());
        result.setMsg(ExceptionEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(message);
        result.setData(null);
        return result;
    }

    public static Result error(BaseExceptionInfo baseExceptionInfo) {
        Result result = new Result();
        result.setCode(baseExceptionInfo.getCode());
        result.setMsg(baseExceptionInfo.getMessage());
        result.setData(null);
        return result;
    }

}

