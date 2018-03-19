package com.buzz.cache;

import java.util.List;

public interface CacheClient {
    String get(String key);

    boolean set(String key, String value);

    boolean expire(String key, long expire);

    <T> boolean setList(String key, List<T> list);

    <T> List<T> getList(String key, Class<T> clazz);

    

}
