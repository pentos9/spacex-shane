package com.buzz.test.pattern.other.callback;

public abstract class Task {
    public void executeWith(Callback callback) {
        execute();
        if (callback != null) {
            callback.call();
        }
    }

    public abstract void execute();
}
