package com.buzz.test.pattern.other.marker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarkerApp {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(MarkerApp.class);

        Thief thief = new Thief();
        Guard guard = new Guard();

        if (guard instanceof Permission) {
            guard.enter();
        } else {
            logger.info("you no permission to enter ,please leave this area");
        }

        if (thief instanceof Permission) {
            thief.steal();
        } else {
            thief.doNothing();
        }
    }
}
