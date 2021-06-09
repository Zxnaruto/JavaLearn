package com.zhaixin.globalexception.exception;

import com.zhaixin.globalexception.common.ExceptionEnum;
import com.zhaixin.globalexception.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Program: globalexception
 * @Classname: GlobalExceptionHandler
 * @Author: Zhai
 * @Description:
 * @Date: 2021/06/04 11:48
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result MyException(HttpServletRequest request, MyException e) {
        return Result.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e){
        return Result.error(ExceptionEnum.NULL_POINTER_ERROR);
    }
}

