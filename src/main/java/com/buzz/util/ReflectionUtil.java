package com.buzz.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {

    /**
     * 创建实例
     */

    public static Object newInstance(Class<?> cls) {

        Object instance = null;
        try {
            instance = cls.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return instance;
    }

    public static Object invokeMethod(Object object, Method method, Object... args) {

        Object result = null;
        method.setAccessible(true);
        try {
            result = method.invoke(object, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return result;

    }

    public static void setField(Object object, Field field, Object value) {

        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
