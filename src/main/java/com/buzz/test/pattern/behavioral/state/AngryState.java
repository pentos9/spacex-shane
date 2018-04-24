package com.buzz.test.pattern.behavioral.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AngryState implements State {

    private Logger logger = LoggerFactory.getLogger(AngryState.class);

    private Mammoth mammoth;

    public AngryState(Mammoth mammoth) {
        this.mammoth = mammoth;
    }

    @Override
    public void onEnterState() {
        logger.info("{} gets angry!", mammoth);
    }

    @Override
    public void observe() {
        logger.info("{} is furious!", mammoth);
    }
}
