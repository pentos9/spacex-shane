package com.buzz.controller.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("shane/social")
public class TweetLikeController {

    private Logger logger = LoggerFactory.getLogger(TweetLikeController.class);

    private String LIKE_WORKS_PREFIX = "shane:social:works:";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "like", method = RequestMethod.POST)
    public String like(String uid, String workId) {
        logger.info("LikeController#like.uid:{},workId:{}", uid, workId);
        String key = LIKE_WORKS_PREFIX + workId;
        Long addedResult = stringRedisTemplate.opsForSet().add(key, uid);
        logger.info("LikeController#like.addedResult:{}", addedResult);
        return "";
    }

    @RequestMapping(value = "unlike", method = RequestMethod.POST)
    public String unlike(String uid, String workId) {
        logger.info("LikeController#like.uid:{},workId:{}", uid, workId);
        String key = LIKE_WORKS_PREFIX + workId;
        Long removedResult = stringRedisTemplate.opsForSet().remove(key, uid);
        logger.info("LikeController#like.removedResult:{}", removedResult);
        return "";
    }

    @RequestMapping(value = "topNLikes")
    public List<String> topNLikes(String uid, String workId, Long size) {
        logger.info("LikeController#topNlikes.uid:{},workId:{}", uid, workId);
        String key = LIKE_WORKS_PREFIX + workId;
        Set<String> members = stringRedisTemplate.opsForSet().members(key);
        int queueSize = members.size();
        if (size >= queueSize) {
            return new ArrayList<>(members);
        }

        Iterator<String> iterator = members.iterator();
        List<String> results = new ArrayList<>();
        int times = 0;
        while (size < times && iterator.hasNext()) {
            results.add(iterator.next());
            times++;
        }
        return results;
    }
}
