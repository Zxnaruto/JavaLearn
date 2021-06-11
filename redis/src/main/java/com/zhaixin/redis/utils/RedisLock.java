package com.zhaixin.redis.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Program: redis
 * @Classname: RedisLock
 * @Author: Zhai
 * @Description: 单redis分布式锁
 * @Date: 2021/06/11 14:18
 */
@Slf4j
@Component
public class RedisLock {
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 分布式锁
     * 如果redis异常，为不影响业务，默认会返回成功，当无事发生
     * @return
     */
    public boolean distributedLock(String key, Integer expireSecond) {
        boolean ret;
        try {
            ret = redisUtils.setnx(key, 0);
            if (ret){
                redisUtils.expire(key,expireSecond);
            }
        } catch (Exception e) {
            ret = false;
            log.error("redis operator error ...... key={}", key, e);
        }
        return ret;
    }

    /**
     * 释放分布式锁
     * 如果redis异常，为不影响业务，默认会返回成功，当无事发生
     * @return
     */
    public boolean distributedReleaseLock(String key,boolean lockRet) {
        try {
            if (redisUtils.hasKey(key) && lockRet) {
                redisUtils.del(key);
            }
            log.info("delete redis key={}", key);
        } catch (Exception e) {
            log.error("redis delete key error ...... key={}", key, e);
            return false;
        }
        return true;
    }

}

