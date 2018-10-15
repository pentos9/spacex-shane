package com.buzz.common.func;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConcurrentDataHandlerFrameRefactor {
    public static void main(String[] args) {
        List<Integer> allData = getAllData(DataSupplier::getKeys, GetTradeData::getData);
        consume(allData, System.out::println);

        List<Double> handledData = handleAllData(allData, (numbers) -> StreamUtil.map(numbers, num -> Math.sqrt(num)));
        consume(handledData, System.out::println);

        List<Object> objs = StreamUtil.map(DataSupplier.getKeys(), Double::valueOf);
        List<Double> handledData2 = handleAllData((numbers) -> StreamUtil.map(numbers, num -> Math.pow((double) num, 2))).apply(objs);
        consume(handledData2, System.out::println);

        Function<List<String>, List<Object>> func = (numbers) -> StreamUtil.map(numbers, (num) -> Integer.parseInt(num) * 2);
        List<Object> handlesData3 = handleAllData(DataSupplier::getKeys).apply(func);
        consume(handlesData3, System.out::println);

    }

    public static <T> List<T> getAllData(Supplier<List<String>> getAllKeyFunc, Function<List<String>, List<T>> iGetBizDataFunc) {
        return getAllData(getAllKeyFunc.get(), iGetBizDataFunc);
    }

    public static <T> List<T> getAllData(List<String> allKeys, Function<List<String>, List<T>> iGetBizDataFunc) {
        return handleAllData(allKeys, iGetBizDataFunc);
    }

    public static <T, R> List<R> handleAllData(Supplier<List<T>> getAllKeysFunc, Function<List<T>, List<R>> handleBizDataFunc) {
        return handleAllData(getAllKeysFunc.get(), handleBizDataFunc);
    }

    public static <T, R> Function<List<T>, List<R>> handleAllData(Function<List<T>, List<R>> handleBizDataFunc) {
        return ts -> handleAllData(ts, handleBizDataFunc);
    }

    public static <T, R> Function<Function<List<T>, List<R>>, List<R>> handleAllData(Supplier<List<T>> getAllKeyFunc) {
        return handleBizDataFunc -> handleAllData(getAllKeyFunc.get(), handleBizDataFunc);
    }

    public static <T, R> List<R> handleAllData(List<T> allKeys, Function<List<T>, List<R>> handleBizDataFunc) {
        return ExecutorUtil.exec(allKeys, handleBizDataFunc);
    }

    public static <T> void consume(List<T> data, Consumer<T> consumer) {
        data.forEach((type) -> CatchUtil.tryDo(type, consumer));
    }

    public static class DataSupplier {
        public static List<String> getKeys() {
            return ForeachUtil.foreachAddWithReturn(1000, (ind -> Arrays.asList(String.valueOf(ind))));
        }
    }

    public static class GetTradeData {
        public static List<Integer> getData(List<String> keys) {
            return StreamUtil.map(keys, key -> Integer.valueOf(key) % 1000000000);
        }
    }
}
