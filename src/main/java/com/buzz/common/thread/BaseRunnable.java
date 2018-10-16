package com.buzz.common.thread;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRunnable implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(BaseRunnable.class);

    protected long id;
    protected String name;
    protected boolean isRunning;
    protected int callCount = 0;
    protected Map<Integer, Long> runTimes = new HashMap<>();

    public BaseRunnable() {
    }

    public BaseRunnable(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void run() {
        if (isRunning) {
            logger.warn(String.format("[Thread] %s is running,need to stop it before execute!", id));
            return;
        }

        isRunning = true;
        callCount++;
        long start = System.currentTimeMillis();
        work();
        long end = System.currentTimeMillis();
        runTimes.put(callCount, (end - start));
        isRunning = false;

    }

    protected abstract void work();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public int getCallCount() {
        return callCount;
    }

    public void setCallCount(int callCount) {
        this.callCount = callCount;
    }

    public Map<Integer, Long> getRunTimes() {
        return runTimes;
    }

    public void setRunTimes(Map<Integer, Long> runTimes) {
        this.runTimes = runTimes;
    }
}
