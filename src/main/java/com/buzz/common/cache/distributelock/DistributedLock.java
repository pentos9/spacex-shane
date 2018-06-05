package com.buzz.common.cache.distributelock;

/**
 * DistributedLock 顶级接口
 */
public interface DistributedLock {
    public static final long TIMEOUT_MILLIS = 30000L;

    public static final int RETRY_TIMES = 5;

    public static final long SLEEP_MILLIS = 5000L;

    public boolean lock(String key);

    public boolean lock(String key, int retryTimes);

    public boolean lock(String key, int retryTimes, long sleepMills);

    public boolean lock(String key, long expire);

    public boolean lock(String key, long expire, int retryTimes);

    public boolean lock(String key, long expire, int retryTimes, long sleepMills);

    public boolean releaseLock(String key);

}
