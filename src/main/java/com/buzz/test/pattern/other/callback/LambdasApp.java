package com.buzz.test.pattern.other.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LambdasApp {
    private static Logger logger = LoggerFactory.getLogger(LambdasApp.class);

    public static void main(String[] args) {
        Task task = new SimpleTask();
        Callback callback = () -> logger.info("I'm done now");
        task.executeWith(callback);

        Callback hook = new Callback() {
            @Override
            public void call() {
                logger.info("I'm done now");
            }
        };

        task.executeWith(hook);
    }
}
