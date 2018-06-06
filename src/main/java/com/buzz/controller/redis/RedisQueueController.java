package com.buzz.controller.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shane/queue")
public class RedisQueueController {
    private Logger logger = LoggerFactory.getLogger(RedisQueueController.class);

    private static final String queueKey = "shane:queue:orders";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/enqueue", method = RequestMethod.POST)
    public String enqueue(String item) {
        logger.info(String.format("item:%s", item));
        Long result = stringRedisTemplate.opsForList().leftPush(queueKey, item);
        logger.info("leftPush#result:%s", result);
        return String.valueOf(result);
    }

    @RequestMapping(value = "/dequeue", method = RequestMethod.POST)
    public String dequeue() {
        String item = stringRedisTemplate.opsForList().rightPop(queueKey);
        logger.info("leftPush#item:%s", item);
        return item;
    }
}
