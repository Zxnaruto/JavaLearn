package com.zhaixin.redis.utils;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Program: redis
 * @Classname: redissonLock
 * @Author: Zhai
 * @Description: 分布式集群可用的分布式锁
 * @Date: 2021/06/11 15:15
 */
@Component
public class RedissonLock {
    @Autowired
    private RedissonClient redissonClient;

    public RLock lock(String lockKey, int lockExpireTime, int threadWaitTime) {
        RLock rLock = redissonClient.getLock(lockKey);
        try{
            rLock.tryLock(threadWaitTime, lockExpireTime, TimeUnit.SECONDS);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rLock;
    }

    public boolean unlock(RLock rLock) {
        if(rLock != null) {
            rLock.unlock();
            return true;
        }
        return false;
    }
}

