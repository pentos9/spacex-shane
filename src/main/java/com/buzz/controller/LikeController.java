package com.buzz.controller;

import com.buzz.api.TweetLike;
import com.buzz.api.TweetLikeInfo;
import com.buzz.cache.KeyGenerator;
import com.buzz.constants.Constants;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.SortedSet;


@Controller
@RequestMapping("/tweet")
public class LikeController {
    private Logger logger = LoggerFactory.getLogger(LikeController.class);

    @Autowired
    private Jedis jedis;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public TweetLikeInfo like(@RequestBody TweetLike like) {
        Assert.notNull(like, "like can not be null");
        Assert.notNull(like.getTweetId(), "tweetId can not be null");
        Assert.notNull(like.getLike(), "like field can not be null");
        Assert.notNull(like.getUserId(), "userId can not be null");
        logger.info(String.format("like:[%s]", like));

        String key = KeyGenerator.generateKey(Constants.TWEET_PREFIX, Long.toString(like.getTweetId()));
        String userIdStr = Long.toString(like.getUserId());
        if (like.getLike() != null && like.getLike()) {
            jedis.zadd(key, 1, userIdStr);
        } else {
            jedis.zrem(key, userIdStr);
        }

        Set<String> userIds = jedis.zrevrange(key, 0, -1);
        logger.info(String.format("userIds:[%s]", userIds));

        Long count = jedis.zcard(key);

        TweetLikeInfo tweetLikeInfo = new TweetLikeInfo();
        tweetLikeInfo.setTweetId(like.getTweetId());
        tweetLikeInfo.setLike(count);
        tweetLikeInfo.setUserIds(userIds);

        return tweetLikeInfo;
    }

    @RequestMapping(value = "exist", method = RequestMethod.POST)
    @ResponseBody
    public boolean exist(@RequestBody TweetLike like) {

        Assert.notNull(like, "like can not be null");
        Assert.notNull(like.getTweetId(), "tweetId can not be null");
        Assert.notNull(like.getUserId(), "userId can not be null");

        String key = KeyGenerator.generateKey(Constants.TWEET_PREFIX, Long.toString(like.getTweetId()));
        String userIdStr = Long.toString(like.getUserId());

        Double score = jedis.zscore(key, userIdStr);
        boolean exist = (score != null);

        return exist;
    }
}
