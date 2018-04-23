package com.buzz.test.pattern.other.balking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WashingMachine {
    private Logger logger = LoggerFactory.getLogger(WashingMachine.class);

    private WashingMachineState washingMachineState;

    public WashingMachine() {
        this.washingMachineState = WashingMachineState.ENABLE;
    }

    public WashingMachineState getWashingMachineState() {
        return washingMachineState;
    }

    public void wash() {
        synchronized (this) {
            logger.info("{} Actual machine state:{} ", Thread.currentThread().getName(), getWashingMachineState());
            if (washingMachineState == WashingMachineState.WASHING) {
                logger.error("ERROR: can not wash because the machine has already been washing");
            }
            washingMachineState = WashingMachineState.WASHING;
        }

        logger.info("{} doing the washing", Thread.currentThread().getName());

        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        endOfWashing();
    }

    public synchronized void endOfWashing() {
        washingMachineState = WashingMachineState.ENABLE;
        logger.info("{}:washing completed.", Thread.currentThread().getName());
    }

}
