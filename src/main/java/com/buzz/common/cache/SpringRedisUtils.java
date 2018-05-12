package com.buzz.common.cache;

import com.buzz.common.spring.ApplicationContextUtil;
import com.buzz.common.string.JsonUtils;
import com.buzz.common.string.PinyinUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

public class SpringRedisUtils {
    private static final StringRedisTemplate redisTemplate = (StringRedisTemplate) ApplicationContextUtil.getBean("redisTemplate");

    public void set(final String key, Object value) {
        final String jsonValue = JsonUtils.toJson(value);
        redisTemplate.opsForValue().set(key, jsonValue);
    }


    public static <T> T get(final String key, Class<T> elementType) {
        String jsonValue = redisTemplate.opsForValue().get(key);
        return JsonUtils.fromJson(jsonValue, elementType);
    }

    public static boolean setNX(final String key, Object value) {
        final String jsonValue = JsonUtils.toJson(value);
        return redisTemplate.opsForValue().setIfAbsent(key, jsonValue);
    }

    public static <T> T getSet(final String key, Object value, Class<T> clazz) {
        final String jsonValue = JsonUtils.toJson(value);
        String oldValue = redisTemplate.opsForValue().getAndSet(key, jsonValue);
        if (StringUtils.isEmpty(oldValue)) {
            return null;
        }
        return JsonUtils.fromJson(oldValue, clazz);
    }

    public static void delete(final String key) {
        redisTemplate.delete(key);
    }

    public static long increment(final String key, long expireTime) {
        return redisTemplate.opsForValue().increment(key, expireTime);
    }

    public static boolean setBit(String key, long offset) {
        boolean result = redisTemplate.opsForValue().setBit(key, offset, true);
        return result;
    }

    public static long bitCount(String key) {
        Object bitcount = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Long count = redisConnection.bitCount(key.getBytes());
                return count;
            }
        });

        return (Long) bitcount;
    }
}
