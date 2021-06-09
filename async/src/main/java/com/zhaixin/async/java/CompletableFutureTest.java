package com.zhaixin.async.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/**
 * @Program: async
 * @Classname: CompletableFutureTest
 * @Author: Zhai
 * @Description:
 * @Date: 2021/06/04 17:50
 */
@Component
public class CompletableFutureTest {

    @Qualifier("myTaskExecutor")
    @Autowired
    private Executor executor;
    public void test() {
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(5000);
                System.out.println("123");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f";
        }, executor).thenAccept(System.out::println).exceptionally(throwable -> {
            throw new NullPointerException();
        });
    }

    public String a(){
        System.out.println("start");
        test();
        System.out.println("end");
        return "ok";
    }

//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFutureTest test = new CompletableFutureTest();
//        System.out.println(test.a());
//    }

    // 异步上传
//    public void putFileTask(List data, String name, String suffix, Map<String, String> alia, String callbackId) {
//        CompletableFuture<OssPutResult> stringCompletableFuture = CompletableFuture.supplyAsync(() -> uploadDataAndGetUrl(data, name+suffix, alia), task.getExecutor());
//        stringCompletableFuture.exceptionally(e -> {
//            String callback = caller.doPost("", callbackId, new Date(), 2, name+suffix);
//            log.error("下载文件失败benz回调结果:{}", callback);
//            throw new BusinessException("BC500", "benz traffic callback error");
//        }).thenAccept(u -> {
//            log.info("入参:{}",u);
//            String result = caller.doPost(u.getUrl(), callbackId, u.getExpireDate(), 1, name+suffix);
//            log.info("benz下载文件回调:{}", result);
//        });
//    }
}

