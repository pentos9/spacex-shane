package com.buzz.test.pattern.other.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskPoolApp {
    private static final Logger logger = LoggerFactory.getLogger(TaskPoolApp.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        logger.info("program started.");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new PotatoPeelingTask(2));
        tasks.add(new CoffeeMakingTask(6));
        tasks.add(new PotatoPeelingTask(4));
        tasks.add(new PotatoPeelingTask(6));
        tasks.add(new CoffeeMakingTask(6));
        tasks.add(new PotatoPeelingTask(5));

        tasks.add(new CoffeeMakingTask(7));
        tasks.add(new CoffeeMakingTask(1));
        tasks.add(new PotatoPeelingTask(6));
        tasks.add(new CoffeeMakingTask(2));
        tasks.add(new CoffeeMakingTask(3));
        tasks.add(new PotatoPeelingTask(6));

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < tasks.size(); i++) {
            Runnable worker = new Worker(tasks.get(i));
            executorService.execute(worker);
        }

        executorService.shutdown();

        while (executorService.isTerminated()) {
            Thread.yield();
        }
        logger.info("program finished");

    }
}
