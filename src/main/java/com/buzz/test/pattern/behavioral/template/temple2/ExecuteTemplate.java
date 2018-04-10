package com.buzz.test.pattern.behavioral.template.temple2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ExecuteTemplate {

    private Logger logger = LoggerFactory.getLogger(ExecuteTemplate.class);

    protected abstract void beforeExecute();

    protected abstract void afterExecute();

    public void doJob() {
        logger.info("do job implementation.");
    }

    public final void execute() {
        this.beforeExecute();
        this.doJob();
        this.afterExecute();
    }
}
