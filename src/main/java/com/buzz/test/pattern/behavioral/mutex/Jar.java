package com.buzz.test.pattern.behavioral.mutex;

public class Jar {
    private final Lock lock;

    private int beans;

    public Jar(Lock lock, int beans) {
        this.lock = lock;
        this.beans = beans;
    }

    public int getBeans() {
        return beans;
    }

    public boolean takeBean() {
        boolean success = false;

        try {
            lock.acquire();
            success = beans > 0;
            if (success) {
                beans = beans - 1;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.release();
        }

        return success;
    }
}

