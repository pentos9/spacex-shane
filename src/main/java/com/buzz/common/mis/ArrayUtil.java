package com.buzz.common.mis;

import java.lang.reflect.Array;

public class ArrayUtil {
    /**
     * 数组中元素未找到的下标，值为-1
     */
    public static final int INDEX_NOT_FOUND = -1;

    public static <T> boolean isEmpty(final T... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Object array) {
        if (null == array) {
            return true;
        } else if (isArray(array)) {
            return 0 == Array.getLength(array);
        }
        throw new IllegalArgumentException("Object to provide is not a Array !");
    }

    public static boolean isEmpty(final long... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(final int... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(final short... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(final char... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(final byte... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(final double... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(final float... array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(final boolean... array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNotEmpty(final T... array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(final Object array) {
        return false == isEmpty((Object) array);
    }

    public static boolean isNotEmpty(final long... array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(final int... array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(final short... array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(final char... array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(final byte... array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(final double... array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(final float... array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(final boolean... array) {
        return (array != null && array.length != 0);
    }

    public static <T> boolean hasNull(T... array) {
        if (isNotEmpty(array)) {
            for (T element : array) {
                if (null == element) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <T> T firstNonNull(T... array) {
        if (isNotEmpty(array)) {
            for (final T val : array) {
                if (null != val) {
                    return val;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(Class<?> componentType, int newSize) {
        return (T[]) Array.newInstance(componentType, newSize);
    }

    public static <T> T[] newArray(int newSize) {
        return (T[]) new Object[newSize];
    }

    public static Class<?> getComponentType(Object array) {
        return null == array ? null : array.getClass().getComponentType();
    }

    public static Class<?> getComponentType(Class<?> arrayClass) {
        return null == arrayClass ? null : arrayClass.getComponentType();
    }

    public static Class<?> getArrayType(Class<?> componentType) {
        return newArray(componentType, 0).getClass();
    }

    public static Object[] cast(Class<?> type, Object arrayObj) throws NullPointerException, IllegalArgumentException {
        if (null == arrayObj) {
            throw new NullPointerException("Argument [arrayObj] is null !");
        }
        if (false == arrayObj.getClass().isArray()) {
            throw new IllegalArgumentException("Argument [arrayObj] is not array !");
        }
        if (null == type) {
            return (Object[]) arrayObj;
        }

        final Class<?> componentType = type.isArray() ? type.getComponentType() : type;
        final Object[] array = (Object[]) arrayObj;
        final Object[] result = ArrayUtil.newArray(componentType, array.length);
        System.arraycopy(array, 0, result, 0, array.length);
        return result;
    }

    @SafeVarargs
    public static <T> T[] append(T[] buffer, T... newElements) {
        if (isEmpty(buffer)) {
            return newElements;
        }
        return insert(buffer, buffer.length, newElements);
    }

    @SafeVarargs
    public static <T> T[] insert(T[] buffer, int index, T... newElements) {
        if (isEmpty(newElements)) {
            return buffer;
        }
        if (isEmpty(buffer)) {
            return newElements;
        }
        if (index < 0) {
            index = (index % buffer.length) + buffer.length;
        }

        final T[] result = newArray(buffer.getClass().getComponentType(), Math.max(buffer.length, index) + newElements.length);
        System.arraycopy(buffer, 0, result, 0, Math.min(buffer.length, index));
        System.arraycopy(newElements, 0, result, index, newElements.length);
        if (index < buffer.length) {
            System.arraycopy(buffer, index, result, index + newElements.length, buffer.length - index);
        }
        return result;
    }

    public static <T> T[] resize(T[] buffer, int newSize, Class<?> componentType) {
        T[] newArray = newArray(componentType, newSize);
        if (isNotEmpty(buffer)) {
            System.arraycopy(buffer, 0, newArray, 0, Math.min(buffer.length, newSize));
        }
        return newArray;
    }

    public static <T> T[] resize(T[] buffer, int newSize) {
        return resize(buffer, newSize, buffer.getClass().getComponentType());
    }

    public static <T> T[] addAll(T[]... arrays) {
        if (arrays.length == 1) {
            return arrays[0];
        }

        int length = 0;
        for (T[] array : arrays) {
            if (array == null) {
                continue;
            }
            length += array.length;
        }
        T[] result = newArray(arrays.getClass().getComponentType().getComponentType(), length);

        length = 0;
        for (T[] array : arrays) {
            if (array == null) {
                continue;
            }
            System.arraycopy(array, 0, result, length, array.length);
            length += array.length;
        }
        return result;
    }

    public static boolean isArray(Object obj) {
        if (null == obj) {
            // throw new NullPointerException("Object check for isArray is null");
            return false;
        }
        return obj.getClass().isArray();
    }
}
