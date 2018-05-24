package com.buzz.test.core.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ScheduleThreadPoolTest {

    private static Logger logger = LoggerFactory.getLogger(ScheduleThreadPoolTest.class);

    public static void main(String[] args) {
        doIt();
    }

    public static void run() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        scheduledExecutorService.schedule(() -> {
            doJob("can only run once");
        }, 1, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            doJob("scheduleAtFixedRate");
        }, 1, 2, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            doJob("scheduleWithFixedDelay");
        }, 1, 1, TimeUnit.SECONDS);


    }

    public static void doTask() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        for (int i = 0; i < 10; i++) {
            scheduledExecutorService.scheduleAtFixedRate(new Worker(i), 1, 1, TimeUnit.SECONDS);
        }

    }

    public static void doJob(String message) {
        logger.info(String.format("This Worker->%s %s", Thread.currentThread().getName(), message));
    }

    public static void doIt() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(10, new RejectedExecutionHandler() {

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                logger.info("rejectedExecution and do nothing...");
            }
        });

        ExecutorService service = new ThreadPoolExecutor(10, 20, 2000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                logger.error("rejectedExecution trigger ");
            }
        });

        service.submit(() -> {
            logger.info("Customize an ExecutorService with ThreadPoolExecutor");
        });

        service.shutdown();

    }
}
