package com.buzz.controller.redis;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("shane")
public class RateLimiterController {

    private Logger logger = LoggerFactory.getLogger(RateLimiterController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping("/limit")
    public String limit() {
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(100);

        for (int i = 0; i < 10000; i++) {
            schedule.scheduleAtFixedRate(() -> {
                Long increment = stringRedisTemplate.opsForValue().increment("shane:global:mock:id", 1);
                logger.info("shane:global:mock:id->{}", increment);
            }, 1, 1, TimeUnit.SECONDS);
        }

        String result = stringRedisTemplate.opsForValue().get("shane:global:mock:id");
        return result;
    }

    @RequestMapping("/flowControl")
    public boolean flowControl(String uid, int maxTimes) {
        Preconditions.checkArgument(StringUtils.isNotBlank(uid), "uid不能为空");

        long max = 10;

        String key = "shane:limit:max:" + uid;
        String value = stringRedisTemplate.opsForValue().get(key);

        if (StringUtils.isNotBlank(value) && Long.parseLong(value) > max) {
            logger.warn("抱歉，你已近达到最大调用次数");
            return false;
        } else {
            long incr = stringRedisTemplate.opsForValue().increment(key, 1);
            stringRedisTemplate.expire(key, 1, TimeUnit.MINUTES);
        }

        logger.info("call and execute target api now...");

        return true;
    }
}
