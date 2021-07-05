package com.zhaixin.springanoncation.exception;

import com.zhaixin.springanoncation.common.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @Program: springanoncation
 * @Classname: MyException
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/07/05 15:36
 */
@ControllerAdvice
public class MyException {

    // String可以换成对应Result(code,message)实体
    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result paramExceptionHandler(MethodArgumentNotValidException e) {
        Result result = new Result();
        BindingResult bindResult = e.getBindingResult();
        if(bindResult.hasErrors()) {
            List<ObjectError> errors = bindResult.getAllErrors();
            if(!errors.isEmpty()) {
                FieldError fieldError = (FieldError)errors.get(0);
                result.setCode(fieldError.getCode());
                result.setMsg(fieldError.getDefaultMessage());
                return result;
            }
        }
        return result;
    }
}

