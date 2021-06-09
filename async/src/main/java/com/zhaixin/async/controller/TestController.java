package com.zhaixin.async.controller;

import com.zhaixin.async.java.CompletableFutureTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: async
 * @Classname: TestController
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/06/07 10:15
 */
@RestController
public class TestController {
    @Autowired
    private CompletableFutureTest completableFutureTest;

    @RequestMapping(value = "/get2", method = RequestMethod.POST)
    public String test1() {
        return completableFutureTest.a();
    }
}

