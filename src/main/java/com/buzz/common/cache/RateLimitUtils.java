package com.buzz.common.cache;

import org.apache.commons.lang3.StringUtils;

public class RateLimitUtils {
    private RateLimitUtils() {
    }

    /**
     * redis.incr命令第一次操作的返回值是1
     *
     * @param key
     * @param expiredTime 到达限制次数上限之后，在规定时间内，不允许再次操作，返回false
     * @param limit
     * @return
     */
    public static boolean isExceedRate(String key, long expiredTime, int limit) {
        long num = SpringRedisUtils.increment(key, expiredTime);
        return num >= limit;
    }
}
