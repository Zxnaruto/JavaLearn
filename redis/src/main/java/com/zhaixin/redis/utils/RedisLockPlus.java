package com.zhaixin.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Program: redis
 * @Classname: RedisLockPlus
 * @Author: Zhai
 * @Description: 一个线程执行完操作删除key时，要保证是同一个线程删除同一个key，即使用value做唯一的id，根据value进行删除，防止锁被其他线程删除。
 * @Date: 2021/06/11 14:39
 */
@Component
public class RedisLockPlus {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public String lock(String lockKey, String token, int lockExpireTime) {
        token = UUID.randomUUID().toString();
        boolean result;
        try {
            result = redisTemplate.opsForValue().setIfAbsent(lockKey, token, lockExpireTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            return null;
        }
        if (result) {
            return token;
        }
        return null;
    }

    public boolean unlock(String lockKey, String token) {
        if (lockKey == null || token == null) {
            return false;
        }
        if (token.equals(redisTemplate.opsForValue().get(lockKey))) {
            redisTemplate.delete(lockKey);
            return true;
        }
        return false;
    }
}

