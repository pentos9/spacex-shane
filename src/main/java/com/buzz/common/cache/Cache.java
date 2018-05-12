package com.buzz.common.cache;

import java.util.concurrent.TimeUnit;

/**
 * Cache common methods
 */
public interface Cache<K, V> {
    V get(K key);

    /** 没有，就添加；有就省略 */
    boolean add(K key, V value);

    boolean add(K key, V value, long expiredTime);

    boolean add(K key, V value, long expiredTime, TimeUnit timeUnit);

    /** 没有就添加，有就替换掉原来的值 */
    boolean put(K key, V value);

    boolean put(K key, V value, long expiredTime);

    boolean put(K key, V value, long expiredTime, TimeUnit timeUnit);

    void remove(K key);

    long incr(K key, long delta);

    long incr(K key, long delta, long initValue);

    long decr(K key, long delta);

    long decr(K key, long delta, long initValue);

    void flushAll();

    void destroy();
}
