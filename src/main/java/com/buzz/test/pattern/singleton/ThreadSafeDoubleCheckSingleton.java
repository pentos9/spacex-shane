package com.buzz.test.pattern.singleton;

public class ThreadSafeDoubleCheckSingleton {

    private static volatile ThreadSafeDoubleCheckSingleton instance;

    private ThreadSafeDoubleCheckSingleton() {

        //to prevent instantiating by Reflection call,it is important
        if (instance != null) {
            throw new IllegalArgumentException("already initialized.");
        }
    }

    public static ThreadSafeDoubleCheckSingleton getInstance() {

        if (instance == null) {
            synchronized (ThreadSafeDoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeDoubleCheckSingleton();
                }
            }
        }

        return instance;
    }
}
