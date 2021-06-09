package com.zhaixin.globalexception.controller;

import com.zhaixin.globalexception.common.ExceptionEnum;
import com.zhaixin.globalexception.common.Result;
import com.zhaixin.globalexception.exception.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: globalexception
 * @Classname: TestController
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/06/04 11:57
 */
@RestController
public class TestController {

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public Result get() {
        return Result.success("test");
    }
}

