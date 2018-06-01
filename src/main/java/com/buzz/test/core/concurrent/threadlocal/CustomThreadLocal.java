package com.buzz.test.core.concurrent.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomThreadLocal<T> {
    private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<Thread, T>());

    public void set(T value) {
        container.put(Thread.currentThread(), value);
    }

    public T get() {
        Thread thread = Thread.currentThread();
        T value = container.get(thread);
        if (value == null && !container.containsKey(thread)) {
            value = initialValue();
            container.put(thread, value);
        }
        return value;
    }

    protected T initialValue() {
        return null;
    }
}
