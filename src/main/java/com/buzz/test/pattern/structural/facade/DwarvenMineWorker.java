package com.buzz.test.pattern.structural.facade;

import com.buzz.util.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DwarvenMineWorker {

    private static Logger logger = LoggerFactory.getLogger(DwarvenMineWorker.class);

    public void sleep() {
        logger.info("{} goes to sleep.", name());
    }

    public void wakeUp() {
        logger.info("{} wakes up.", name());
    }

    public void goHome() {
        logger.info("{} goes home.", name());
    }

    public void goToMine() {
        logger.info("{} goes to mine.", name());
    }

    private void action(Action action) {
        switch (action) {
            case SLEEP:
                sleep();
                break;

            case WAKE_UP:
                wakeUp();
                break;

            case GO_HOME:
                goHome();
                break;

            case GO_TO_MINE:
                goToMine();
                break;

            case WORK:
                work();
                break;

            default:
                logger.error("undefined action");
                break;
        }

    }

    public void action(Action... actions) {
        for (Action action : actions) {
            action(action);
        }
    }

    public abstract void work();

    public abstract String name();

    static enum Action {
        SLEEP, WAKE_UP, GO_HOME, GO_TO_MINE, WORK;
    }

}
