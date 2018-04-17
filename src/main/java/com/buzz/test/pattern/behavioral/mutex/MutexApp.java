package com.buzz.test.pattern.behavioral.mutex;

public class MutexApp {
    public static void main(String[] args) {
        Mutex mutex = new Mutex();
        Jar jar = new Jar(mutex, 1000);

        Thief peter = new Thief("peter", jar);
        Thief john = new Thief("john", jar);

        peter.start();
        john.start();
    }
}
