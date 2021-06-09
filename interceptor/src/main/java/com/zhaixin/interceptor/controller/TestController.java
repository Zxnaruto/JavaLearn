package com.zhaixin.interceptor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: interceptor
 * @Classname: TestController
 * @Author:  Zhai
 * @Description:
 * @Date: 2021/06/09 15:21
 */
@RestController
public class TestController {

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getResult() {
        return "ok";
    }
}

