package com.buzz.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.concurrent.TimeUnit;

@RestController
public class RedisDistributedLockController {

    private Logger logger = LoggerFactory.getLogger(RedisDistributedLockController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping(value = "/distribute/redis/lock")
    public String distributeLock() {
        String key = "redis:distribute:key";
        String value = "value";

        final String NX = "NX";
        final String EX = "EX";
        final int expireTime = 1000;

        final String OK = "OK";

        stringRedisTemplate.opsForValue().set(key, value, 1L, TimeUnit.MINUTES);
        boolean result = stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set(key.getBytes(), value.getBytes(), Expiration.seconds(60L), RedisStringCommands.SetOption.SET_IF_ABSENT);
                Object nativeConnection = redisConnection.getNativeConnection();
                String retStatusCode = "";
                // 集群模式
                if (nativeConnection instanceof JedisCluster) {
                    JedisCluster jedisCluster = (JedisCluster) nativeConnection;
                    retStatusCode = jedisCluster.set(key, value, NX, EX, expireTime);
                }
                // 单机模式
                else if (nativeConnection instanceof Jedis) {
                    Jedis jedis = (Jedis) nativeConnection;
                    retStatusCode = jedis.set(key, value, NX, EX, expireTime);
                }
                return StringUtils.equalsIgnoreCase(OK, retStatusCode);
            }
        });

        logger.info(String.format("%s", result));

        return RandomStringUtils.random(16, true, false);
    }
}
