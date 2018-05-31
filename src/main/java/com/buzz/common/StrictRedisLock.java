package com.buzz.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Collections;

@Component
public class StrictRedisLock {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String DEFAULT_VALUE = "value";//这个可以是随意设置

    @Autowired
    private Jedis jedis;

    /**
     * @param key
     * @param expiredTime
     * @return
     */
    public boolean tryLock(String key, long expiredTime) {
        String result = jedis.set(key, DEFAULT_VALUE, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expiredTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey 锁
     * @return 是否释放成功
     */
    public boolean releaseDistributedLock(String lockKey) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(DEFAULT_VALUE));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }
}
