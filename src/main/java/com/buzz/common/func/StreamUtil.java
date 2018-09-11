package com.buzz.common.func;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamUtil {
    public static <T, R> List<R> map(List<T> data, Function<T, R> mapFunction) {
        return data.stream().map(mapFunction).collect(Collectors.toList());
    }
}
