package com.buzz.controller;

import com.buzz.common.spring.ApplicationContextUtil;
import com.buzz.common.string.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/pong")
    public String ping() {
        LikeController likeController = (LikeController) ApplicationContextUtil.getBean("likeController");
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtil.getBean("redisTemplate");
        ApplicationContextUtil applicationContextUtil = (ApplicationContextUtil) ApplicationContextUtil.getBean("applicationContextUtil");
        System.out.println(logger.isDebugEnabled());
        System.out.println(logger.isInfoEnabled());
        System.out.println(logger.isWarnEnabled());
        System.out.println(logger.isErrorEnabled());
        logger.warn(String.format("likeController=%s , redisTemplate=%s ", JsonUtils.toJson(likeController), JsonUtils.toJson(applicationContextUtil)));
        if (true) {
            throw new RuntimeException("test exception");
        }
        return "pong";
    }
}
