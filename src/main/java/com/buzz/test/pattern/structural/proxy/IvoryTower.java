package com.buzz.test.pattern.structural.proxy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IvoryTower implements WizardTower {

    private Logger logger = LoggerFactory.getLogger(IvoryTower.class);

    @Override
    public void enter(Wizard wizard) {
        logger.info(String.format("%s enters the tower.", wizard.getName()));
    }
}
