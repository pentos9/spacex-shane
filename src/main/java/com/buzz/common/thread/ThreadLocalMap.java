package com.buzz.common.thread;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalMap {
    protected static final ThreadLocal<Map<String, Object>> threadContext = new MapThreadLocal();

    public ThreadLocalMap() {
    }

    public static void put(String key, Object value) {
        getContextMap().put(key, value);
    }

    public static void remove(String key) {
        getContextMap().remove(key);

    }

    public static Object get(String key) {
        return getContextMap().get(key);
    }

    /**
     * 存放Map类型变量的ThreadLocal
     */
    private static class MapThreadLocal extends ThreadLocal<Map<String, Object>> {

        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>() {
                @Override
                public Object put(String key, Object value) {
                    return super.put(key, value);
                }
            };
        }
    }

    public static Map<String, Object> getContextMap() {
        return (Map<String, Object>) threadContext.get();
    }

    public static void clear() {
        getContextMap().clear();
    }
}
