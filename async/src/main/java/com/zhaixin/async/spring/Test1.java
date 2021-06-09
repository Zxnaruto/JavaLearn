package com.zhaixin.async.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Program: async
 * @Classname: Test1
 * @Author:  Zhai
 * @Description:
 * @Date: 2021/06/04 15:47
 */
@RestController
public class Test1 {
    @Autowired
    private AsyncClass asyncClass;

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public String test() throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("start");
       String s =asyncClass.asyncMethod2().get(3000, TimeUnit.SECONDS);
      System.out.println("end");
        return s;
    }

    @RequestMapping(value = "get1", method = RequestMethod.POST)
    public String test1() throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("start");
        asyncClass.asyncMethod1();
        System.out.println("end");
        return "ok";
    }
}

