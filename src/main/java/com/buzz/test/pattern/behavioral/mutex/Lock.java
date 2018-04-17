package com.buzz.test.pattern.behavioral.mutex;

public interface Lock {
    void acquire() throws InterruptedException;

    void release();
}
