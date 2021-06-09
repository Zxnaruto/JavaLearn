package com.zhaixin.async.spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @Program: async
 * @Classname: AsyncTest
 * @Author: Zhai
 * @Description:
 * @Date: 2021/06/04 15:43
 */
@Component
public class AsyncClass {

    // 无结果返回
    @Async
    public void asyncMethod1() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("asyncMethod1");
    }

    // 有返回值的，其实就是同步，只不过在加了线程池后，效率有所提高
    @Async
    public CompletableFuture<String> asyncMethod2() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("ok");
    }
}

