package com.buzz.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class JedisCacheClient {

    @Autowired
    private Jedis jedis;

}
