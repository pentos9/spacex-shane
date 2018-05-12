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
        logger.info(String.format("likeController=%s , redisTemplate=%s ", JsonUtils.toJson(likeController), JsonUtils.toJson(applicationContextUtil)));
        return "pong";
    }
}
