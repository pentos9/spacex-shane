package com.buzz.test.pattern.behavioral.command;

import com.sun.tools.classfile.Annotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Target {
    private Logger logger = LoggerFactory.getLogger(Target.class);

    private Size size;

    private Visibility visibility;

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    @Override
    public abstract String toString();

    public void printStatus() {
        logger.info("{},[size={}] [visibility={}]", this, getSize(), getVisibility());
    }
}
