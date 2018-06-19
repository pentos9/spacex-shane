package com.buzz.test.core.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableInvokeAllTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        ExecutorService executorService = Executors.newWorkStealingPool(3);
        List<Callable<String>> callableList = Arrays.asList(() -> "task1", () -> "task2", () -> "task3");

        try {
            executorService.invokeAll(callableList).stream().map(future -> {
                try {
                    return future.get();
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }).forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            String result = executorService.invokeAny(callableList);
            System.out.println("invokeAny->result:" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}