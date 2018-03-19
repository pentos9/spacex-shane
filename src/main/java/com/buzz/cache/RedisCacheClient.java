package com.buzz.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisCacheClient implements CacheClient {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String get(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        return value;
    }

    @Override
    public boolean set(String key, String value) {
        return true;
    }

    @Override
    public boolean expire(String key, long expire) {
        return false;
    }

    @Override
    public <T> boolean setList(String key, List<T> list) {
        return false;
    }

    @Override
    public <T> List<T> getList(String key, Class<T> clazz) {
        return null;
    }


}
