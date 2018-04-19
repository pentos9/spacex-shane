package com.buzz.test.pattern.behavioral.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoldierVisitor implements UnitVisitor {

    private static Logger logger = LoggerFactory.getLogger(Soldier.class);

    @Override
    public void visitSoldier(Soldier soldier) {
        logger.info("Greetings {}", soldier.toString());
    }

    @Override
    public void visitSergeant(Sergeant sergeant) {
        //do nothing
    }

    @Override
    public void visitCommander(Commander commander) {
        //do nothing
    }
}
