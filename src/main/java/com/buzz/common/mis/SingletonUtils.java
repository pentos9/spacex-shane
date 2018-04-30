package com.buzz.common.mis;

public abstract class SingletonUtils<T> {
    private volatile T instance;

    protected abstract T newInstance();

    public final T getInstance() {
        if (instance == null) {
            synchronized (SingletonUtils.class) {
                if (instance == null) {
                    instance = newInstance();
                }
            }
        }

        return instance;
    }
}
