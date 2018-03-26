package com.buzz.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public final class RedisLock {

    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private static final String DEFAULT_NX_VALUE = "1";
    private static final long REDIS_SETNX_SUCCESS = 1L;
    private static final int defaultExpired = 60;

    @Autowired
    private Jedis jedis;


    public RedisLock() {
    }

    public boolean lock(String key) {

        return lock(key, defaultExpired);
    }


    public boolean lock(String key, int expire) {

        long expireTime = System.currentTimeMillis() + expire;
        long status = jedis.setnx(key, String.valueOf(expireTime));

        if (status == REDIS_SETNX_SUCCESS) {
            return true;
        }

        String oldExpireValue = jedis.get(key);
        if (oldExpireValue == null) {
            oldExpireValue = "0";
        }

        Long oldExpireTime = Long.parseLong(oldExpireValue);
        if (oldExpireTime < System.currentTimeMillis()) {
            long newExpireTime = System.currentTimeMillis() + expire;
            long currentExpireTime = Long.parseLong(jedis.getSet(key, String.valueOf(newExpireTime)));
            if (currentExpireTime == Long.parseLong(oldExpireValue)) {
                return true;
            }
        }

        return false;
    }

    public void unlock(String key) {
        jedis.del(key);

    }

    private void unlock2(String key) {
        String expiredValue = jedis.get(key);
        if (expiredValue == null) {
            expiredValue = "0";
        }
        Long oldExpireTime = Long.parseLong(expiredValue);
        if (oldExpireTime < System.currentTimeMillis()) {
            jedis.del(key);
        }
    }


}
