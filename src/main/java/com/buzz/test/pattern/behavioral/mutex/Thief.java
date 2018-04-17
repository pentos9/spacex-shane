package com.buzz.test.pattern.behavioral.mutex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Thief extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(Thief.class);

    private final String name;

    private final Jar jar;

    public Thief(String name, Jar jar) {
        this.name = name;
        this.jar = jar;
    }

    @Override
    public void run() {

        int beans = 0;
        while (jar.takeBean()) {
            beans = beans + 1;
            logger.info("{} toke a bean", name);
        }
        logger.info("{} toke {} beans.", name, beans);
    }
}
