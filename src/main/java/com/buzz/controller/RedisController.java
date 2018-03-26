package com.buzz.controller;


import com.buzz.cache.KeyGenerator;
import com.buzz.common.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
public class RedisController {

    private Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Jedis jedis;

    @Autowired
    private RedisLock redisLock;

    @RequestMapping("/redis")
    @ResponseBody
    public String index() {
        String key = "test";
        String value = "value";

        redisTemplate.opsForValue().set(key, value);
        Object vResult = redisTemplate.opsForValue().get(key);
        logger.info("redis client is ok");
        return vResult.toString();
    }

    @RequestMapping("/jedis")
    @ResponseBody
    public String jedis() {
        String key = "jedis-key";
        String value = "jedis-value";

        String setResult = jedis.set(key, value);
        String getResult = jedis.get(key);
        logger.info(String.format("Jedis client setResult:[%s],getResult:[%s]", setResult, getResult));
        return getResult;
    }

    @RequestMapping("/jedis/lock/{userId}")
    @ResponseBody
    public boolean lock(@PathVariable long userId) {

        String prefix = "redis:lock:uid:";
        String key = KeyGenerator.generateKey(prefix, String.valueOf(userId));
        boolean lock = redisLock.lock(key, 60 * 1000);

        if (lock) {
            try {
                logger.info(String.format("do job..."));

            } finally {
                redisLock.unlock(key);
            }

        } else {
            logger.info(String.format("[%s]:try lock failed", RedisController.class.getName()));
        }


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            String scheduledKey = KeyGenerator.generateKey(prefix, String.valueOf(21));
            try {

                boolean scheduledLock = redisLock.lock(scheduledKey, 10 * 1000);
                logger.info(String.format("thread:[%s],scheduledLock:[%s],time:[%s]", Thread.currentThread().getName(), scheduledLock, LocalDateTime.now()));
            } finally {
                redisLock.unlock(scheduledKey);
            }


        }, 1000, 100, TimeUnit.MILLISECONDS);


        return lock;
    }
}
