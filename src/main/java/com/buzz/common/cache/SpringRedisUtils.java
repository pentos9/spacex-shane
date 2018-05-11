package com.buzz.common.cache;

import com.buzz.common.spring.ApplicationContextUtil;
import com.buzz.common.string.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

public class SpringRedisUtils {
    private static final StringRedisTemplate redisTemplate = (StringRedisTemplate) ApplicationContextUtil.getBean("redisTemplate");

    public static void set(final String key, Object value) {
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
}
