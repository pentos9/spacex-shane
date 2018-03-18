package com.buzz.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
public class RedisController {

    private Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Jedis jedis;

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
}
