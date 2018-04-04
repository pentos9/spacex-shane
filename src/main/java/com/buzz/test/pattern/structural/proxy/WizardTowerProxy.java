package com.buzz.test.pattern.structural.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WizardTowerProxy implements WizardTower {

    private Logger logger = LoggerFactory.getLogger(WizardTowerProxy.class);

    private static final int NUMS_WIZARD_ALLOWED = 3;

    private int numWizards = 0;

    private WizardTower tower;

    public WizardTowerProxy(WizardTower tower) {
        this.tower = tower;
    }

    @Override
    public void enter(Wizard wizard) {
        if (numWizards < NUMS_WIZARD_ALLOWED) {
            tower.enter(wizard);
            numWizards++;
        } else {
            logger.info(String.format("%s is not allowed to enter", wizard.getName()));
        }

    }
}
