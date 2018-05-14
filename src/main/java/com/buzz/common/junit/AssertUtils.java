package com.buzz.common.junit;

public class AssertUtils {
    public static void notNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("argument can not be nulll");
        }

    }
}
