package com.buzz.common.func;

import java.util.function.Consumer;
import java.util.function.Function;

public class CatchUtil {
    public static <T, R> R tryDo(T type, Function<T, R> function) {
        try {
            return function.apply(type);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public static <T> void tryDo(T type, Consumer<T> consumer) {
        try {
            consumer.accept(type);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
