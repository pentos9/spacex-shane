package com.buzz.interceptor.entity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SimpleRateLimiter {

    private Semaphore semaphore;

    private int maxPermits;

    private TimeUnit timeUnit;

    private ScheduledExecutorService scheduler;

    private SimpleRateLimiter(int maxPermits, TimeUnit timeUnit) {
        this.semaphore = new Semaphore(maxPermits);
        this.maxPermits = maxPermits;
        this.timeUnit = timeUnit;
    }

    public static SimpleRateLimiter create(int maxPermits, TimeUnit timeUnit) {
        SimpleRateLimiter simpleRateLimiter = new SimpleRateLimiter(maxPermits, timeUnit);
        simpleRateLimiter.schedulePermitReplenishment();
        return simpleRateLimiter;
    }

    public boolean tryAcquire() {
        return semaphore.tryAcquire();
    }

    public void stop() {
        scheduler.shutdownNow();
    }

    public void schedulePermitReplenishment() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            semaphore.release(maxPermits - semaphore.availablePermits());
        }, 1, timeUnit);
    }
}
