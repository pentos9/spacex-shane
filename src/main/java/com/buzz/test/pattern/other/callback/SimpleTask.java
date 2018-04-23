package com.buzz.test.pattern.other.callback;

import com.buzz.test.pattern.other.pool.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTask extends Task {

    private Logger logger = LoggerFactory.getLogger(SimpleTask.class);

    @Override
    public void execute() {
        logger.info("perform some very important activity and call the callback method.");
    }
}
