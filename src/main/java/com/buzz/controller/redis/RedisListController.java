package com.buzz.controller.redis;

import com.buzz.common.mis.RandomUtils;
import com.buzz.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("shane/redis")
public class RedisListController {
    private Logger logger = LoggerFactory.getLogger(RedisListController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/setComments", method = RequestMethod.POST)
    public String list(String articleId, String item) {
        logger.info("RedisListController#list.item:{}", item);
        String key = "shane:article:comments:" + articleId;
        for (int i = 0; i < 1000; i++) {
            int randomNumber = RandomUtils.getRandom(1, 1000);
            stringRedisTemplate.opsForList().leftPush(key, String.valueOf(randomNumber));
            stringRedisTemplate.opsForList().trim(key, 0, 100);
        }

        return "done";
    }

    @RequestMapping(value = "getLastComments")
    public String getLastComments(String articleId, int start, int size) {
        String key = "shane:article:comments:" + articleId;
        List<String> commentIds = stringRedisTemplate.opsForList().range(key, start, start + size);
        return JsonUtil.toJson(commentIds);
    }
}
