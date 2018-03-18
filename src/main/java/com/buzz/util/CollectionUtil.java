package com.buzz.util;


import java.util.Map;
import java.util.Set;

public class CollectionUtil {

    public static boolean isEmpty(Map map) {
        if (map != null) {
            return map.isEmpty();
        }

        return false;
    }

    public static boolean isNotEmpty(Set set) {
        if (set != null && set.size() > 0) {
            return true;
        }

        return false;
    }
}
