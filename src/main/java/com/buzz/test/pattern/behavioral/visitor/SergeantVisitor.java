package com.buzz.test.pattern.behavioral.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SergeantVisitor implements UnitVisitor {

    private static Logger logger = LoggerFactory.getLogger(SergeantVisitor.class);

    @Override
    public void visitSoldier(Soldier soldier) {

    }

    @Override
    public void visitSergeant(Sergeant sergeant) {
        logger.info("Hello {}", sergeant.toString());
    }

    @Override
    public void visitCommander(Commander commander) {

    }
}
