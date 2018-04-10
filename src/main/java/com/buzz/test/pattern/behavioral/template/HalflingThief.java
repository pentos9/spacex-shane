package com.buzz.test.pattern.behavioral.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HalflingThief {
    private Logger logger = LoggerFactory.getLogger(HalflingThief.class);

    private StealingMethod method;

    public HalflingThief(StealingMethod method) {
        this.method = method;
    }

    public void changeMethod(StealingMethod method) {
        this.method = method;
    }

    public void steal() {
        method.steal();
    }
}
