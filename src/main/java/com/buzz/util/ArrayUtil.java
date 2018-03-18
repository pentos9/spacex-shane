package com.buzz.util;


public class ArrayUtil {

    public static boolean isEmpty(Object[] array) {
        if (array != null && array.length > 0) {
            return false;
        }

        return true;
    }

    public static boolean isNotEmpty(Object[] array) {
        boolean result = !isEmpty(array);
        return result;
    }
}
