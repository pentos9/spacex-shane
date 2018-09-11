package com.buzz.common.func;


import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

public class ExecutorUtil {
    private static final int CORE_CPUS = Runtime.getRuntime().availableProcessors();
    private static final int TASK_SIZE = 1000;

    private static ExecutorService executor = new ThreadPoolExecutor(CORE_CPUS, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(60));

    public static <T, R> List<R> exec(List<T> allKeys, Function<List<T>, List<R>> handleBizDataFunc) {
        List<String> parks = TaskUtil.divide(allKeys.size(), TASK_SIZE);
        CompletionService<List<R>> completionService = new ExecutorCompletionService<List<R>>(executor);

        ForeachUtil.foreachDone(parks, (part) -> {
            final List<T> temRowKeyList = TaskUtil.getSubList(allKeys, part);
            completionService.submit(() -> handleBizDataFunc.apply(temRowKeyList));
        });

        List<R> result = ForeachUtil.foreachAddWithReturn(parks.size(), (ind) -> get(ind, completionService));
        return result;
    }

    public static <T> void exec(List<T> allKeys, Consumer<List<T>> handBizDataFunc) {
        List<String> parks = TaskUtil.divide(allKeys.size(), TASK_SIZE);
        ForeachUtil.foreachDone(parks, (park) -> {
            final List<T> temRowKeyList = TaskUtil.getSubList(allKeys, park);
            executor.execute(() -> handBizDataFunc.accept(temRowKeyList));
        });
    }

    private static <T> List<T> get(Integer ind, CompletionService<List<T>> completionService) {
        try {
            return completionService.take().get();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
