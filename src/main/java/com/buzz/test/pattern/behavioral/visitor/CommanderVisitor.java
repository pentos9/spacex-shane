package com.buzz.test.pattern.behavioral.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommanderVisitor implements UnitVisitor {

    private static Logger logger = LoggerFactory.getLogger(CommanderVisitor.class);

    @Override
    public void visitSoldier(Soldier soldier) {

    }

    @Override
    public void visitSergeant(Sergeant sergeant) {

    }

    @Override
    public void visitCommander(Commander commander) {
        logger.info("good to see you,", commander);
    }
}
