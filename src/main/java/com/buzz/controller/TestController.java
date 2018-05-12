package com.buzz.controller;

import com.buzz.cache.KeyGenerator;
import com.buzz.common.mis.RandomUtils;
import com.buzz.common.spring.ApplicationContextUtil;
import com.buzz.common.string.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/pong")
    public String ping() {
        LikeController likeController = (LikeController) ApplicationContextUtil.getBean("likeController");
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtil.getBean("redisTemplate");
        ApplicationContextUtil applicationContextUtil = (ApplicationContextUtil) ApplicationContextUtil.getBean("applicationContextUtil");
        logger.info(String.format("likeController=%s , redisTemplate=%s ", JsonUtils.toJson(likeController), JsonUtils.toJson(applicationContextUtil)));
        return "pong";
    }

    @RequestMapping("bitcount")
    public String bitCount() {

        String key = KeyGenerator.generateKey("redis:bit:", "count");
        StringBuilder builder = new StringBuilder();
        int randomNumber = 0;
        try {

            for (int i = 0; i < 10; i++) {
                randomNumber = RandomUtils.getRandom(0, Integer.MAX_VALUE);
                builder.append(randomNumber).append(",");
                redisTemplate.opsForValue().setBit(key, randomNumber, true);
            }
            Object bitcount = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    Long count = redisConnection.bitCount(key.getBytes());
                    return count;
                }
            });

            logger.info("bitcount:{},randomNumbers:{}", bitcount, builder.toString());
            return String.valueOf(bitcount);

        } catch (Exception e) {
            logger.error(String.format("randomNumber:%s,exception:%s", randomNumber, e));
        } finally {
            redisTemplate.delete(key);
        }
        return String.valueOf("ok");
    }
}
